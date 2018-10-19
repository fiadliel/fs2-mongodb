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

  def bulkWrite(requests: Seq[WriteModel[A]])(implicit F: Async[F]): F[BulkWriteResult] = {
    Async[F]
      .async[BulkWriteResult] { cb =>
        underlying.bulkWrite(requests.asJava, cb.toMongo)
      }
  }

  def bulkWrite(requests: Seq[WriteModel[A]], options: BulkWriteOptions)
               (implicit F: Async[F]): F[BulkWriteResult] = {
    Async[F]
      .async[BulkWriteResult] { cb =>
        underlying.bulkWrite(requests.asJava, options, cb.toMongo)
      }
  }

  def count()(implicit F: Async[F]): F[Long] = {
    Async[F]
      .async[java.lang.Long] { cb =>
        underlying.countDocuments(cb.toMongo)
      }
      .map(_.longValue())
  }

  def count(filter: Bson)(implicit F: Async[F]): F[Long] = {
    Async[F]
      .async[java.lang.Long] { cb =>
        underlying.countDocuments(filter, cb.toMongo)
      }
      .map(_.longValue())
  }

  def count(filter: Bson, options: CountOptions)(implicit F: Async[F]): F[Long] = {
    Async[F]
      .async[java.lang.Long] { cb =>
        underlying.countDocuments(filter, options, cb.toMongo)
      }
      .map(_.longValue())
  }

  def createIndex(key: Bson)(implicit F: Async[F]): F[String] = {
    Async[F]
      .async[String] { cb =>
        underlying.createIndex(key, cb.toMongo)
      }
  }

  def createIndex(key: Bson, options: IndexOptions)(implicit F: Async[F]): F[String] = {
    Async[F]
      .async[String] { cb =>
        underlying.createIndex(key, options, cb.toMongo)
      }
  }

  def createIndexes(indexes: Seq[IndexModel])(implicit F: Async[F]): F[Seq[String]] = {
    Async[F]
      .async[java.util.List[String]] { cb =>
        underlying.createIndexes(indexes.asJava, cb.toMongo)
      }
      .map(_.asScala)
  }

  def createIndexes(indexes: Seq[IndexModel], options: CreateIndexOptions)
                   (implicit F: Async[F]): F[Seq[String]] = {
    Async[F]
      .async[java.util.List[String]] { cb =>
        underlying.createIndexes(indexes.asJava, options, cb.toMongo)
      }
      .map(_.asScala)
  }

  def deleteMany(filter: Bson)(implicit F: Async[F]): F[DeleteResult] = {
    Async[F]
      .async[DeleteResult] { cb =>
        underlying.deleteMany(filter, cb.toMongo)
      }
  }

  def deleteMany(filter: Bson, options: DeleteOptions)
                (implicit F: Async[F]): F[DeleteResult] = {
    Async[F]
      .async[DeleteResult] { cb =>
        underlying.deleteMany(filter, options, cb.toMongo)
      }
  }

  def deleteOne(filter: Bson)(implicit F: Async[F]): F[DeleteResult] = {
    Async[F]
      .async[DeleteResult] { cb =>
        underlying.deleteOne(filter, cb.toMongo)
      }
  }

  def deleteOne(filter: Bson, options: DeleteOptions)(implicit F: Async[F]): F[DeleteResult] = {
    Async[F]
      .async[DeleteResult] { cb =>
        underlying.deleteOne(filter, options, cb.toMongo)
      }
  }

  def drop()(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.drop(cb.toMongo)
      }
      .void
  }

  def dropIndex(indexName: String)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.dropIndex(indexName, cb.toMongo)
      }
      .void
  }

  def dropIndex(indexName: String, options: DropIndexOptions)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.dropIndex(indexName, options, cb.toMongo)
      }
      .void
  }

  def dropIndex(keys: Bson)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.dropIndex(keys, cb.toMongo)
      }
      .void
  }

  def dropIndex(keys: Bson, options: DropIndexOptions)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.dropIndex(keys, options, cb.toMongo)
      }
      .void
  }

  def dropIndexes()(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.dropIndexes(cb.toMongo)
      }
      .void
  }

  def dropIndexes(options: DropIndexOptions)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.dropIndexes(options, cb.toMongo)
      }
      .void
  }

  def findOneAndDelete(filter: Bson)(implicit F: Async[F]): F[A] = {
    Async[F]
      .async[A] { cb =>
        underlying.findOneAndDelete(filter, cb.toMongo)
      }
  }

  def findOneAndDelete(filter: Bson, options: FindOneAndDeleteOptions)
                      (implicit F: Async[F]): F[A] = {
    Async[F]
      .async[A] { cb =>
        underlying.findOneAndDelete(filter, options, cb.toMongo)
      }
  }

  def findOneAndReplace(filter: Bson, replacement: A)(implicit F: Async[F]): F[A] = {
    Async[F]
      .async[A] { cb =>
        underlying.findOneAndReplace(filter, replacement, cb.toMongo)
      }
  }

  def findOneAndReplace(filter: Bson, replacement: A, options: FindOneAndReplaceOptions)
                       (implicit F: Async[F]): F[A] = {
    Async[F]
      .async[A] { cb =>
        underlying.findOneAndReplace(filter, replacement, options, cb.toMongo)
      }
  }

  def findOneAndUpdate(filter: Bson, update: Bson)(implicit F: Async[F]): F[A] = {
    Async[F]
      .async[A] { cb =>
        underlying.findOneAndUpdate(filter, update, cb.toMongo)
      }
  }

  def findOneAndUpdate(filter: Bson, update: Bson, options: FindOneAndUpdateOptions)
                      (implicit F: Async[F]): F[A] = {
    Async[F]
      .async[A] { cb =>
        underlying.findOneAndUpdate(filter, update, options, cb.toMongo)
      }
  }

  def insertMany(documents: Seq[A])(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.insertMany(documents.asJava, cb.toMongo)
      }
      .void
  }

  def insertMany(documents: Seq[A], options: InsertManyOptions)
                (implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.insertMany(documents.asJava, options, cb.toMongo)
      }
      .void
  }

  def insertOne(document: A)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.insertOne(document, cb.toMongo)
      }
      .void
  }

  def insertOne(document: A, options: InsertOneOptions)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.insertOne(document, options, cb.toMongo)
      }
      .void
  }

  def renameCollection(newCollectionName: MongoNamespace)(implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.renameCollection(newCollectionName, cb.toMongo)
      }
      .void
  }

  def renameCollection(newCollectionName: MongoNamespace, options: RenameCollectionOptions)
                      (implicit F: Async[F]): F[Unit] = {
    Async[F]
      .async[Void] { cb =>
        underlying.renameCollection(newCollectionName, options, cb.toMongo)
      }
      .void
  }

  def replaceOne(filter: Bson, replacement: A)(implicit F: Async[F]): F[UpdateResult] = {
    Async[F]
      .async[UpdateResult] { cb =>
        underlying.replaceOne(filter, replacement, cb.toMongo)
      }
  }

  def replaceOne(filter: Bson, replacement: A, options: ReplaceOptions)
                (implicit F: Async[F]): F[UpdateResult] = {
    Async[F]
      .async[UpdateResult] { cb =>
        underlying.replaceOne(filter, replacement, options, cb.toMongo)
      }
  }

  def updateMany(filter: Bson, update: Bson)(implicit F: Async[F]): F[UpdateResult] = {
    Async[F]
      .async[UpdateResult] { cb =>
        underlying.updateMany(filter, update, cb.toMongo)
      }
  }

  def updateMany(filter: Bson, update: Bson, options: UpdateOptions)
                (implicit F: Async[F]): F[UpdateResult] = {
    Async[F]
      .async[UpdateResult] { cb =>
        underlying.updateMany(filter, update, options, cb.toMongo)
      }
  }

  def updateOne(filter: Bson, update: Bson)(implicit F: Async[F]): F[UpdateResult] = {
    Async[F]
      .async[UpdateResult] { cb =>
        underlying.updateOne(filter, update, cb.toMongo)
      }
  }

  def updateOne(filter: Bson, update: Bson, options: UpdateOptions)
               (implicit F: Async[F]): F[UpdateResult] = {
    Async[F]
      .async[UpdateResult] { cb =>
        underlying.updateOne(filter, update, options, cb.toMongo)
      }
  }
}
