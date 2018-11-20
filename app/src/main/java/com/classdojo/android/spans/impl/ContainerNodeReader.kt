package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.NodeReaderBasic
import com.classdojo.android.spans.interfaces.StyleMarker

class ContainerNodeReader(
    private val childNodeBuilders: List<NodeReaderBasic>
) : NodeReaderBasic {
    override fun fullText(): String {
        return childNodeBuilders.map{ c -> c.fullText() }.joinToString("")
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        val zero = Pair(emptyList<StyleMarker>(), 0);
        return childNodeBuilders.fold(zero) { styleMarkersAndCurrentIndex, currentNodeReader ->
            val correctlyPositionedStyleMarkers = styleMarkersAndCurrentIndex.first
            val startIndexOfCurrentNodeBuilder = styleMarkersAndCurrentIndex.second
            val adjustedStyleMarkersForCurrentNode = currentNodeReader.styleMarkersFromOutermostToInnermost().map{
                    styleMarker -> styleMarker.move(startIndexOfCurrentNodeBuilder)
            }
            val startIndexOfNextNodeBuilder = startIndexOfCurrentNodeBuilder + currentNodeReader.fullText().length
            Pair(
                correctlyPositionedStyleMarkers + adjustedStyleMarkersForCurrentNode,
                startIndexOfNextNodeBuilder
            )
        }.first
    }
}
