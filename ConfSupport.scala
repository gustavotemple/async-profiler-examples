package gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder

object ConfSupport {
  private val baseUrl = sys.env.getOrElse("BASE_URL", "http://127.0.0.1:8080")

  val protocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .disableWarmUp
}
