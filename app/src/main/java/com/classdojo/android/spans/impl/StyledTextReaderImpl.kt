package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.StyledTextReader
import com.classdojo.android.spans.interfaces.StyleMarker

class StyledTextReaderImpl(private val text: String) : StyledTextReader {
    override fun fullText(): String {
        return text
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return emptyList()
    }
}