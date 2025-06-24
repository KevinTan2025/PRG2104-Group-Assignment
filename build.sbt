ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.6"
run / fork := true

libraryDependencies ++= Seq(
  "org.openjfx" % "javafx-controls" % "24.0.1",
  "org.openjfx" % "javafx-fxml" % "24.0.1",
  "com.github.tototoshi" %% "scala-csv" % "2.0.0"
)

resolvers += "Maven Central" at "https://repo1.maven.org/maven2/"
