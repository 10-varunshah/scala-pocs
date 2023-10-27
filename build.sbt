ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "scala-pocs"
  )

libraryDependencies ++= Seq(
  "org.apache.pdfbox" % "pdfbox" % "3.0.0"
)

