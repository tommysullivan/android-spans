package com.classdojo.android.spans.interfaces

interface SubspanContainer<T> {
    fun addStyledSpan(styleReader: StyleReader, spanBuilder: StyledTextReader): T
    fun addSubspan(textWithStyleMarkers:StyledTextReader): T
}