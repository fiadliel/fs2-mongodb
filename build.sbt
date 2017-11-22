scalaVersion := "2.11.11"

crossScalaVersions := List("2.11.11", "2.12.2")

organization := "org.lyranthe"

name := "fs2-mongodb"

libraryDependencies += "org.mongodb" % "mongodb-driver-async" % "3.6.0-rc0"
libraryDependencies += "co.fs2"      %% "fs2-core"            % "0.10.0-M8"

enablePlugins(GitVersioning)

homepage in Global := Some(url("https://github.com/fiadliel/fs2-mongodb"))
licenses in Global += "MIT" -> url("https://github.com/fiadliel/fs2-mongodb/blob/master/LICENSE")
developers in Global += Developer("fiadliel",
                                  "Gary Coady",
                                  "gary@lyranthe.org",
                                  url("https://www.lyranthe.org/"))
