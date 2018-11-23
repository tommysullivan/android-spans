package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.StyleBuilder

class SizeImpl<NativeSizeType>(
    private val newStyleBuilderWithColor: Function1<NativeSizeType, StyleBuilder>,
    private val _tiny: NativeSizeType,
    private val _small: NativeSizeType,
    private val _normal: NativeSizeType,
    private val _large: NativeSizeType,
    private val _huge: NativeSizeType
) : StyleBuilder.Size {

    override val tiny: StyleBuilder by lazy { newStyleBuilderWithColor(_tiny) }
    override val small: StyleBuilder by lazy { newStyleBuilderWithColor(_small) }
    override val normal: StyleBuilder by lazy { newStyleBuilderWithColor(_normal) }
    override val large: StyleBuilder by lazy { newStyleBuilderWithColor(_large) }
    override val huge: StyleBuilder by lazy { newStyleBuilderWithColor(_huge) }
}
