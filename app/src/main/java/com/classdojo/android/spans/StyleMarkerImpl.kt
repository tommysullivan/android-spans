package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableString

class StyleMarkerImpl(
    private val startIndex: Int,
    private val length: Int,
    private val styleBuilder: StyleBuilder,
    private val styleMarkerFactory: StyleMarkerFactory
) : StyleMarker {

    override fun applyStyle(spannableString: SpannableString): SpannableString {
        return styleBuilder.build().applyStyleToSpannable(spannableString, startIndex, this.startIndex + this.length)
    }

    override fun move(amount: Int): StyleMarker {
        return styleMarkerFactory.newStyleMarker(startIndex + amount, length, styleBuilder)
    }

}