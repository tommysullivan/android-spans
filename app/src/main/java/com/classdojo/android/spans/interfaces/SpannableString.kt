package com.classdojo.android.spans.interfaces

interface SpannableString {
    fun setSpan(currentStyleObject:Any, startIndex:Int, endIndex:Int, flags:Int):Unit
}