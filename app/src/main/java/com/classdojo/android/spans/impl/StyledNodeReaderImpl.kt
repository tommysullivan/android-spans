package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.StyleMarker
import com.classdojo.android.spans.interfaces.StyleMarkerFactory
import com.classdojo.android.spans.interfaces.StyleReader
import com.classdojo.android.spans.interfaces.StyledTextReader

class StyledNodeReaderImpl (
    private val styleMarkerFactory: StyleMarkerFactory,
    private val styledNode: StyledTextReader,
    private val styleReader: StyleReader
) : StyledTextReader {
    override fun fullText(): String {
        return styledNode.fullText()
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        val styleMarker = styleMarkerFactory.newStyleMarker(0, fullText().length, styleReader)
        return listOf(styleMarker) + styledNode.styleMarkersFromOutermostToInnermost()
    }
}