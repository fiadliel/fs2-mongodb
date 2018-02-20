addSbtPlugin("io.get-coursier"           % "sbt-coursier" % "1.0.2")
addSbtPlugin("com.typesafe.sbt"          % "sbt-git"      % "0.9.3")
addSbtPlugin("com.jsuereth"              % "sbt-pgp"      % "1.1.0")
addSbtPlugin("com.geirsson"              % "sbt-scalafmt" % "1.4.0")
addSbtPlugin("org.xerial.sbt"            % "sbt-sonatype" % "2.3")
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.3")
addSbtPlugin("com.timushev.sbt"          % "sbt-updates"  % "0.3.4")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"
