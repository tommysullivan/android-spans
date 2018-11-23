package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.StyledTextReader
import com.classdojo.android.spans.interfaces.Subspannable
import com.classdojo.android.spans.interfaces.TranslatedStyledTextReaderFactory
import com.classdojo.android.spans.interfaces.TranslatedTextWriter

class TranslatedTextWriterImpl<TypeToReturnForChainedOperations>(
    private val translatedStyledTextReaderFactory: TranslatedStyledTextReaderFactory,
    private val previousBuilder:Subspannable<TypeToReturnForChainedOperations>
) : TranslatedTextWriter<TypeToReturnForChainedOperations> {

    override fun addTranslatedText(stringResourceId: Int, vararg substitutions: StyledTextReader): TypeToReturnForChainedOperations {
        return previousBuilder.addSubspan(translatedStyledTextReaderFactory.newTranslatedStyledTextReader(stringResourceId, *substitutions))
    }
}