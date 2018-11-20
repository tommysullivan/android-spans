package com.classdojo.android.spans.interfaces

interface StyleBuilderFactory {
    fun newStyleBuilder(): StyleBuilder
    fun newStyleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder
}