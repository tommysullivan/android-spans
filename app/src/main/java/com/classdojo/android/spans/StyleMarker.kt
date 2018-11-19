package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableString

interface StyleMarker {
    fun move(amount: Int): StyleMarker
    fun applyStyle(spannableString: SpannableString): SpannableString
}

