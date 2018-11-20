package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpannableStringReaderImpl(
    private val spannableStringFactory: SpannableStringFactory,
    private val nodeReader: NodeReader
) : SpannableStringReader
{
    override fun asSpannableString(): SpannableString {
        val spannableString = spannableStringFactory.newSpannableString(nodeReader.fullText())
        return nodeReader.styleMarkersFromOutermostToInnermost().fold(spannableString) {
            spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
        }
    }
}