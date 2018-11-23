package com.classdojo.android.spans.interfaces

interface StyledTextReader {
    fun fullText(): String
    fun styleMarkersFromOutermostToInnermost(): List<StyleMarker>
}