package gatling

import scala.sys.process._

object CommandsSupport {
  val error: String = "Error!"

  val proc: String = (Seq("ps", "-ef")
    #| Seq("grep", "java")
    #| Seq("grep", "PreserveFramePointer")
    #| Seq("grep", "-v", "sbt.ForkMain")
    #| Seq("grep", "-v", "grep")
    #|| Seq("echo", error)).!!
}
