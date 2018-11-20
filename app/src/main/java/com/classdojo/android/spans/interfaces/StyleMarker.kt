package com.classdojo.android.spans.interfaces

interface StyleMarker {
    fun move(amount: Int): StyleMarker
    fun applyStyle(spannableString: SpannableString): SpannableString
}

