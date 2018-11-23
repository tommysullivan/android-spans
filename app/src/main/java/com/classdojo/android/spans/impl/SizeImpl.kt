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

    override fun tiny(): StyleBuilder = newStyleBuilderWithColor(_tiny)
    override fun small(): StyleBuilder = newStyleBuilderWithColor(_small)
    override fun normal(): StyleBuilder = newStyleBuilderWithColor(_normal)
    override fun large(): StyleBuilder = newStyleBuilderWithColor(_large)
    override fun huge(): StyleBuilder = newStyleBuilderWithColor(_huge)
}
