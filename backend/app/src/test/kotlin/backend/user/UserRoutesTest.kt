import backend.user.UserRepository
import io.ktor.server.testing.*
import io.ktor.http.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.*
import org.mockito.Mockito.*

class UserRoutesTest {

    @Test
    fun testGetUserWorkshops() = testApplication {
        // Mock UserRepository or other dependencies
        val userRepository = mock<UserRepository>()
        // Set up behavior for the mock if necessary
        // whenever(userRepository.getWorkShopRegistrations(any())).thenReturn(...)

        // Configure and start your application with necessary mocks and settings
        application {
            // Your application setup here
            // e.g., configureRouting(), where you define your routes
        }

        // Perform the request and assert the result
        val call = handleRequest(HttpMethod.Get, "/user/workshop")
        assertEquals(HttpStatusCode.OK, call.response.status())
        // Additional assertions
    }

    @Test
    fun testPostUserWorkshop() = testApplication {
        // Mock data and setup

        handleRequest(HttpMethod.Post, "/user/workshop/1").apply {
            assertEquals(HttpStatusCode.OK, response.status())
            // Additional assertions
        }
    }

    @Test
    fun testCancelUserWorkshop() = testApplication {
        // Mock data and setup

        handleRequest(HttpMethod.Put, "/user/workshop/1/cancel").apply {
            assertEquals(HttpStatusCode.OK, response.status())
            // Additional assertions
        }
    }
}
