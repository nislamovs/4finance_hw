package gatling.simulations.LoanSpecificScenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

object MassLoanStatusChangeSimulation extends Simulation {

  val testTimeSecs = 5
  val minWaitMs: FiniteDuration = 100 milliseconds
  val maxWaitMs: FiniteDuration = 300 milliseconds

  val baseName = "loanapi-loan-status-change"
  val requestName: String = baseName + "-request"
  val scenarioName: String = baseName + "-scenario"
  val URI = "/api/v1/loan"

  val r: Random.type = scala.util.Random

  val changeLoanStatusScn: ScenarioBuilder = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .put(URI + "/" + r.nextInt(5) + 1)
          .queryParam("status", "manual_check")
          .check(status.is(202))
      ).pause(minWaitMs, maxWaitMs)
    }
}
