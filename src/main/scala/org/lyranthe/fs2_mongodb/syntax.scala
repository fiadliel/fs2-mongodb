package org.lyranthe.fs2_mongodb

import com.mongodb.async.client.MongoIterable
import com.mongodb.async.{AsyncBatchCursor, SingleResultCallback}
import fs2._

import scala.collection.JavaConverters._

object syntax {
  private[syntax] implicit class AsyncToMongoOpt[A](val cb: Either[Throwable, Option[A]] => Unit)
      extends AnyVal {
    def toMongo: SingleResultCallback[A] = toMongo(identity)

    def toMongo[B](f: B => A): SingleResultCallback[B] = {
      new SingleResultCallback[B] {
        override def onResult(result: B, throwable: Throwable): Unit = {
          (Option(result), Option(throwable)) match {
            case (_, Some(t)) => cb(Left(t))
            case (r, None)    => cb(Right(r map f))
          }
        }
      }
    }
  }

  private[syntax] implicit class AsyncToMongo[A](val cb: Either[Throwable, A] => Unit)
      extends AnyVal {
    def toMongo: SingleResultCallback[A] = toMongo(identity)

    def toMongo[B](f: B => A): SingleResultCallback[B] = {
      new SingleResultCallback[B] {
        override def onResult(result: B, throwable: Throwable): Unit = {
          (result, Option(throwable)) match {
            case (_, Some(t)) => cb(Left(t))
            case (r, None)    => cb(Right(f(r)))
          }
        }
      }
    }
  }

  implicit class MongoIterableSyntax[A <: MongoIterable[B], B](val iterable: A) extends AnyVal {
    private def closeCursor(maybeCursor: Option[AsyncBatchCursor[_]]): Task[Unit] =
      maybeCursor.fold(Task.now(()))(cursor => Task.delay(cursor.close()))

    private def iterate(maybeCursor: Option[AsyncBatchCursor[B]])(
        implicit S: Strategy): Stream[Task, B] = {
      maybeCursor match {
        case None =>
          Stream.empty

        case Some(cursor) =>
          Stream
            .repeatEval(asyncNext(cursor))
            .through(pipe.unNoneTerminate)
            .flatMap(values => Stream.chunk(Chunk.seq(values)))
      }
    }

    private def asyncBatchCursor(implicit S: Strategy): Task[Option[AsyncBatchCursor[B]]] = {
      Task.suspend {
        Task.async { cb =>
          iterable.batchCursor(cb.toMongo)
        }
      }
    }

    def stream(implicit S: Strategy): Stream[Task, B] = {
      Stream.bracket(asyncBatchCursor)(iterate, closeCursor)
    }
  }

  def asyncNext[T](cursor: AsyncBatchCursor[T])(implicit S: Strategy): Task[Option[Seq[T]]] = {
    if (cursor.isClosed) {
      Task.now(None)
    } else {
      Task.suspend {
        Task.async { cb =>
          cursor.next(cb.toMongo(_.asScala))
        }
      }
    }
  }
}
