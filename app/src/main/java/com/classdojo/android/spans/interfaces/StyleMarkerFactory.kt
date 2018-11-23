package com.classdojo.android.spans.interfaces

interface StyleMarkerFactory {
    fun newStyleMarker(startIndex: Int, length: Int, styleReader: StyleReader): StyleMarker
}