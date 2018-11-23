package com.classdojo.android.spans.interfaces

interface SpanTextWriter<TypeToReturnForChainedOperations> {
    fun addText(text: String): TypeToReturnForChainedOperations
    fun addStyledText(styleBuilder: StyleReader, text: String): TypeToReturnForChainedOperations
    fun newLine(): TypeToReturnForChainedOperations = addText("\n")
}