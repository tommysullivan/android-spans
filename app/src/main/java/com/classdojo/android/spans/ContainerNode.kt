package com.classdojo.android.immutable.spannable

class ContainerNode(
    spannableFactory: SpannableFactory,
    private val childNodeBuilders: List<NodeBuilder>
) : BaseNode(spannableFactory), Node {

    override fun fullText(): String {
        return childNodeBuilders.map{ c -> c.build().fullText() }.joinToString("")
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        val zero = Pair(emptyList<StyleMarker>(), 0);
        return childNodeBuilders.fold(zero) { styleMarkersAndCurrentIndex, currentNodeBuilder ->
            val currentNode = currentNodeBuilder.build()
            val correctlyPositionedStyleMarkers = styleMarkersAndCurrentIndex.component1()
            val startIndexOfCurrentNodeBuilder = styleMarkersAndCurrentIndex.component2()
            val adjustedStyleMarkersForCurrentNode = currentNode.styleMarkersFromOutermostToInnermost().map{
                    styleMarker -> styleMarker.move(startIndexOfCurrentNodeBuilder)
            }
            val startIndexOfNextNodeBuilder = startIndexOfCurrentNodeBuilder + currentNode.fullText().length
            Pair(
                correctlyPositionedStyleMarkers + adjustedStyleMarkersForCurrentNode,
                startIndexOfNextNodeBuilder
            ) //TODO: Can a syntactic sugar tuple be used here instead of Pair<>?
        }.component1()
    }

    override fun addText(text: String): NodeBuilder {
        return spannableFactory.newContainerNodeBuilder(
            childNodeBuilders + spannableFactory.newTextNodeBuilder(text)
        )
    }

    override fun addStyledSpan(styleBuilder: StyleBuilder, spanBuilder: NodeBuilder): NodeBuilder {
        val newSpanBuilder = spannableFactory.newStyledNodeBuilder(
            styleBuilder,
            spanBuilder
        )
        return spannableFactory.newContainerNodeBuilder(
            childNodeBuilders + newSpanBuilder
        )
    }
}
