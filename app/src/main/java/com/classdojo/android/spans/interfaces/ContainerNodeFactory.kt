package com.classdojo.android.spans.interfaces

interface ContainerNodeFactory<T : NodeBuilderBasic<T>> {
    fun newContainerNodeBuilder(childNodes: List<NodeReaderBasic>): T
    fun newContainerNodeReader(childNodes: List<NodeReaderBasic>): NodeReaderBasic
}