package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.StyleMarker
import com.classdojo.android.spans.interfaces.StyleMarkerFactory
import com.classdojo.android.spans.interfaces.StyleReader

class StyleMarkerImpl(
    private val startIndex: Int,
    private val length: Int,
    private val styleReader: StyleReader,
    private val styleMarkerFactory: StyleMarkerFactory
) : StyleMarker {

    override fun applyStyle(spannableString: SpannableString): SpannableString {
        return styleReader.applyStyleToSpannable(spannableString, startIndex, this.startIndex + this.length)
    }

    override fun move(amount: Int): StyleMarker {
        return styleMarkerFactory.newStyleMarker(startIndex + amount, length, styleReader)
    }

    override fun toString():String {
        return "StyleMarkerImpl startIndex=${startIndex} length=${length} styleReader=${styleReader}"
    }

}