package backend.user

import backend.CustomPrincipal
import backend.ViewRegisteredWorkshops
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.userRoutes(userRepository: UserRepository) {
    authenticate("basic") {
        get("/user/workshop") {
            // Should be based on the logged in user
            val userId = call.authentication.principal<CustomPrincipal>()?.userId!!
            val workshops = ViewRegisteredWorkshops(userRepository).viewRegisteredWorkshops(userId)
            call.respond(workshops)
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
