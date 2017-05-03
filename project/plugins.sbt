addSbtPlugin("com.geirsson"     % "sbt-scalafmt" % "0.6.8")
addSbtPlugin("io.get-coursier"  % "sbt-coursier" % "1.0.0-RC1")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"  % "0.3.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git"      % "0.9.2")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")


libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.21"
