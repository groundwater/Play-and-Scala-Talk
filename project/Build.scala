import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "My Snappy App"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
        
        // For example
    	// "org.apache.hadoop" % "hadoop-core" % "1.0.2",
    	// "org.apache.hbase"  % "hbase"       % "0.92.1"

    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings()

}

