enablePlugins(SbtPlugin, ScriptedPlugin, BuildInfoPlugin)

organization := "com.github.tonykoval"
name := "sbt-nifi-nar"

homepage := Some(url("https://github.com/tonykoval/sbt-nifi-nar"))
licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
publishTo in ThisBuild := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}
scmInfo := Some(
  ScmInfo(
    url("https://github.com/tonykoval/sbt-nifi-nar"),
    "scm:git:git@github.com/tonykoval/sbt-nifi-nar.git"
  )
)

sbtPlugin := true

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.10"

scriptedBufferLog := false
scriptedLaunchOpts := { scriptedLaunchOpts.value ++
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value, "-Dplugin.name=" + name.value)
}

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-compress" % "1.19"
)

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)
