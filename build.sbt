
name := "sPDF"

description := "Create PDFs using plain old HTML+CSS. Uses wkhtmltopdf on the back-end which renders HTML using Webkit."

homepage := Some(url("https://github.com/cloudify/sPDF"))

startYear := Some(2013)

licenses := Seq(
  ("MIT", url("http://opensource.org/licenses/MIT"))
)

organization := "io.github.cloudify"

scalaVersion := "2.13.0"
scalacOptions ++= Seq(
  "-deprecation"          // Emit warning and location for usages of deprecated APIs.
  , "-encoding", "utf-8"  // Specify character encoding used by source files.
  , "-explaintypes"       // Explain type errors in more detail.
  , "-feature"            // Emit warning and location for usages of features that should be imported explicitly.
  , "-unchecked"          // Enable additional warnings where generated code depends on assumptions.
  , "-Xfatal-warnings"    // Fail the compilation if there are any fatal warnings.
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

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
)

libraryDependencies ++= Seq (
  "org.scalatest"     %% "scalatest"             % "3.1.0"    % Test,
  "org.scalatestplus" %% "scalatestplus-mockito" % "1.0.0-M2" % Test,
  "org.mockito"       %  "mockito-all"           % "1.10.8"   % Test
)

publishArtifact in Test := false
