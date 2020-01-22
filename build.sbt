
name := "sPDF"

version := "0.0.1"

description := "Create PDFs using plain old HTML+CSS. Uses wkhtmltopdf on the back-end which renders HTML using Webkit."

licenses := Seq(
  ("MIT", url("http://opensource.org/licenses/MIT"))
)

organization := "io.flow"

scalaVersion := "2.13.1"

crossScalaVersions := Seq("2.12.10", "2.13.1")

libraryDependencies ++= Seq(
  "org.mockito" %% "mockito-scala-scalatest" % "1.11.0",
  "org.scalatest" %% "scalatest" % "3.1.0" % Test,
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
  compilerPlugin("com.github.ghik" %% "silencer-plugin" % "1.4.4" cross CrossVersion.full),
  "com.github.ghik" %% "silencer-lib" % "1.4.4" % Provided cross CrossVersion.full,
)

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
