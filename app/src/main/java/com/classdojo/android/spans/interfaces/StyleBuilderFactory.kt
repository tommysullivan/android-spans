package com.classdojo.android.spans.interfaces

interface StyleBuilderFactory {
    fun newStyle():Style
    fun newStyleBuilder(): StyleBuilder
    fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder
}