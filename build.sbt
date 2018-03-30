


lazy val commonSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.4",
  organization := "com.mattkirwan",
)


lazy val dockerSettings = Seq(
  dockerfile in docker := {
    val artifact: File = assembly.value
    val artifactTargetPath = s"/app/${artifact.name}"

    new Dockerfile {
      from("openjdk:8-jre")
      add(artifact, artifactTargetPath)
      entryPoint("java", "-jar", artifactTargetPath)
    }
  }
  buildOptions in docker := BuildOptions(cache = false)
)

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl, ScalatraPlugin, DockerPlugin)
  .settings(
    commonSettings,
    dockerSettings,
    mainClass in assembly := Some("com.mattkirwan.Main"),
    name := "personal-website",
    libraryDependencies += "org.scalatra" %% "scalatra" % "2.6.3",
    libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.4.9.v20180320" % "container;compile",
    libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
  )