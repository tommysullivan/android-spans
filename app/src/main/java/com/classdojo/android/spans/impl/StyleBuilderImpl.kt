package com.classdojo.android.spans.impl

import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import com.classdojo.android.spans.interfaces.*

class StyleBuilderImpl(
    private val styleObjects: List<Any>,
    private val stylesFactory: StylesFactory
) : StyleBuilder, StyleReader, Styles {

    override val hasColor: StyleBuilder.Color by lazy {
        ColorImpl { color: Int -> this.hasColor(color) }
    }

    class CustomClickableSpan(private val clickHandler:() -> Unit) : ClickableSpan() {
        override fun onClick(widget: View) {
            clickHandler()
        }
    }

    override fun onClick(clickHandler:() -> Unit):StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(CustomClickableSpan(clickHandler))
    }

    override fun hasColor(@ColorInt color: Int?): StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(ForegroundColorSpan(color!!))
    }

    override fun sizeRelative(@FloatRange(from = 0.0) size: Float?): StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(RelativeSizeSpan(size!!))
    }

    private fun newStyleBuilderWithAdditionalStyle(additionalStyle: Any): StyleBuilder {
        return stylesFactory.styleBuilder(styleObjects + additionalStyle)
    }

    override val sizeRelative: StyleBuilder.Size by lazy {
        SizeImpl(
            { size: Float -> this.sizeRelative(size) },
            0.4f,
            0.8f,
            1.0f,
            1.5f,
            2.0f
        )
    }

    override fun applyStyleToSpannable(
        spannableString: SpannableString,
        startIndex: Int,
        endIndex: Int
    ): SpannableString {
        return styleObjects.fold(spannableString) { styledSpannableSoFar, currentStyleObject ->
            styledSpannableSoFar.setSpan(currentStyleObject, startIndex, endIndex, 0)
            styledSpannableSoFar
        }
    }

    override fun sizeAbsolute(size: Int?): StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(AbsoluteSizeSpan(size!!))
    }

    override val sizeAbsolute: StyleBuilder.Size by lazy {
        SizeImpl(
            { size: Int -> this.sizeAbsolute(size) },
            12,
            18,
            24,
            30,
            48
        )
    }

    override fun toString():String {
        return "StyleBuilderImpl styleObjects=${styleObjects}"
    }
}