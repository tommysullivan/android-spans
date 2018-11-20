package com.classdojo.android.spans.interfaces

interface StyleMarkerFactory {
    fun newStyleMarker(startIndex: Int, length: Int, styleBuilder: StyleReader): StyleMarker
}