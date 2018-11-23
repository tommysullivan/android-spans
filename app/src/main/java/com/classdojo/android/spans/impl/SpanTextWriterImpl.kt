package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.SpanTextWriter
import com.classdojo.android.spans.interfaces.StyleReader
import com.classdojo.android.spans.interfaces.StyledTextReaderFactoryForPlainText
import com.classdojo.android.spans.interfaces.Subspannable

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