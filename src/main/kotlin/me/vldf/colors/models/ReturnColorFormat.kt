package me.vldf.colors.models

import io.ktor.util.*

enum class ReturnColorFormat(private val stringName: String) {
    TRIPLE_255("rgb255"), HEX("hex");

    companion object {
        fun parse(value: String): ReturnColorFormat? {
            val cleanValue = value.toLowerCasePreservingASCIIRules()

            return ReturnColorFormat.values().firstOrNull { v -> v.stringName == cleanValue }
        }
    }
}