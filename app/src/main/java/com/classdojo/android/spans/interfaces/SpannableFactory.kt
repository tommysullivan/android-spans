package com.classdojo.android.spans.interfaces

interface SpannableFactory :
    TextNodeFactory,
    ContainerNodeFactory<NodeBuilderEnhanced>,
    StyledNodeFactory<NodeBuilderEnhanced>,
    StyleMarkerFactory,
    StyleBuilderFactory,
    SpannableStringFactory,
    TranslatedNodeFactory,
    TextNodeBuilderFactory<NodeBuilderEnhanced>