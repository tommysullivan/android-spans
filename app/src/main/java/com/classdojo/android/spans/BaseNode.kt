package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableString

abstract class BaseNode(
    protected val spannableFactory: SpannableFactory
    ) : Node {

    override fun build(): NodeReader {
        return this
    }

    override fun addStyledText(styleBuilder: StyleBuilder, text: String): NodeBuilder {
        return addStyledSpan(styleBuilder, spannableFactory.newTextNodeBuilder(text))
    }

    override fun addText(text: String): NodeBuilder {
        return addStyledText(spannableFactory.newStyleBuilder(), text)
    }

    override fun asSpannableString(): SpannableString {
        val spannableString = spannableFactory.newSpannableString(fullText())
        return styleMarkersFromOutermostToInnermost().fold(spannableString) {
                spannableSoFar, styleMarker -> styleMarker.applyStyle(spannableSoFar)
        }
    }

}