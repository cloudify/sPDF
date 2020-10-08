name := "sPDF"

version := "1.4.7"

description := "Create PDFs using plain old HTML+CSS. Uses wkhtmltopdf on the back-end which renders HTML using Webkit."

licenses := Seq(
  ("MIT", url("http://opensource.org/licenses/MIT"))
)

organization := "io.flow"

scalaVersion := "2.13.3"

crossScalaVersions := Seq("2.12.10", "2.13.3")

fork in Test := true

libraryDependencies ++= Seq(
  "org.mockito" %% "mockito-scala-scalatest" % "1.16.0" % Test,
  "org.scalatest" %% "scalatest" % "3.2.2" % Test,
  "org.scala-lang.modules" %% "scala-xml" % "1.3.0",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
)


libraryDependencies ++= (scalaBinaryVersion.value match {
  case "2.12" => Seq(
    compilerPlugin("com.github.ghik" % "silencer-plugin" % "1.6.0" cross CrossVersion.full),
    "com.github.ghik" % "silencer-lib" % "1.6.0" % Provided cross CrossVersion.full,
    "org.scala-lang.modules" %% "scala-collection-compat" % "2.2.0",
  )
  case _ => Seq()
})

scalacOptions += (scalaBinaryVersion.value match {
  case "2.12" => "-P:silencer:globalFilters=parameter value.*?is never used"
  case _ => ""
})

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
