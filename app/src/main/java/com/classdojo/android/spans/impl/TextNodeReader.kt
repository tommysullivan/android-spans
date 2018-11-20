package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.NodeReaderBasic
import com.classdojo.android.spans.interfaces.StyleMarker

class TextNodeReader(private val text: String) : NodeReaderBasic {
    override fun fullText(): String {
        return text
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return emptyList()
    }
}