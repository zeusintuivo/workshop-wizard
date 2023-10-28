package backend.user

import backend.CustomPrincipal
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureUserRoutes() {
    routing {
        userRoutes(Repository())
    }
}

fun Routing.userRoutes(userRepository: Repository) {
    authenticate ("basic") {
        get("/user/workshop") {
            // Should be based on the logged in user
            val userId = call.authentication.principal<CustomPrincipal>()?.userId!!
            call.respond(userRepository.getWorkShopRegistrations(userId))
        }

        post("/user/workshop/{workshopId}") {
            try {
                val userId = call.authentication.principal<CustomPrincipal>()?.userId!!
                userRepository.addWorkshopRegistrations(userId, call.parameters["workshopId"]!!.toInt())
                call.respondText("Workshop added")
            } catch (e: Exception) {
                call.respondText("Workshop not found", status = io.ktor.http.HttpStatusCode.NotFound)
            }
        }

        put("/user/workshop/{workshopId}/cancel") {
            val userId = call.authentication.principal<CustomPrincipal>()?.userId!!
            userRepository.cancelWorkshopRegistration(userId, call.parameters["workshopId"]!!.toInt())
            call.respondText("Workshop cancelled")
        }
    }
}
