package me.vldf.colors.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import me.vldf.colors.controllers.api.exceptions.InvalidParameterException

object InvalidParameterExceptionSerializer : KSerializer<InvalidParameterException> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        "exception"
    ) {
        element("error", PrimitiveSerialDescriptor("error", PrimitiveKind.BOOLEAN))
        element("message", PrimitiveSerialDescriptor("message", PrimitiveKind.STRING))
    }

    override fun deserialize(decoder: Decoder): InvalidParameterException {
        error("not implemented")
    }

    override fun serialize(encoder: Encoder, value: InvalidParameterException) {
        encoder.encodeStructure(descriptor) {
            encodeBooleanElement(descriptor, 0, true)
            encodeStringElement(descriptor, 1, value.message!!)
        }
    }
}
