package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class ConvertibleToSpannableStringImpl(
    private val spannableStringFactory: SpannableStringFactory,
    private val nodeReaderBasic: StyledTextReader
) : ConvertibleToSpannableString
{
    override fun asSpannableString(): SpannableString {
        val spannableString = spannableStringFactory.newSpannableString(nodeReaderBasic.fullText())
        return nodeReaderBasic.styleMarkersFromOutermostToInnermost().fold(spannableString) {
            spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
        }
    }
}