package com.classdojo.android.spans.interfaces

import android.support.annotation.ColorInt
import android.support.annotation.FloatRange

interface StyleBuilder {
    fun color(): Color
    fun color(@ColorInt color: Int?): StyleBuilder

    interface Color {
        fun red(): StyleBuilder
        fun green(): StyleBuilder
        fun blue(): StyleBuilder
        fun yellow(): StyleBuilder
        fun black(): StyleBuilder
        fun gray(): StyleBuilder
    }

    fun sizeRelative(@FloatRange(from = 0.0) size: Float?): StyleBuilder
    fun sizeRelative(): Size

    fun sizeAbsolute(size: Int?): StyleBuilder
    fun sizeAbsolute(): Size

    interface Size {
        fun tiny(): StyleBuilder
        fun small(): StyleBuilder
        fun normal(): StyleBuilder
        fun large(): StyleBuilder
        fun huge(): StyleBuilder
    }

    fun onClick(clickHandler:() -> Unit):StyleBuilder

    fun build(): StyleReader
}