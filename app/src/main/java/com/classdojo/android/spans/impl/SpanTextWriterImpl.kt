package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpanTextWriterImpl<TypeToReturnForChainedOperations>(
    private val styledTextReaderFactoryForPlainText: StyledTextReaderFactoryForPlainText,
    private val subspannable: Subspannable<TypeToReturnForChainedOperations>
) : SpanTextWriter<TypeToReturnForChainedOperations>
{
    override fun addText(text: String): TypeToReturnForChainedOperations {
        return subspannable.addSubspan(styledTextReaderFactoryForPlainText.newStyledTextReader(text))
    }

    override fun addStyledText(styleReader: StyleReader, text: String): TypeToReturnForChainedOperations {
        return subspannable.addStyledSpan(styleReader, styledTextReaderFactoryForPlainText.newStyledTextReader(text))
    }
}