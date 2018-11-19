package com.classdojo.android.immutable.spannable

interface NodeBuilder {
    fun addText(text: String): NodeBuilder
    fun addStyledSpan(styleBuilder: StyleBuilder, spanBuilder: NodeBuilder): NodeBuilder
    fun build(): NodeReader
    fun addStyledText(styleBuilder: StyleBuilder, text: String): NodeBuilder
    //	NodeBuilder addTranslatedText(@StringRes Integer translationId, NodeBuilder[] replacements);
    fun newLine(): NodeBuilder {
        return addText("\n")
    }
}