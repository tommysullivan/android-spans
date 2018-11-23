package com.classdojo.android.spans.interfaces

interface TextNodeBuilderFactory<TypeToReturnForChainedOperations> {
    fun newEmptySpan():TypeToReturnForChainedOperations
    fun newTextNodeBuilder(text:String):TypeToReturnForChainedOperations
}