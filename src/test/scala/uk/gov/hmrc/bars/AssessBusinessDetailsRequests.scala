package uk.gov.hmrc.bars

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object AssessBusinessDetailsRequests extends ServicesConfiguration {

  val baseUrl = s"${baseUrlFor("bank-account-reputation")}/bank-account-reputation"
  val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  val assessBusinessBankDetails: HttpRequestBuilder = {
    http("Submit sort code, account number, name and post code")
      .post(s"$baseUrl/business/v2/assess": String)
      .header("Content-Type", "application/json")
      .body(StringBody(
        """|{
           |  "account": {
           |    "sortCode": "${sortcode}",
           |    "accountNumber": "${accountno}"
           |  },
           |  "business": {
           |    "companyName": "${name}",
           |    "companyRegistrationNumber": "UK27318156",
           |    "address": {
           |      "lines": ["22303 Darwin Turnpike"],
           |      "postcode": "${postcode}"
           |    }
           |  }
           |}
           |""".stripMargin)).asJSON
      .check(substring(""""accountExists":"${accountexists}""""))
      .check(status.is(200))
  }
}
