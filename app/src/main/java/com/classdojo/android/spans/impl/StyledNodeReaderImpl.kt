package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class StyledNodeReaderImpl (
    private val styleMarkerFactory: StyleMarkerFactory,
    private val styledNode: NodeReaderBasic,
    private val styleReader: StyleReader
) : NodeReaderBasic {
    override fun fullText(): String {
        return styledNode.fullText()
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        val styleMarker = styleMarkerFactory.newStyleMarker(0, fullText().length, styleReader)
        return listOf(styleMarker) + styledNode.styleMarkersFromOutermostToInnermost()
    }
}