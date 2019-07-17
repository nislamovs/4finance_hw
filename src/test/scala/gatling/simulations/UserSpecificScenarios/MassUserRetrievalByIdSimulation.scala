package gatling.simulations.UserSpecificScenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

object MassUserRetrievalByIdSimulation extends Simulation {

  val testTimeSecs = 5
  val minWaitMs: FiniteDuration = 100 milliseconds
  val maxWaitMs: FiniteDuration = 300 milliseconds

  val baseName = "loanapi-userdata-retrieval-by-id"
  val requestName: String = baseName + "-request"
  val scenarioName: String = baseName + "-scenario"
  val URI = "/api/v1/user"

  val r: Random.type = scala.util.Random
  val userId: String = (r.nextInt(5) + 1).toString

  val retrievingByIdScn: ScenarioBuilder = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .get(URI + "/" + userId)
          .check(status.is(200))
      ).pause(minWaitMs, maxWaitMs)
    }
}
