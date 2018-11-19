package com.classdojo.android.immutable.spannable

import android.graphics.Color;

class ColorImpl(private val newStyleBuilderWithColor: (Int) -> StyleBuilder) : StyleBuilder.Color {

    override fun red(): StyleBuilder {
        return newStyleBuilderWithColor(Color.RED)
    }

    override fun green(): StyleBuilder {
        return newStyleBuilderWithColor(Color.GREEN)
    }

    override fun blue(): StyleBuilder {
        return newStyleBuilderWithColor(Color.BLUE)
    }

    override fun yellow(): StyleBuilder {
        return newStyleBuilderWithColor(Color.YELLOW)
    }

    override fun black(): StyleBuilder {
        return newStyleBuilderWithColor(Color.BLACK)
    }

    override fun gray(): StyleBuilder {
        return newStyleBuilderWithColor(Color.GRAY)
    }
}