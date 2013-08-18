addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8")

// addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.8.4")

// addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.1.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.1")

// addSbtPlugin("io.spray" % "sbt-revolver" % "0.6.2")

// addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.6")

// addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.0")

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.3")

resolvers ++= Seq(
  Classpaths.sbtPluginReleases,
  Opts.resolver.sonatypeReleases
)
