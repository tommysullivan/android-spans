package com.classdojo.android.spans.interfaces

interface SpanTextWriter<TypeToReturnForChainedOperations> {
    fun addText(text: String): TypeToReturnForChainedOperations
    fun addStyledText(textToStyle: String, styleBuilderBlock: StyleBuilderBlock): TypeToReturnForChainedOperations
    fun newLine(): TypeToReturnForChainedOperations = addText("\n")
}