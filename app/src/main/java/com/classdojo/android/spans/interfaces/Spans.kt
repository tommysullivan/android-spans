package com.classdojo.android.spans.interfaces

interface Spans : SpansOfT<Span>

interface SpansOfT<TypeToReturnForChainedOperations> :
    StyledTextReaderFactoryForPlainText,
    CombinesTextReaderSequences<TypeToReturnForChainedOperations>,
    StyledNodeFactory<TypeToReturnForChainedOperations>,
    StyleMarkerFactory,
    StylesFactory,
    SpannableStringFactory,
    TranslatedStyledTextReaderFactory,
    TextNodeBuilderFactory<TypeToReturnForChainedOperations>