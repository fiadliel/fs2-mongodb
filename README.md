# fs2-mongodb

Library offers a very basic interface to stream results from MongoDB

## Installation

Add the following to your `build.sbt`:

```scala
libraryDependencies += "org.lyranthe" %% "fs2-mongodb" % "0.4.0"
```

## Usage

Add to your code:
```scala
import org.lyranthe.fs2_mongodb.imports._
```

### Creating a client

There are two ways of creating a `Stream[F, com.mongodb.async.client.MongoClient]`
which will be closed after use by FS2:

*`Mongo.fromUrl[F]`*
This takes a string representing a MongoDB URL (e.g. `mongodb://localhost`).

*`Mongo.fromSettings[F]`*
This takes a `com.mongodb.MongoClientSettings` object, and uses to set
up the client (this can include connecting to multiple databases, required authentication
information, etc.).

### Streaming iterables

When you have a value of type `com.mongodb.async.client.MongoIterable`, you can turn it into
a `fs2.Stream[Task, Document]` by calling `.stream[IO]`. You can supply any type (other
than `IO`) if there is a `cats.effect.Async` typeclass instance available for it.

One common requirement is to change the batch size for requests. This functionality is
available on the `MongoIterable` type, so you can chain a call like `.batchSize(1000)`
before turning it into a stream.

## Example

```scala
import cats.effect.IO
import fs2._
import org.bson.Document
import org.lyranthe.fs2_mongodb.imports._

val allDocuments: Stream[IO, Document] =
  for {
    conn <- Mongo.fromUrl[IO]("mongodb://localhost")
    database = conn.getDatabase("test_db")
    collection = database.getCollection("test_collection")
    document <- collection.find().stream[IO]
  } yield document
```
