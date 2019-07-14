package gatling.simulations.Stuff

import io.gatling.http.Predef._
import io.gatling.core.Predef._

object TestSim extends Simulation {

  val testScn = scenario("foo load test")
    .exec(http("Healthcheck").get("/actuator/health"))
}
