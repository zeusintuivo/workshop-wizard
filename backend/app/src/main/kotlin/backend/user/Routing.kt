package backend.user

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*



fun Application.configureUserRoutes() {
    routing {
        userRoutes(Repository())
    }
}

fun Routing.userRoutes(userRepository: Repository) {
    get("/user/workshop") {
        // Should be based on the logged in user
        call.respond(userRepository.getWorkShopRegistrations(1))
    }

    post("/user/workshop/{workshopId}") {
        try {
            userRepository.addWorkshopRegistrations(1, call.parameters["workshopId"]!!.toInt())
            call.respondText("Workshop added")
        } catch (e: Exception) {
            call.respondText("Workshop not found", status = io.ktor.http.HttpStatusCode.NotFound)
        }
    }

    put("/user/workshop/{workshopId}/cancel") {
        userRepository.cancelWorkshopRegistration(1, call.parameters["workshopId"]!!.toInt())
        call.respondText("Workshop cancelled")
    }
}
