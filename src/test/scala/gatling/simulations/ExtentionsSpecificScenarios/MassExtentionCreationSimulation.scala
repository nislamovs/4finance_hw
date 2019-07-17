package gatling.simulations.ExtentionsSpecificScenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import net.liftweb.json.Serialization.write
import net.liftweb.json._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

object MassExtentionCreationSimulation extends Simulation {

  val testTimeSecs = 5
  val minWaitMs: FiniteDuration = 100 milliseconds
  val maxWaitMs: FiniteDuration = 300 milliseconds

  val baseName = "loanapi-extention-creation"
  val requestName: String = baseName + "-request"
  val scenarioName: String = baseName + "-scenario"
  val URI = "/api/v1/extention"

  case class Extention(loanId: Long, extentionDays: Integer)
  implicit val formats: DefaultFormats.type = DefaultFormats

  val r: Random.type = Random
  val id: Long = (r.nextInt(5) + 1).toLong
  val extentionDays = 100

  val extentionAsJson: String = write(Extention(id, extentionDays))

  val createExtentionScn: ScenarioBuilder = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .post(URI)
          .body(StringBody(extentionAsJson.stripMargin))
          .check(status.is(201))
      ).pause(minWaitMs, maxWaitMs)
    }
}
