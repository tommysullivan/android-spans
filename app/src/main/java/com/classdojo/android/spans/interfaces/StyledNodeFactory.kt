package com.classdojo.android.spans.interfaces

interface StyledNodeFactory<T> {
    fun newStyledNodeBuilder(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): T
    fun newStyledNodeReader(styleReader: StyleReader, styledTextReaderToStyleFurther: StyledTextReader): StyledTextReader
}