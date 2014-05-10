name := "we-lab3-group94"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaCore,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.1.Final",
  "com.google.code.gson" % "gson" % "2.2"
)

templatesImport += "scala.collection._"

templatesImport += "at.ac.tuwien.big.we14.lab2.api._"

play.Project.playJavaSettings