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
    get("/admin") {
        call.respondText("Hello, world!")
    }
}
