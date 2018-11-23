package com.classdojo.android.spans.interfaces

interface TranslatedStyledTextReaderFactory {
    fun newTranslatedStyledTextReader(stringResourceIdOfTranslationWithPossiblePlacholders: Int, vararg styledTextToSubstituteInForPlaceholders: StyledTextReader):StyledTextReader
}