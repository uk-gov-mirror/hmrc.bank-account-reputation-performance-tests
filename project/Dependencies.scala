import sbt._

object Dependencies {

  val test = Seq(
    "com.typesafe" % "config" % "1.4.0" % Test,
    "uk.gov.hmrc" %% "performance-test-runner" % "3.7.0" % Test,
    "io.gatling" % "gatling-test-framework" % "2.3.1" % Test,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.1" % Test
  )
}
