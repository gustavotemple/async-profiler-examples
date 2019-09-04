package gatling

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object Runner {

  def main(args: Array[String]) {

    // This sets the class for the simulation we want to run.
    val simClass = classOf[SimulationTest].getName

    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)
    props.runDescription(s"$simClass Runner")
    props.resourcesDirectory("./src/main/scala")
    props.binariesDirectory("./target/scala-2.12/classes")
    props.simulationsDirectory("./src/test/scala/gatling")

    Gatling.fromMap(props.build)
  }
}