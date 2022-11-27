package me.vldf.colors.serializers

import com.github.ajalt.colormath.model.RGB
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure

@ExperimentalSerializationApi
@Serializer(RGB::class)
object Rgb255ColorSerializer : KSerializer<RGB> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Color") {
        this.element("r", PrimitiveSerialDescriptor("red", PrimitiveKind.INT))
        this.element("g", PrimitiveSerialDescriptor("green", PrimitiveKind.INT))
        this.element("b", PrimitiveSerialDescriptor("blue", PrimitiveKind.INT))
    }

    override fun deserialize(decoder: Decoder): RGB {
        return RGB.from255(decoder.decodeInt(), decoder.decodeInt(), decoder.decodeInt())
    }

    override fun serialize(encoder: Encoder, value: RGB) {
        encoder.encodeStructure(descriptor) {
            encodeIntElement(descriptor, 0, value.redInt)
            encodeIntElement(descriptor, 1, value.greenInt)
            encodeIntElement(descriptor, 2, value.blueInt)
        }
    }
}