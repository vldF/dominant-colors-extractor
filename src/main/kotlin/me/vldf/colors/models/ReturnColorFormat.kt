package me.vldf.colors.models

import io.ktor.util.*

enum class ReturnColorFormat(private val stringName: String) {
    TRIPLE_255("rgb255"), HEX("hex");

    companion object {
        fun parse(value: String?): ReturnColorFormat {
            return if (value == null) {
                TRIPLE_255
            } else {
                val cleanValue = value.toLowerCasePreservingASCIIRules()

                ReturnColorFormat.values().firstOrNull { v -> v.stringName == cleanValue }!!
            }
        }
    }
}