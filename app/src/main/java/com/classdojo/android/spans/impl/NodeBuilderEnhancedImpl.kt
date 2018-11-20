package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.*

class NodeBuilderEnhancedImpl(
    nodeBuilderTextHelpers:NodeBuilderTextHelpers<NodeBuilderEnhanced>,
    nodeBuilderBasic:NodeBuilderBasic<NodeBuilderEnhanced>,
    translatedTextBuilder:TranslatedTextBuilder<NodeBuilderEnhanced>,
    nodeBuilder:NodeBuilder
) : NodeBuilderEnhanced,
    NodeBuilderTextHelpers<NodeBuilderEnhanced> by nodeBuilderTextHelpers,
    NodeBuilderBasic<NodeBuilderEnhanced> by nodeBuilderBasic,
    TranslatedTextBuilder<NodeBuilderEnhanced> by translatedTextBuilder,
    NodeBuilder by nodeBuilder