package me.vldf.colors

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import me.vldf.colors.controllers.api.getThemeRoute
import me.vldf.colors.statuspages.configure

@OptIn(ExperimentalSerializationApi::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Routing)
        install(ContentNegotiation) {
            json()
        }
        install(StatusPages) {
            configure()
        }

        configureApiRoutes()
    }.start(wait = true)
}

@ExperimentalSerializationApi
internal fun Application.configureApiRoutes() {
    routing {
        route("api") {
            getThemeRoute()
        }
    }
}
