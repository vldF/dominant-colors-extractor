package me.vldf.colors.controllers.api.tests

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import me.vldf.colors.configureApiRoutes
import me.vldf.colors.statuspages.configure
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalSerializationApi
class Tests {
    @Test
    fun `get colors by URL`() = testApplication {
        this.application {
            initializeTests()
        }

        val url = "https://github.com/vldF/dominant-colors-extractor/raw/master/img/example2.png"
        val actualResponse = client.put("api/getTheme/url") {
            this.parameter("url", url)
        }

        val expectedResponse = """
            {"result":[{"r":0,"g":0,"b":0},{"r":247,"g":136,"b":11},{"r":120,"g":113,"b":226},{"r":64,"g":131,"b":220},{"r":240,"g":128,"b":39}]}
        """.trimIndent()
        assertEquals(expectedResponse, actualResponse.bodyAsText())
    }

    @Test
    fun `get colors by URL with color count`() = testApplication {
        this.application {
            initializeTests()
        }

        val url = "https://github.com/vldF/dominant-colors-extractor/raw/master/img/example2.png"
        val colorsCount = 4
        val actualResponse = client.put("api/getTheme/url") {
            this.parameter("url", url)
            this.parameter("count", colorsCount)
        }

        val expectedResponse = """
            {"result":[{"r":0,"g":0,"b":0},{"r":246,"g":135,"b":17},{"r":64,"g":131,"b":220},{"r":120,"g":113,"b":226}]}
        """.trimIndent()
        assertEquals(expectedResponse, actualResponse.bodyAsText())
    }

    private fun Application.initializeTests() {
        install(Routing)
        install(ContentNegotiation) {
            json()
        }
        install(StatusPages) {
            configure()
        }

        configureApiRoutes()
    }
}
