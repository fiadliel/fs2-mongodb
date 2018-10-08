package org.lyranthe.fs2_mongodb

import com.mongodb.async.client.{MongoClient, MongoClients}
import com.mongodb.MongoClientSettings

import fs2._
import cats.effect.Sync

object Mongo {
  def fromUrl[F[_]](url: String)(implicit F: Sync[F]): Stream[F, MongoClient] = {
    Stream.bracket(F.delay(MongoClients.create(url))){ client =>
      F.delay(client.close())
    }
  }
  def fromSettings[F[_]](settings: MongoClientSettings)(
    implicit F: Sync[F]): Stream[F, MongoClient] = {
    Stream.bracket(F.delay(MongoClients.create(settings)))(client =>
      F.delay(client.close()))
  }
}
