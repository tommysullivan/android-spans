package com.classdojo.android.spans.interfaces

interface StylesFactory {
    fun styles():Styles
    fun newStyleBuilder(): StyleBuilder
    fun styleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder
}