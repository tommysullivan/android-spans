package com.classdojo.android.immutable.spannable

class TextNode(
    spannableFactory: SpannableFactory,
    private val text: String
) : BaseNode(spannableFactory), Node {

    override fun fullText(): String {
        return text
    }

    override fun styleMarkersFromOutermostToInnermost(): List<StyleMarker> {
        return emptyList()
    }

    override fun addText(text: String): NodeBuilder {
        return spannableFactory.newTextNodeBuilder(this.text + text)
    }

    override fun addStyledSpan(styleBuilder: StyleBuilder, span: NodeBuilder): NodeBuilder {
        return spannableFactory.newContainerNodeBuilder(
            listOf(
                this,
                spannableFactory.newStyledNodeBuilder(
                    styleBuilder,
                    span
                )
            )
        )
    }
}