addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8.3")

resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.7.0")

resolvers ++= Seq(
  "less is" at "http://repo.lessis.me"
)

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.2")

// This resolver declaration is added by default in SBT 0.12.x
resolvers += Resolver.url(
  "sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
)(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.5")
