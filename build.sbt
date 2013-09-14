name := "BabbleHub-unstable"

version := "0.5.6"

libraryDependencies ++= Seq(
  "commons-configuration" % "commons-configuration" % "20041012.002804",
  "com.google.guava" % "guava" % "14.0.1",
  "redis.clients" % "jedis" % "2.1.0",
  "log4j" % "log4j" % "1.2.17",
  "io.netty" % "netty" % "3.6.6.Final")
