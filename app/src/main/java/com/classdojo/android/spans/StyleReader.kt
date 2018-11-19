package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableString

interface StyleReader {
    fun applyStyleToSpannable(spannableString: SpannableString, startIndex: Int, EndIndex: Int): SpannableString
}
