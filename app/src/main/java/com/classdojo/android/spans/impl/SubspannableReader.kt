package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.StyledTextReader
import com.classdojo.android.spans.interfaces.StyleMarker

class SubspannableReader (
    private val sequenceOfSubspans: List<StyledTextReader>
) : StyledTextReader {
    override fun fullText(): String {
        return sequenceOfSubspans.map{ c -> c.fullText() }.joinToString("")
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        val zero = Pair(emptyList<StyleMarker>(), 0);
        return sequenceOfSubspans.fold(zero) { styleMarkersAndCurrentIndex, currentNodeReader ->
            val correctlyPositionedStyleMarkers = styleMarkersAndCurrentIndex.first
            val startIndexOfCurrentNodeBuilder = styleMarkersAndCurrentIndex.second
            val adjustedStyleMarkersForCurrentNode = currentNodeReader.styleMarkersFromOutermostToInnermost().map{
                it.move(startIndexOfCurrentNodeBuilder)
            }
            val startIndexOfNextNodeBuilder = startIndexOfCurrentNodeBuilder + currentNodeReader.fullText().length
            Pair(
                correctlyPositionedStyleMarkers + adjustedStyleMarkersForCurrentNode,
                startIndexOfNextNodeBuilder
            )
        }.first
    }
}
