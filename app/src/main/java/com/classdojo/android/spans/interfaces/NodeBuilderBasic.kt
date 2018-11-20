package com.classdojo.android.spans.interfaces

interface NodeBuilderBasic<T : NodeBuilderBasic<T>> {
    //TODO: Implement addTranslatedText(@StringRes Integer translationId, NodeBuilder[] replacements);
    //TODO: Implement Builder based convenience functions so callers need not call build() first (even though reader will suffice)
    fun addStyledSpan(styleReader: StyleReader, spanBuilder: NodeReaderBasic): T
    fun build(): NodeReaderBasic
}