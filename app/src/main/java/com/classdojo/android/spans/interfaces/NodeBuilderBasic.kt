package com.classdojo.android.spans.interfaces

interface NodeBuilderBasic<T : NodeBuilderBasic<T>> {
    //TODO: Implement addTranslatedText(@StringRes Integer translationId, NodeBuilder[] replacements);
    //TODO: Implement Builder based convenience functions so callers need not call build() first (even though reader will suffice)
    fun addStyledSpan(styleReader: StyleReader, spanBuilder: NodeReaderBasic): T

    //TODO: Make build() return NodeReaderBasic or something that extends it
    fun build(): NodeReader
}

//interface Eq<F> {
//    fun F.eqv(b: F): Boolean
//
//    fun F.neqv(b: F): Boolean =
//        !eqv(b)
//}