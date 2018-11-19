package com.classdojo.android.immutable.spannable

class StyledNode(
    spannableFactory: SpannableFactory,
    private val styleBuilder: StyleBuilder,
    private val node: NodeBuilder,
    private val styleMarkerFactory: StyleMarkerFactory
) : BaseNode(spannableFactory), Node {

    override fun fullText(): String {
        return node.build().fullText()
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        val styleMarker = styleMarkerFactory.newStyleMarker(0, fullText().length, styleBuilder)
        return listOf(styleMarker) + node.build().styleMarkersFromOutermostToInnermost()
    }

    override fun addText(text: String): NodeBuilder {
        return spannableFactory.newStyledNodeBuilder(
            styleBuilder,
            node.addText(text)
        )
    }

    override fun addStyledSpan(style: StyleBuilder, span: NodeBuilder): NodeBuilder {
        return spannableFactory.newStyledNodeBuilder(
            this.styleBuilder,
            spannableFactory.newContainerNodeBuilder(
                listOf(
                    this.node,
                    spannableFactory.newStyledNodeBuilder(
                        style,
                        span
                    )
                )
            )
        )
    }
}
