package org.lyranthe.fs2_mongodb

import com.mongodb.async.client.{MongoClient, MongoClients}
import fs2._
import cats.effect.Async

object Mongo {
  def client[F[_]](url: String)(implicit A: Async[F]): Stream[F, MongoClient] = {
    Stream.bracket(A.delay(MongoClients.create(url)))(Stream.emit, { client =>
      A.delay(client.close())
    })
  }
}
