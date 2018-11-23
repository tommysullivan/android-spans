package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.ConvertibleToSpannableString
import com.classdojo.android.spans.interfaces.SpannableString
import com.classdojo.android.spans.interfaces.SpannableStringFactory
import com.classdojo.android.spans.interfaces.StyledTextReader

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