package me.vldf.colors.services

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

object ImageDownloaderHelper {
    private val client = HttpClient(CIO)

    suspend fun downloadImage(url: Url): BufferedImage {
        val downloadResponse = client.get(url)

        val imageChannel =downloadResponse.bodyAsChannel()
        imageChannel.awaitContent()

        val image = withContext(Dispatchers.IO) {
            ImageIO.read(imageChannel.toInputStream())
        }

        return image
    }
}