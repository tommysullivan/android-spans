package com.classdojo.android.spans.interfaces

interface StylesFactory {
    fun styles():Styles
    fun styleBuilder(): StyleBuilder
    fun styleBuilder(crappyAndroidStyleObjects: List<Any>): StyleBuilder
}