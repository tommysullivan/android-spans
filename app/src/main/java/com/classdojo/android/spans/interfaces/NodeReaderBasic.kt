package com.classdojo.android.spans.interfaces

interface NodeReaderBasic {
    fun fullText(): String
    fun styleMarkersFromOutermostToInnermost(): List<StyleMarker>
}