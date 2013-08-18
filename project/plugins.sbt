addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8.1")

// addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.4")

// addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.1")

// addSbtPlugin("io.spray" % "sbt-revolver" % "0.6.2")

// addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.6")

// addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.0")

resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.7.0")

resolvers ++= Seq(
  "less is" at "http://repo.lessis.me"
)

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.2")

