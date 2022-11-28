package me.vldf.colors.controllers.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import me.vldf.colors.controllers.api.exceptions.InvalidCountParameterException
import me.vldf.colors.controllers.api.exceptions.InvalidFormatParameterException
import me.vldf.colors.models.FormattableColors
import me.vldf.colors.models.ReturnColorFormat
import me.vldf.colors.services.ImageProcessor
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

@ExperimentalSerializationApi
fun Route.getThemeRoute() {
    val defaultColorsCount = 5

    put("getTheme/{format?}") {
        val colorsCount = call.validateAndGetColorsCount(defaultColorsCount)
        val colorFormat = call.validateAndGetFormat(ReturnColorFormat.TRIPLE_255)

        val imageBytes = call.receive<ByteArray>()
        val image = withContext(Dispatchers.IO) {
            ImageIO.read(ByteArrayInputStream(imageBytes))
        }

        val dominantColors = ImageProcessor.getImageDominantColors(image, colorsCount)
        call.respond(FormattableColors.getModel(colorFormat, dominantColors))
    }

    put("getTheme/url/{format?}") {
        val colorsCount = call.validateAndGetColorsCount(defaultColorsCount)
        val colorFormat = call.validateAndGetFormat(ReturnColorFormat.TRIPLE_255)

        val imageUrlString = call.parameters.getOrFail("url")
        val imageUrl = Url(imageUrlString)

        val dominantColors = ImageProcessor.getImageDominantColors(imageUrl, colorsCount)
        call.respond(FormattableColors.getModel(colorFormat, dominantColors))
    }
}

private fun ApplicationCall.validateAndGetColorsCount(default: Int): Int {
    val countString = this.parameters["count"] ?: return default

    return countString.toIntOrNull() ?: throw InvalidCountParameterException(countString)
}

private fun ApplicationCall.validateAndGetFormat(default: ReturnColorFormat): ReturnColorFormat {
    val format = this.parameters["format"] ?: return default

    return ReturnColorFormat.parse(format) ?: throw InvalidFormatParameterException(format)
}
