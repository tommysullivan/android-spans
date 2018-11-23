package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class SpanWriterImpl<TypeToReturnForChainedOperations>(
    spanTextWriter:SpanTextWriter<TypeToReturnForChainedOperations>,
    subspannable:Subspannable<TypeToReturnForChainedOperations>,
    translatedTextWriter:TranslatedTextWriter<TypeToReturnForChainedOperations>,
    convertibleToReadOnlySpan:ConvertibleToReadOnlySpan
) : SpanWriter<TypeToReturnForChainedOperations>,
    SpanTextWriter<TypeToReturnForChainedOperations> by spanTextWriter,
    Subspannable<TypeToReturnForChainedOperations> by subspannable,
    TranslatedTextWriter<TypeToReturnForChainedOperations> by translatedTextWriter,
    ConvertibleToReadOnlySpan by convertibleToReadOnlySpan