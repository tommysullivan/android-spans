package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableString

interface NodeReader {
    fun fullText(): String
    fun styleMarkersFromOutermostToInnermost(): List<StyleMarker>
    fun asSpannableString(): SpannableString
}