package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.StyleBuilder

class SizeImpl<T>(
    private val newStyleBuilderWithColor: Function1<T, StyleBuilder>,
    private val _tiny: T,
    private val _small: T,
    private val _normal: T,
    private val _large: T,
    private val _huge: T
) : StyleBuilder.Size {

    override fun tiny(): StyleBuilder = newStyleBuilderWithColor(_tiny)
    override fun small(): StyleBuilder = newStyleBuilderWithColor(_small)
    override fun normal(): StyleBuilder = newStyleBuilderWithColor(_normal)
    override fun large(): StyleBuilder = newStyleBuilderWithColor(_large)
    override fun huge(): StyleBuilder = newStyleBuilderWithColor(_huge)
}
