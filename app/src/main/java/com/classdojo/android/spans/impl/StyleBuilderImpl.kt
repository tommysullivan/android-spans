package com.classdojo.android.spans.impl

import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.StyleBuilder
import com.classdojo.android.spans.interfaces.StyleBuilderFactory
import com.classdojo.android.spans.interfaces.StyleReader

class StyleBuilderImpl(
    private val styleObjects: List<Any>,
    private val styleBuilderFactory: StyleBuilderFactory
) : StyleBuilder, StyleReader {

    override fun color(): StyleBuilder.Color {
        return ColorImpl { color: Int -> this.color(color) }
    }

    class CustomClickableSpan(private val clickHandler:() -> Unit) : ClickableSpan() {
        override fun onClick(widget: View) {
            clickHandler()
        }
    }

    override fun onClick(clickHandler:() -> Unit):StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(CustomClickableSpan(clickHandler))
    }

    override fun color(@ColorInt color: Int?): StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(ForegroundColorSpan(color!!))
    }

    override fun sizeRelative(@FloatRange(from = 0.0) size: Float?): StyleBuilder {
        return newStyleBuilderWithAdditionalStyle(RelativeSizeSpan(size!!))
    }

    private fun newStyleBuilderWithAdditionalStyle(additionalStyle: Any): StyleBuilder {
        return styleBuilderFactory.newStyleBuilder(styleObjects + additionalStyle)
    }

    override fun sizeRelative(): StyleBuilder.Size {
        return SizeImpl(
            { size: Float -> this.sizeRelative(size) },
            0.4f,
            0.8f,
            1.0f,
            1.5f,
            2.0f
        )
    }

    override fun build(): StyleReader {
        return this
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

    override fun sizeAbsolute(): StyleBuilder.Size {
        return SizeImpl(
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