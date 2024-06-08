lazy val root = (project in file("."))
 // .withRank(KeyRanks.Invisible)
  .settings(
  // Project name (artifact name in Maven)
  name := "(java-web-sbt-springboot-batch)",

  // orgnization name (e.g., the package name of the project)
  organization := "example",

  version := "1.0-SNAPSHOT",

  // project description
  description := "SpringBoot Project",

  // Do not append Scala versions to the generated artifacts
  crossPaths := false,

  // This forbids including Scala related libraries into the dependency
  autoScalaLibrary := false,

  // used by sbt to set logging
  //logLevel := Level.Info,

  // javacOptions ++= Seq("-source", "11", "-target", "11", "-Xlint"),

  // Need springframework 2.5.0 or higher
  libraryDependencies ++= Seq(
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    "com.google.code.gson" % "gson" % "2.8.9",

    // https://mvnrepository.com/artifact/org.json/json
    "org.json" % "json" % "20180813",

    "com.vladmihalcea" % "hibernate-types-52" % "2.10.4",

    "org.springframework.boot" % "spring-boot-starter-web" % "2.6.0",
//    "org.apache.logging.log4j" % "log4j-core" % "2.14.1",
//    "org.apache.logging.log4j" % "log4j-api" % "2.14.1",
//    "org.springframework.boot" % "spring-boot-starter-log4j2" % "2.6.0"
//      exclude("ch.qos.logback", "logback-classic")
 //     exclude("org.springframework.boot", "spring-boot-starter-logging" ),
    "org.springframework.boot" % "spring-boot-starter-jdbc" % "2.6.0",
    "org.springframework.boot"%"spring-boot-starter-data-jpa"%"2.6.0",
    "org.springframework.boot" % "spring-boot-starter-batch" % "2.6.0",
    "org.projectlombok"%"lombok"%"1.18.24",
    "org.postgresql"%"postgresql" % "42.2.18.jre7",
    "jakarta.xml.bind" % "jakarta.xml.bind-api" % "2.3.2",
    "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2",
    "javax.annotation" % "javax.annotation-api" % "1.3.2"
   ),

  mainClass := Some("example.Main")
)
