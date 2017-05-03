scalaVersion := "2.11.11"

crossScalaVersions := List("2.11.11", "2.12.2")

organization := "org.lyranthe"

name := "fs2-mongodb"

libraryDependencies += "org.mongodb" % "mongodb-driver-async" % "3.0.4"
libraryDependencies += "co.fs2"      %% "fs2-core"            % "0.9.5"

enablePlugins(GitVersioning)

pomExtra in Global := {
  <url>https://github.com/fiadliel/fs2-mongodb</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>https://github.com/fiadliel/fs2-mongodb/blob/master/LICENSE</url>
    </license>
  </licenses>
  <developers>
    <developer>
      <id>fiadliel</id>
      <name>Gary Coady</name>
      <url>https://www.lyranthe.org/</url>
    </developer>
  </developers>
}

