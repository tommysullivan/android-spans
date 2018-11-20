package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpannableStringReaderImpl(
    private val spannableStringFactory: SpannableStringFactory,
    private val nodeReaderBasic: NodeReaderBasic
) : SpannableStringReader
{
    override fun asSpannableString(): SpannableString {
        val spannableString = spannableStringFactory.newSpannableString(nodeReaderBasic.fullText())
        return nodeReaderBasic.styleMarkersFromOutermostToInnermost().fold(spannableString) {
            spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
        }
    }
}