package org.lyranthe.fs2_mongodb

import cats.effect.Async
import cats.syntax.functor.toFunctorOps
import com.mongodb.MongoNamespace
import com.mongodb.async.client.MongoCollection
import com.mongodb.bulk.BulkWriteResult
import com.mongodb.client.model._
import com.mongodb.client.result._
import org.bson.conversions.Bson

import scala.collection.JavaConverters._

private[fs2_mongodb] final class MongoCollectionEffect[F[_], A](
    val underlying: MongoCollection[A])
  extends AnyVal {
  import imports.AsyncToMongo

  @inline
  def bulkWrite(requests: Seq[WriteModel[A]])(implicit F: Async[F]): F[BulkWriteResult] =
    F.async[BulkWriteResult] { cb =>
      underlying.bulkWrite(requests.asJava, cb.toMongo)
    }

  @inline
  def bulkWrite(requests: Seq[WriteModel[A]], options: BulkWriteOptions)
               (implicit F: Async[F]): F[BulkWriteResult] =
    F.async[BulkWriteResult] { cb =>
      underlying.bulkWrite(requests.asJava, options, cb.toMongo)
    }

  @inline
  def count()(implicit F: Async[F]): F[Long] =
    F.async[java.lang.Long] { cb =>
      underlying.countDocuments(cb.toMongo)
    } map { count =>
      count.toLong
    }

  @inline
  def count(filter: Bson)(implicit F: Async[F]): F[Long] =
    F.async[java.lang.Long] { cb =>
      underlying.countDocuments(filter, cb.toMongo)
    } map { count =>
      count.toLong
    }

  @inline
  def count(filter: Bson, options: CountOptions)(implicit F: Async[F]): F[Long] =
    F.async[java.lang.Long] { cb =>
      underlying.countDocuments(filter, options, cb.toMongo)
    } map { count =>
      count.toLong
    }

  @inline
  def createIndex(key: Bson)(implicit F: Async[F]): F[String] =
    F.async[String] { cb =>
      underlying.createIndex(key, cb.toMongo)
    }

  @inline
  def createIndex(key: Bson, options: IndexOptions)(implicit F: Async[F]): F[String] =
    F.async[String] { cb =>
      underlying.createIndex(key, options, cb.toMongo)
    }

  @inline
  def createIndexes(indexes: Seq[IndexModel])(implicit F: Async[F]): F[Seq[String]] =
    F.async[java.util.List[String]] { cb =>
      underlying.createIndexes(indexes.asJava, cb.toMongo)
    } map { indexes =>
      indexes.asScala
    }

  @inline
  def createIndexes(indexes: Seq[IndexModel], options: CreateIndexOptions)
                   (implicit F: Async[F]): F[Seq[String]] =
    F.async[java.util.List[String]] { cb =>
      underlying.createIndexes(indexes.asJava, options, cb.toMongo)
    } map { indexes =>
      indexes.asScala
    }

  @inline
  def deleteMany(filter: Bson)(implicit F: Async[F]): F[DeleteResult] =
    F.async[DeleteResult] { cb =>
      underlying.deleteMany(filter, cb.toMongo)
    }

  @inline
  def deleteMany(filter: Bson, options: DeleteOptions)(implicit F: Async[F]): F[DeleteResult] =
    F.async[DeleteResult] { cb =>
      underlying.deleteMany(filter, options, cb.toMongo)
    }

  @inline
  def deleteOne(filter: Bson)(implicit F: Async[F]): F[DeleteResult] =
    F.async[DeleteResult] { cb =>
      underlying.deleteOne(filter, cb.toMongo)
    }

  @inline
  def deleteOne(filter: Bson, options: DeleteOptions)(implicit F: Async[F]): F[DeleteResult] =
    F.async[DeleteResult] { cb =>
      underlying.deleteOne(filter, options, cb.toMongo)
    }

  @inline
  def drop()(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.drop(cb.toMongo)
    }.void

  @inline
  def dropIndex(indexName: String)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.dropIndex(indexName, cb.toMongo)
    }.void

  @inline
  def dropIndex(indexName: String, options: DropIndexOptions)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.dropIndex(indexName, options, cb.toMongo)
    }.void

  @inline
  def dropIndex(keys: Bson)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.dropIndex(keys, cb.toMongo)
    }.void

  @inline
  def dropIndex(keys: Bson, options: DropIndexOptions)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.dropIndex(keys, options, cb.toMongo)
    }.void

  @inline
  def dropIndexes()(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.dropIndexes(cb.toMongo)
    }.void

  @inline
  def dropIndexes(options: DropIndexOptions)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.dropIndexes(options, cb.toMongo)
    }.void

  @inline
  def findOneAndDelete(filter: Bson)(implicit F: Async[F]): F[A] =
    F.async[A] { cb =>
      underlying.findOneAndDelete(filter, cb.toMongo)
    }

  @inline
  def findOneAndDelete(filter: Bson, options: FindOneAndDeleteOptions)(implicit F: Async[F]): F[A] =
    F.async[A] { cb =>
      underlying.findOneAndDelete(filter, options, cb.toMongo)
    }

  @inline
  def findOneAndReplace(filter: Bson, replacement: A)(implicit F: Async[F]): F[A] =
    F.async[A] { cb =>
      underlying.findOneAndReplace(filter, replacement, cb.toMongo)
    }

  @inline
  def findOneAndReplace(filter: Bson, replacement: A, options: FindOneAndReplaceOptions)
                       (implicit F: Async[F]): F[A] =
    F.async[A] { cb =>
      underlying.findOneAndReplace(filter, replacement, options, cb.toMongo)
    }

  @inline
  def findOneAndUpdate(filter: Bson, update: Bson)(implicit F: Async[F]): F[A] =
    F.async[A] { cb =>
      underlying.findOneAndUpdate(filter, update, cb.toMongo)
    }

  @inline
  def findOneAndUpdate(filter: Bson, update: Bson, options: FindOneAndUpdateOptions)
                      (implicit F: Async[F]): F[A] =
    F.async[A] { cb =>
      underlying.findOneAndUpdate(filter, update, options, cb.toMongo)
    }

  @inline
  def insertMany(documents: Seq[A])(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.insertMany(documents.asJava, cb.toMongo)
    }.void

  @inline
  def insertMany(documents: Seq[A], options: InsertManyOptions)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.insertMany(documents.asJava, options, cb.toMongo)
    }.void

  @inline
  def insertOne(document: A)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.insertOne(document, cb.toMongo)
    }.void

  @inline
  def insertOne(document: A, options: InsertOneOptions)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.insertOne(document, options, cb.toMongo)
    }.void

  @inline
  def renameCollection(newCollectionName: MongoNamespace)(implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.renameCollection(newCollectionName, cb.toMongo)
    }.void

  @inline
  def renameCollection(newCollectionName: MongoNamespace, options: RenameCollectionOptions)
                      (implicit F: Async[F]): F[Unit] =
    F.async[Void] { cb =>
      underlying.renameCollection(newCollectionName, options, cb.toMongo)
    }.void

  @inline
  def replaceOne(filter: Bson, replacement: A)(implicit F: Async[F]): F[UpdateResult] =
    F.async[UpdateResult] { cb =>
      underlying.replaceOne(filter, replacement, cb.toMongo)
    }

  @inline
  def replaceOne(filter: Bson, replacement: A, options: ReplaceOptions)
                (implicit F: Async[F]): F[UpdateResult] =
    F.async[UpdateResult] { cb =>
      underlying.replaceOne(filter, replacement, options, cb.toMongo)
    }

  @inline
  def updateMany(filter: Bson, update: Bson)(implicit F: Async[F]): F[UpdateResult] =
    F.async[UpdateResult] { cb =>
      underlying.updateMany(filter, update, cb.toMongo)
    }

  @inline
  def updateMany(filter: Bson, update: Bson, options: UpdateOptions)
                (implicit F: Async[F]): F[UpdateResult] =
    F.async[UpdateResult] { cb =>
      underlying.updateMany(filter, update, options, cb.toMongo)
    }

  @inline
  def updateOne(filter: Bson, update: Bson)(implicit F: Async[F]): F[UpdateResult] =
    F.async[UpdateResult] { cb =>
      underlying.updateOne(filter, update, cb.toMongo)
    }

  @inline
  def updateOne(filter: Bson, update: Bson, options: UpdateOptions)
               (implicit F: Async[F]): F[UpdateResult] =
    F.async[UpdateResult] { cb =>
      underlying.updateOne(filter, update, options, cb.toMongo)
    }
}
