package com.classdojo.android.spans.interfaces

interface SpannableFactory<T : NodeBuilderBasic<T>> :
    TextNodeFactory,
    ContainerNodeFactory<T>,
    StyledNodeFactory<T>,
    StyleMarkerFactory,
    StyleBuilderFactory,
    SpannableStringFactory {
    fun newNodeBuilder():NodeBuilder
}