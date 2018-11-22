package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class NodeBuilderEnhancedImpl<T : NodeBuilderEnhanced<T>>(
    nodeBuilderTextHelpers:NodeBuilderTextHelpers<T>,
    nodeBuilderBasic:NodeBuilderBasic<T>,
    translatedTextBuilder:TranslatedTextBuilder<T>,
    nodeBuilder:NodeBuilder
) : NodeBuilderEnhanced<T>,
    NodeBuilderTextHelpers<T> by nodeBuilderTextHelpers,
    NodeBuilderBasic<T> by nodeBuilderBasic,
    TranslatedTextBuilder<T> by translatedTextBuilder,
    NodeBuilder by nodeBuilder