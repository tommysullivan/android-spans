package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.NodeBuilderBasic
import com.classdojo.android.spans.interfaces.NodeBuilderEnhanced
import com.classdojo.android.spans.interfaces.NodeBuilderTextHelpers
import com.classdojo.android.spans.interfaces.TranslatedTextBuilder

class NodeBuilderEnhancedImpl(
    nodeBuilderTextHelpers:NodeBuilderTextHelpers<NodeBuilderEnhanced>,
    nodeBuilderBasic:NodeBuilderBasic<NodeBuilderEnhanced>,
    translatedTextBuilder:TranslatedTextBuilder<NodeBuilderEnhanced>
) : NodeBuilderEnhanced,
    NodeBuilderTextHelpers<NodeBuilderEnhanced> by nodeBuilderTextHelpers,
    NodeBuilderBasic<NodeBuilderEnhanced> by nodeBuilderBasic,
    TranslatedTextBuilder<NodeBuilderEnhanced> by translatedTextBuilder