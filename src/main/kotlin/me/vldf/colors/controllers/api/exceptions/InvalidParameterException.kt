package me.vldf.colors.controllers.api.exceptions

import kotlinx.serialization.Serializable
import me.vldf.colors.serializers.InvalidParameterExceptionSerializer

sealed class InvalidParameterException(parameterName: String, expected: String, actual: String)
    : BaseColorExtractorException("Invalid parameter value: $parameterName, expected: $expected, actual: $actual")

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(with = InvalidParameterExceptionSerializer::class)
class InvalidCountParameterException(actualValue: String)
    : InvalidParameterException("count", "value in range [3; 10]", actualValue)

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(with = InvalidParameterExceptionSerializer::class)
class InvalidFormatParameterException(actualValue: String)
    : InvalidParameterException("format", "\"rgb255\" or \"hex\"", actualValue)
