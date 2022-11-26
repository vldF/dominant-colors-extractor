package me.vldf.colors.model

/**
 * Color is represent by 8*3=24 bits of color and 8 bits of alpha (ignored):
 * Alpha -- a
 * Red -- r
 * Green -- g
 * Blue -- b
 * (one letter -- one bit)
 * (aaaaaaaa)(rrrrrrrr)(gggggggg)(bbbbbbbb)
 */
object RgbConstants {
    const val RED_MASK = (255).shl(8+8)
    const val GREEN_MASK = (255).shl(8)
    const val BLUE_MASK = 255
}