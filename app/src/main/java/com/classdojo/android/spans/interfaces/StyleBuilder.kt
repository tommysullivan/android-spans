package com.classdojo.android.spans.interfaces

import android.support.annotation.ColorInt
import android.support.annotation.FloatRange

interface StyleBuilder : StyleReader {
    val color:Color
    fun color(@ColorInt color: Int?): StyleBuilder

    interface Color {
        val red: StyleBuilder
        val green: StyleBuilder
        val blue: StyleBuilder
        val yellow: StyleBuilder
        val black: StyleBuilder
        val gray: StyleBuilder
    }

    fun sizeRelative(@FloatRange(from = 0.0) size: Float?): StyleBuilder
    val sizeRelative: Size

    fun sizeAbsolute(size: Int?): StyleBuilder
    val sizeAbsolute: Size

    interface Size {
        val tiny: StyleBuilder
        val small: StyleBuilder
        val normal: StyleBuilder
        val large: StyleBuilder
        val huge: StyleBuilder
    }

    fun onClick(clickHandler:() -> Unit):StyleBuilder
}