package gatling.simulations

import gatling.simulations.Stuff.{MassHealtcheckSimulation, TestSim}
import gatling.simulations.UserSpecificScenarios.{MassUserRegistrationSimulation, MassUserRetrievalSimulation}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class GatlingSimulations extends Simulation {

  val rampUpTimeSecs = 1
  val noOfUsers = 10

  val baseURL = "http://localhost:8081"
  val httpConf: HttpProtocolBuilder = http
    .baseURL(baseURL)
    .acceptHeader("application/json")
    .doNotTrackHeader("1")

  setUp(

    //Stuff
    MassUserRegistrationSimulation.registrationScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
    MassUserRetrievalSimulation.registrationScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),

    //Users
    MassHealtcheckSimulation.healhcheckScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs),
    TestSim.testScn.inject(rampUsers(noOfUsers) over rampUpTimeSecs)
  ).protocols(httpConf)
}
