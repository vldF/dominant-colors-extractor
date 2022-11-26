package me.vldf.colors.controllers.api

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.vldf.colors.model.ImageProcessor
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
}
