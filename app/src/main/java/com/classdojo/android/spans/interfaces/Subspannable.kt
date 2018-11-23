package com.classdojo.android.spans.interfaces

interface Subspannable<TypeToReturnForChainedOperations> {
    fun addStyledSpan(styleReader: StyleReader, styledTextReader: StyledTextReader): TypeToReturnForChainedOperations
    fun addSubspan(styledTextReaderForSubspan:StyledTextReader): TypeToReturnForChainedOperations
}