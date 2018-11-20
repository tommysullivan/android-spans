package com.classdojo.android.spans.interfaces

interface StyledNodeFactory<T> {
    fun newStyledNodeBuilder(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): T
    fun newStyledNodeReader(styleReader: StyleReader, nodeToStyle: NodeReaderBasic): NodeReaderBasic
}