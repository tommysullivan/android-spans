package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class ConvertibleToSpannableStringImpl(
    private val spannableStringFactory: SpannableStringFactory,
    private val styledTextReader: StyledTextReader
) : ConvertibleToSpannableString
{
    override fun asSpannableString(): SpannableString {
        val spannableString = spannableStringFactory.newSpannableString(styledTextReader.fullText())
        return styledTextReader.styleMarkersFromOutermostToInnermost().fold(spannableString) {
            spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
        }
    }
}