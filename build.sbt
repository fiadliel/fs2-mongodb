scalaVersion := "2.11.12"

crossScalaVersions := List("2.11.12", "2.12.4")

organization := "org.lyranthe"

name := "fs2-mongodb"

libraryDependencies += "org.mongodb" % "mongodb-driver-async" % "3.6.3"
libraryDependencies += "co.fs2"      %% "fs2-core"            % "1.0.0-M1"

enablePlugins(GitVersioning)

publishTo := sonatypePublishTo.value
sonatypeProfileName := "org.lyranthe"
publishMavenStyle := true
licenses in Global += "MIT" -> url("https://github.com/fiadliel/fs2-mongodb/blob/master/LICENSE")

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("fiadliel", "fs2-mongodb", "Gary Coady", "gary@lyranthe.org"))
