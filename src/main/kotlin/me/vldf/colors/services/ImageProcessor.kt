package me.vldf.colors.services

import com.github.ajalt.colormath.model.LAB
import com.github.ajalt.colormath.model.RGB
import io.ktor.http.*
import kotlinx.coroutines.*
import smile.clustering.KMeans
import smile.clustering.PartitionClustering
import java.awt.image.BufferedImage


object ImageProcessor {
    /**
     * This function processes an [image] via K-Means algorithm to distribute all pixels over clusters.
     *
     * Each color converts to LAB color format that is more suitable for this task.
     *
     * @param: [image] represents an image
     * @param [colorsCount] sets required count of colors
     * @return: an array with length [colorsCount] of RGB colors
     */
    suspend fun getImageDominantColors(image: BufferedImage, colorsCount: Int = 5): Array<RGB> {
        val colors = Array(image.width * image.height + 1) { DoubleArray(3) }

        @OptIn(ExperimentalStdlibApi::class)
        for (x in 0..<image.width) {
            for (y in 0..<image.height) {
                val rgbCode = image.getRGB(x, y)
                val rgb = ColorUtils.parseFromInt(rgbCode)
                val lab = rgb.toLAB()

                colors[(x+1)*(y+1)] = doubleArrayOf(lab.l.toDouble(), lab.a.toDouble(), lab.b.toDouble())
            }
        }

        val clusters = CoroutineScope(Dispatchers.Default).async {
            PartitionClustering.run(10) {
                KMeans.fit(colors, colorsCount)
            }
        }

        return clusters
            .await()
            .centroids
            .map { (l, a, b) -> LAB(l, a, b) }
            .map { lab -> lab.toSRGB() }
            .toTypedArray()
    }

    /**
     * This function gets an image by [imageUrl] then processes it via K-Means algorithm to distribute all pixels
     * over clusters.
     *
     * Each color converts to LAB color format that is more suitable for this task.
     *
     * @param: [imageUrl] sets url of the image
     * @param [colorsCount] sets required count of colors
     * @return: an array with length [colorsCount] of RGB colors
     */
    suspend fun getImageDominantColors(imageUrl: Url, colorsCount: Int = 5): Array<RGB> {
        val image = ImageDownloaderHelper.downloadImage(imageUrl)

        return getImageDominantColors(image, colorsCount)
    }
}
