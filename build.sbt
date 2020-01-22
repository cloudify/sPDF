
name := "lib-pdf-generator"

version := "0.0.1"

description := "Create PDFs using plain old HTML+CSS. Uses wkhtmltopdf on the back-end which renders HTML using Webkit."

homepage := Some(url("https://github.com/cloudify/sPDF"))

startYear := Some(2013)

licenses := Seq(
  ("MIT", url("http://opensource.org/licenses/MIT"))
)

organization := "io.flow"

scalaVersion := "2.13.1"

crossScalaVersions := Seq("2.12.10", "2.13.1")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8"
)

fork in Test := true

parallelExecution in Test := false

logLevel in compile := Level.Warn

scmInfo := Some(
  ScmInfo(
    url("https://github.com/cloudify/sPDF"),
    "scm:git:https://github.com/cloudify/sPDF.git",
    Some("scm:git:git@github.com:cloudify/sPDF.git")
  )
)

// add dependencies on standard Scala modules, in a way
// supporting cross-version publishing
// taken from: http://github.com/scala/scala-module-dependency-sample
libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
  )
    case _ =>
      libraryDependencies.value
  }
}

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.0" % Test,
  "org.mockito" % "mockito-core" % "3.2.4" % Test,
  compilerPlugin("com.github.ghik" %% "silencer-plugin" % "1.4.4" cross CrossVersion.full),
  "com.github.ghik" %% "silencer-lib" % "1.4.4" % Provided cross CrossVersion.full,
)

// publishing
publishMavenStyle := true

resolvers += "Artifactory" at "https://flow.jfrog.io/flow/libs-release/"

publishTo := {
  val host = "https://flow.jfrog.io/flow"
  if (isSnapshot.value) {
    Some("Artifactory Realm" at s"$host/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime)
  } else {
    Some("Artifactory Realm" at s"$host/libs-release-local")
  }
}

credentials += Credentials(
  "Artifactory Realm",
  "flow.jfrog.io",
  System.getenv("ARTIFACTORY_USERNAME"),
  System.getenv("ARTIFACTORY_PASSWORD")
)

publishArtifact in Test := false

// publishArtifact in (Compile, packageDoc) := false

// publishArtifact in (Compile, packageSrc) := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <developers>
    <developer>
      <id>cloudify</id>
      <name>Federico Feroldi</name>
      <email>pix@yahoo.it</email>
      <url>http://www.pixzone.com</url>
    </developer>
  </developers>
  )

// Josh Suereth's step-by-step guide to publishing on sonatype
// http://www.scala-sbt.org/using_sonatype.html
