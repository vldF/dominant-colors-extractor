package me.vldf.colors.model

import com.github.ajalt.colormath.model.RGB

object ColorUtils {
    fun parseFromInt(value: Int): RGB {
        return RGB.from255(
            value.and(RgbConstants.RED_MASK).shr(8+8),
            value.and(RgbConstants.GREEN_MASK).shr(8),
            value.and(RgbConstants.BLUE_MASK)
        )
    }
}