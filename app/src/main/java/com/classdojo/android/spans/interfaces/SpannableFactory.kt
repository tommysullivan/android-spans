package com.classdojo.android.spans.interfaces

interface SpannableFactory<T> :
    TextNodeFactory,
    ContainerNodeFactory<T>,
    StyledNodeFactory<T>,
    StyleMarkerFactory,
    StyleBuilderFactory,
    SpannableStringFactory,
    TranslatedNodeFactory,
    TextNodeBuilderFactory<T>