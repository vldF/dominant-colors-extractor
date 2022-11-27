package me.vldf.colors.controllers.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.vldf.colors.services.ImageProcessor
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

fun Route.getThemeRoute() {
    put("getTheme") {
        val imageBytes = call.receive<ByteArray>()
        val image = withContext(Dispatchers.IO) {
            ImageIO.read(ByteArrayInputStream(imageBytes))
        }

        val dominantColors = ImageProcessor.getImageDominantColors(image)
        call.respond(dominantColors)
    }

    put("getTheme/url") {
        val imageUrlString = call.parameters.getOrFail("url")
        val imageUrl = Url(imageUrlString)

        val dominantColors = ImageProcessor.getImageDominantColors(imageUrl)
        call.respond(dominantColors)
    }
}
