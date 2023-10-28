package backend.admin

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureAdminRoutes() {
    routing {
        adminRoutes(Repository())
    }
}

fun Routing.adminRoutes(adminRepository: backend.admin.Repository) {
    get("/admin/workshop") {
        call.respond(adminRepository.getWorkshops())
    }
    get("/admin/workshop/{workshopId}") {
        try {
            call.respond(adminRepository.getWorkshopRegistrations(call.parameters["workshopId"]!!.toInt()))
        } catch (e: Exception) {
            call.respondText("Workshop not found", status = io.ktor.http.HttpStatusCode.NotFound)
        }
    }
}
