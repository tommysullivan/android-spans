package com.classdojo.android.spans.interfaces

interface NodeBuilderEnhanced<T> :
    NodeBuilderTextHelpers<T>,
    NodeBuilderBasic<T>,
    TranslatedTextBuilder<T>,
    NodeBuilder