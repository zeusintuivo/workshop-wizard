package backend.user

import backend.createServer
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class RoutingKtTest {

    @Test
    fun testGetUserWorkshop() = testApplication {
        application {
            createServer()
        }
        client.get("/user/workshop").apply {
            Assertions.assertEquals(HttpStatusCode.OK, HttpResponseData.resqponse.status())
        }
    }
}