package backend.admin

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.adminRoutes() {
    get("/admin") {
        call.respondText("Hello, world!")
    }
}
