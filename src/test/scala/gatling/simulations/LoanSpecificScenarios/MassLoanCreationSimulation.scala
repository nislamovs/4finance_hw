package gatling.simulations.LoanSpecificScenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import net.liftweb.json.Serialization.write
import net.liftweb.json._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

object MassLoanCreationSimulation extends Simulation {

  val testTimeSecs = 5
  val minWaitMs: FiniteDuration = 100 milliseconds
  val maxWaitMs: FiniteDuration = 300 milliseconds

  val baseName = "loanapi-loan-creation"
  val requestName: String = baseName + "-request"
  val scenarioName: String = baseName + "-scenario"
  val URI = "/api/v1/loan"

  def genIP(r: Random): String = {
    r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256)
  }

  case class Loan(userId: Long, loanAmount: Integer, loanTerm: Integer, ipAddress: String)
  implicit val formats: DefaultFormats.type = DefaultFormats

  val r: Random.type = Random
  val id: Long = (r.nextInt(5) + 1).toLong
  val ip: String = genIP(r)
  val loanAmount = 100000
  val loanTerm = 100

  val loanAsJson: String = write(Loan(id, loanAmount, loanTerm, ip))

  val createLoanScn: ScenarioBuilder = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .post(URI)
          .body(StringBody(loanAsJson.stripMargin))
          .check(status.is(201))
      ).pause(minWaitMs, maxWaitMs)
    }
}
