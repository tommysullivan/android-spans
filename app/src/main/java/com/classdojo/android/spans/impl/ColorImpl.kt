package com.classdojo.android.spans.impl

import android.graphics.Color;
import com.classdojo.android.spans.interfaces.StyleBuilder

class ColorImpl(private val newStyleBuilderWithColor: (Int) -> StyleBuilder) : StyleBuilder.Color {

    override val red: StyleBuilder by lazy {
        newStyleBuilderWithColor(Color.RED)
    }

    override val green: StyleBuilder by lazy {
        newStyleBuilderWithColor(Color.GREEN)
    }

    override val blue: StyleBuilder by lazy {
        newStyleBuilderWithColor(Color.BLUE)
    }

    override val yellow: StyleBuilder by lazy {
        newStyleBuilderWithColor(Color.YELLOW)
    }

    override val black: StyleBuilder by lazy {
        newStyleBuilderWithColor(Color.BLACK)
    }

    override val gray: StyleBuilder by lazy {
        newStyleBuilderWithColor(Color.GRAY)
    }
}