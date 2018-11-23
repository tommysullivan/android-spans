package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.ConvertibleToSpannableString
import com.classdojo.android.spans.interfaces.SpanReader
import com.classdojo.android.spans.interfaces.StyleMarker
import com.classdojo.android.spans.interfaces.StyledTextReader

class SpanReaderImpl(
    private val convertibleToSpannableString: ConvertibleToSpannableString,
    private val nodeReaderBasic: StyledTextReader
) : SpanReader {

    override fun asSpannableString() = convertibleToSpannableString.asSpannableString()
    override fun fullText() = nodeReaderBasic.fullText()
    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return nodeReaderBasic.styleMarkersFromOutermostToInnermost()
    }
}