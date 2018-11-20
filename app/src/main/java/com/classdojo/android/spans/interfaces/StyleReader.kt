package com.classdojo.android.spans.interfaces

interface StyleReader {
    fun applyStyleToSpannable(spannableString: SpannableString, startIndex: Int, EndIndex: Int): SpannableString
}
