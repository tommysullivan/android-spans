package com.classdojo.android.spans.interfaces

interface TextNodeBuilderFactory<TypeToReturnForChainedOperations> {
    fun newTextNodeBuilder():TypeToReturnForChainedOperations
    fun newTextNodeBuilder(text:String):TypeToReturnForChainedOperations
}