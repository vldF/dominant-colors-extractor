package me.vldf.colors.statuspages

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import me.vldf.colors.controllers.api.exceptions.InvalidParameterException
import org.slf4j.LoggerFactory

fun StatusPagesConfig.configure() {
    val logger = LoggerFactory.getLogger(this::class.java)
    exception<InvalidParameterException> { call, cause ->
        logger.info("error occurred", cause)
        call.respond(cause)
    }

    exception { call: ApplicationCall, cause: Exception ->
        logger.info("error occurred", cause)
        call.respond(HttpStatusCode.InternalServerError, message = cause.message ?: "")
    }
}
