package com.classdojo.android.immutable.spannable

import com.classdojo.android.spans.SpannableStringFactory

interface SpannableFactory : TextNodeFactory, ContainerNodeFactory, StyledNodeFactory, StyleMarkerFactory,
    StyleBuilderFactory, SpannableStringFactory

interface TextNodeFactory {
    fun newTextNodeBuilder(text: String): NodeBuilder
}

interface ContainerNodeFactory {
    fun newContainerNodeBuilder(): NodeBuilder
    fun newContainerNodeBuilder(childNodes: List<NodeBuilder>): NodeBuilder
}

interface StyledNodeFactory {
    fun newStyledNodeBuilder(styleBuilder: StyleBuilder, nodeToStyle: NodeBuilder): NodeBuilder
}

interface StyleMarkerFactory {
    fun newStyleMarker(startIndex: Int, length: Int, styleBuilder: StyleBuilder): StyleMarker
}

interface StyleBuilderFactory {
    fun newStyleBuilder(): StyleBuilder
    fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder
}