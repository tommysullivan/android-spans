package com.classdojo.android.spans.interfaces

interface SpanWriter<TypeToReturnForChainedOperations> :
    SpanTextWriter<TypeToReturnForChainedOperations>,
    Subspannable<TypeToReturnForChainedOperations>,
    TranslatedTextWriter<TypeToReturnForChainedOperations>,
    ConvertibleToReadOnlySpan