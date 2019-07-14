package gatling.simulations.UserSpecificScenarios

import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.language.postfixOps

import scala.concurrent.duration._

object MassUserRetrievalSimulation extends Simulation {

  val testTimeSecs = 5
  val minWaitMs: FiniteDuration = 100 milliseconds
  val maxWaitMs: FiniteDuration = 300 milliseconds

  val baseName = "loanapi-userdata-retrieval"
  val requestName: String = baseName + "-request"
  val scenarioName: String = baseName + "-scenario"
  val URI = "/api/v1/user"

  val registrationScn: ScenarioBuilder = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .get(URI)
          .check(status.is(200))
      ).pause(minWaitMs, maxWaitMs)
    }
}
