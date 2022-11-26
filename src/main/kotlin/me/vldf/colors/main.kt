package me.vldf.colors

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import me.vldf.colors.controllers.api.getThemeRoute
import me.vldf.colors.serializers.ColorSerializer

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Routing)
        install(ContentNegotiation) {
            json(Json {
                this.serializersModule = SerializersModule {
                    this.contextual(ColorSerializer)
                }
            })
        }

        configureApiRoutes()
    }.start(wait = true)
}

private fun Application.configureApiRoutes() {
    routing {
        route("api") {
            getThemeRoute()
        }
    }
}