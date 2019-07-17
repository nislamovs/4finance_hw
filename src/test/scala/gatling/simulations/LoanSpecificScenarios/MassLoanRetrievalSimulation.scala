package gatling.simulations.LoanSpecificScenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

object MassLoanRetrievalSimulation extends Simulation {

  val testTimeSecs = 5
  val minWaitMs: FiniteDuration = 100 milliseconds
  val maxWaitMs: FiniteDuration = 300 milliseconds

  val baseName = "loanapi-loan-mass-retrieval"
  val requestName: String = baseName + "-request"
  val scenarioName: String = baseName + "-scenario"
  val URI = "/api/v1/loan"

  val retrievingScn: ScenarioBuilder = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .get(URI)
          .check(status.is(200))
      ).pause(minWaitMs, maxWaitMs)
    }
}
