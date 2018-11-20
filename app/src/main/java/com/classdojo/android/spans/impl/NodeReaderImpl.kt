package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class NodeReaderImpl(
    private val spannableStringReader: SpannableStringReader,
    private val nodeReaderBasic: NodeReaderBasic
) : NodeReader {

    override fun asSpannableString() = spannableStringReader.asSpannableString()
    override fun fullText() = nodeReaderBasic.fullText()
    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return nodeReaderBasic.styleMarkersFromOutermostToInnermost()
    }
}