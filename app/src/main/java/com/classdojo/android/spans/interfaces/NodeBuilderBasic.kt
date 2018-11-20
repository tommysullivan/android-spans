package com.classdojo.android.spans.interfaces

interface NodeBuilderBasic<T> {
    //TODO: Implement Builder based convenience functions so callers need not call build() first (even though reader will suffice)
    fun addStyledSpan(styleReader: StyleReader, spanBuilder: NodeReaderBasic): T
    fun build(): NodeReader
}