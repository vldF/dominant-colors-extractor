package me.vldf.colors

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import me.vldf.colors.controllers.api.getThemeRoute

@OptIn(ExperimentalSerializationApi::class)
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Routing)
        install(ContentNegotiation) {
            json()
        }

        configureApiRoutes()
    }.start(wait = true)
}

@ExperimentalSerializationApi
private fun Application.configureApiRoutes() {
    routing {
        route("api") {
            getThemeRoute()
        }
    }
}