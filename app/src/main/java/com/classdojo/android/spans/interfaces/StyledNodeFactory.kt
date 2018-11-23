package com.classdojo.android.spans.interfaces

interface StyledNodeFactory<TypeToReturnForChainedOperations> {
    fun newStyledSpanBuilder(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): TypeToReturnForChainedOperations
    fun newStyledTextReaderWithExplicitStyle(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): StyledTextReader
}