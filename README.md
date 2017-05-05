# fs2-mongodb

Library offers a very basic interface to stream results from MongoDB

## Installation

Add the following to your `build.sbt`:

```scala
libraryDependencies += "org.lyranthe" %% "fs2-mongodb" % "0.0.4"
```

## Usage

Add to your code:
```scala
import org.lyranthe.fs2_mongodb.imports._
```

When you have a value of type `com.mongodb.async.client.MongoIterable`, you can turn it into
a `fs2.Stream[Task, Document]` by calling `.stream[Task]`. You can supply any type (other
than `Task`) if there is an `Async` typeclass instance available for it.

## Example

```scala
import com.mongodb.async.client._
import fs2._
import org.bson.Document
import org.lyranthe.fs2_mongodb.imports._

implicit val strategy =
  Strategy.fromExecutionContext(scala.concurrent.ExecutionContext.global)

def mongoConnection(url: String): Stream[Task, MongoClient] =
    Stream.bracket(Task.delay(MongoClients.create(url)))(Stream.emit, { client =>
      Task.delay(client.close())
    })

val allDocuments: Stream[Task, Document] =
  mongoConnection("mongodb://localhost")
    .flatMap(_.getDatabase("dbname").getCollection("collname").find().stream[Task])
```
