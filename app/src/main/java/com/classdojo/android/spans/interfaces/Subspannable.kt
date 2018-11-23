package com.classdojo.android.spans.interfaces

interface Subspannable<TypeToReturnForChainedOperations> {
    fun addStyledSpan(styleBuilderBlock: StyleBuilderBlock, styledTextReader: StyledTextReader): TypeToReturnForChainedOperations
    fun addSubspan(styledTextReaderForSubspan:StyledTextReader): TypeToReturnForChainedOperations
}