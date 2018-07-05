package org.lyranthe.fs2_mongodb

import com.mongodb.async.client.{MongoClient, MongoClientSettings, MongoClients}
import fs2._
import cats.effect.Sync

object Mongo {

  def fromUrl[F[_]](url: String)(implicit F: Sync[F]): Stream[F, MongoClient] = {
    createStream(MongoClients.create(url))
  }

  def fromSettings[F[_]](settings: MongoClientSettings)(implicit F: Sync[F]): Stream[F, MongoClient] = {
    createStream(MongoClients.create(settings))
  }

  private def createStream[F[_]](mongoClient: => MongoClient)(implicit F: Sync[F]): Stream[F, MongoClient] = {
    Stream.bracket(F.delay(mongoClient)) { client =>
      F.delay(client.close())
    }
  }
}
