package gatling.simulations

import gatling.simulations.ExtentionsSpecificScenarios.{MassExtentionCreationSimulation, MassExtentionRetrievalByIdSimulation, MassExtentionRetrievalSimulation}
import gatling.simulations.LoanSpecificScenarios.{MassLoanCreationSimulation, MassLoanRetrievalByIdSimulation, MassLoanRetrievalSimulation, MassLoanStatusChangeSimulation}
import gatling.simulations.Other.MassHealtcheckSimulation
import gatling.simulations.UserSpecificScenarios.{MassUserRegistrationSimulation, MassUserRetrievalByIdSimulation, MassUserRetrievalSimulation}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.io.Source._
import scala.util.control.Breaks._

class GatlingSimulations extends Simulation {

  var serverUp = false
  val rampUpTimeSecs = 1
  val noOfUsers = 10

  val baseURL: String = "http://localhost:8081"
  val healthEndpoint: String = baseURL + "/actuator/health"


  def get(url: String) = fromURL(url).mkString

  breakable {
    for (retryCount <- 10 to 1 by -1) {
      val response = get(healthEndpoint)
      if (!response.contains("UP")) {
        if (retryCount <= 1) {
          println("Server [" + healthEndpoint + "]" + "is down!")
        }
        println("Server is not responding! Retries left : " + retryCount)
        Thread.sleep(1000)
      }
      println("Server is UP! Testing ...")
      serverUp = true
      break
    }
  }

  if (serverUp) {

    val httpConf: HttpProtocolBuilder = http
      .baseURL(baseURL)
      .acceptHeader("application/json")
      .contentTypeHeader("application/json")
      .doNotTrackHeader("1")

    setUp(

      //Users
      MassUserRegistrationSimulation.registrationScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassUserRetrievalSimulation.retrievingScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassUserRetrievalByIdSimulation.retrievingByIdScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),

      //Loans
      MassLoanCreationSimulation.createLoanScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassLoanRetrievalSimulation.retrievingScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassLoanRetrievalByIdSimulation.retrievingByIdScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassLoanStatusChangeSimulation.changeLoanStatusScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),

      //Extentions
      MassExtentionCreationSimulation.createExtentionScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassExtentionRetrievalSimulation.retrievingScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
      MassExtentionRetrievalByIdSimulation.retrievingByIdScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),

      //Other
      MassHealtcheckSimulation.healhcheckScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),

    ).protocols(httpConf)
  }
}
