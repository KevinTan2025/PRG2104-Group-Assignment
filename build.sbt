ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.6"
run / fork := false

val javafxVersion = "24.0.1"

libraryDependencies ++= Seq(
  "org.openjfx" % "javafx-controls" % javafxVersion classifier "win",
  "org.openjfx" % "javafx-fxml" % javafxVersion classifier "win",
  "org.openjfx" % "javafx-base" % javafxVersion classifier "win",
  "com.github.tototoshi" %% "scala-csv" % "2.0.0"
)

resolvers += "Maven Central" at "https://repo1.maven.org/maven2/"

// JavaFX specific JVM options
run / javaOptions ++= Seq(
  "--module-path", "lib",
  "--add-modules", "javafx.controls,javafx.fxml"
)
