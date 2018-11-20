package com.classdojo.android.spans.interfaces

interface TextNodeBuilderFactory<T> {
    fun newTextNodeBuilder():T
    fun newTextNodeBuilder(text:String):T
}