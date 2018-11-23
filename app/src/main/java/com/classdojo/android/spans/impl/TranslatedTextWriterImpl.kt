package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class TranslatedTextWriterImpl<TypeToReturnForChainedOperations>(
    private val translatedStyledTextReaderFactory: TranslatedStyledTextReaderFactory,
    private val previousBuilder:Subspannable<TypeToReturnForChainedOperations>
) : TranslatedTextWriter<TypeToReturnForChainedOperations> {

    override fun addTranslatedText(stringResourceId: Int, vararg substitutions: StyledTextReader): TypeToReturnForChainedOperations {
        return previousBuilder.addSubspan(translatedStyledTextReaderFactory.newTranslatedStyledTextReader(stringResourceId, *substitutions))
    }
}