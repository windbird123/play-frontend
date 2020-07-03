name := "play-frontend"
version := "1.0.0-SNAPSHOT"
organization in ThisBuild := "com.github.windbird123"
scalaVersion in ThisBuild := "2.12.10"

// PROJECTS
lazy val `play-frontend` = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(
    PlayKeys.devSettings ++= Seq(
      "play.server.http.port"  -> "9000",
      "play.server.https.port" -> "9443"
    )
  )
  .settings(
    commonSettings,
    libraryDependencies ++= commonDependencies ++ Seq(
      guice,
      dependencies.bootstrap,
      dependencies.playBootstrap,
      dependencies.scalatestplus % Test
    )
  )

// DEPENDENCIES
lazy val dependencies =
  new {
    // common dependencies
    val logback        = "ch.qos.logback"             % "logback-classic" % "1.2.3"
    val scalaLogging   = "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.2"
    val slf4j          = "org.slf4j"                  % "jcl-over-slf4j"  % "1.7.26"
    val typesafeConfig = "com.typesafe"               % "config"          % "1.3.2"
    val zio            = "dev.zio"                    %% "zio"            % "1.0.0-RC18-2"
    val zioStreams     = "dev.zio"                    %% "zio-streams"    % "1.0.0-RC18-2"
    val zioTest        = "dev.zio"                    %% "zio-test"       % "1.0.0-RC18-2"
    val zioTestSbt     = "dev.zio"                    %% "zio-test-sbt"   % "1.0.0-RC18-2"
    val scalatest      = "org.scalatest"              %% "scalatest"      % "3.0.5"

    // project specific dependencies
    val bootstrap     = "org.webjars"            % "bootstrap"           % "4.5.0"
    val playBootstrap = "com.adrianhurt"         %% "play-bootstrap"     % "1.6.1-P28-B4"
    val scalatestplus = "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0"
    val scalajHttp    = "org.scalaj"             %% "scalaj-http"        % "2.4.2"
  }

lazy val commonDependencies = Seq(
  dependencies.logback,
  dependencies.slf4j,
  dependencies.typesafeConfig,
  dependencies.zio,
  dependencies.zioStreams,
  dependencies.zioTest    % "test",
  dependencies.zioTestSbt % "test",
  dependencies.scalatest  % "test"
)

// SETTINGS
lazy val compilerOptions = Seq(
  "-encoding",
  "UTF-8",                 // source files are in UTF-8
  "-target:jvm-1.8",       // jvm-1.8
  "-deprecation",          // warn about use of deprecated APIs
  "-unchecked",            // warn about unchecked type parameters
  "-feature",              // warn about misused language features
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  "-Ypartial-unification", // allow the compiler to unify type constructors of different arities
  "-language:implicitConversions"
)

lazy val commonSettings = Seq(
  scalacOptions := compilerOptions,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val assemblySettings = Seq(
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false),
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)

addCompilerPlugin("org.spire-math"  %% "kind-projector" % "0.9.3")
addCompilerPlugin("org.scalamacros" % "paradise"        % "2.1.0" cross CrossVersion.full)

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
//testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
