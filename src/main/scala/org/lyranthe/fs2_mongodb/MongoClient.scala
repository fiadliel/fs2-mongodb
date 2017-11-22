package org.lyranthe.fs2_mongodb

import com.mongodb.async.client.{MongoClient, MongoClients}
import fs2._
import cats.effect.Sync

object Mongo {
  def client[F[_]](url: String)(implicit F: Sync[F]): Stream[F, MongoClient] = {
    Stream.bracket(F.delay(MongoClients.create(url)))(Stream.emit(_), { client =>
      F.delay(client.close())
    })
  }
}
