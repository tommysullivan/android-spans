package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.SpannableString

class SpannableStringImpl(text:String) : android.text.SpannableString(text), SpannableString {}