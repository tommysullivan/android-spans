package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.NodeReaderBasic
import com.classdojo.android.spans.interfaces.StyleMarker

class StringFormattedNode(
    private val pattern:String,
    private val replacements:List<NodeReaderBasic>,
    private val replacementIndex:Int
) : NodeReaderBasic {
    override fun fullText(): String {
        return String.format(pattern, *replacements.map{r -> r.fullText()}.toTypedArray())
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return replacements[replacementIndex].styleMarkersFromOutermostToInnermost()
    }
}