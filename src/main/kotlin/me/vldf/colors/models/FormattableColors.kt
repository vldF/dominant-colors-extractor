package me.vldf.colors.models

import com.github.ajalt.colormath.model.RGB
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import me.vldf.colors.serializers.Rgb255ColorSerializer
import me.vldf.colors.serializers.RgbHexColorSerializer


interface FormattableColors {
    val result: Array<@Contextual RGB>

    companion object {
        @ExperimentalSerializationApi
        fun getModel(colorsFormat: ReturnColorFormat, result: Array<RGB>): FormattableColors {
            return when (colorsFormat) {
                ReturnColorFormat.TRIPLE_255 -> Rgb255Colors(result)
                ReturnColorFormat.HEX -> RgbHexColors(result)
            }
        }
    }
}

@ExperimentalSerializationApi
@Suppress("ArrayInDataClass")
@Serializable
data class Rgb255Colors (
    override val result: Array<@Serializable(Rgb255ColorSerializer::class) RGB>
) : FormattableColors

@ExperimentalSerializationApi
@Suppress("ArrayInDataClass")
@Serializable
data class RgbHexColors (
    override val result: Array<@Serializable(RgbHexColorSerializer::class) RGB>
) : FormattableColors
