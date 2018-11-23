package com.classdojo.android.spans.interfaces

interface Spans<T> :
    StyledTextReaderFactoryForPlainText,
    CombinesTextReaderSequences<T>,
    StyledNodeFactory<T>,
    StyleMarkerFactory,
    StylesFactory,
    SpannableStringFactory,
    TranslatedStyledTextReaderFactory,
    TextNodeBuilderFactory<T>