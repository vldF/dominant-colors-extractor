package me.vldf.colors.serializers

import com.github.ajalt.colormath.RenderCondition
import com.github.ajalt.colormath.model.RGB
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure


object RgbHexColorSerializer : KSerializer<RGB> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Color") {
        this.element("code", PrimitiveSerialDescriptor("code", PrimitiveKind.STRING))
    }

    override fun deserialize(decoder: Decoder): RGB {
        return RGB(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: RGB) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.toHex(renderAlpha = RenderCondition.NEVER))
        }
    }
}
