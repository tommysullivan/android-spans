package com.classdojo.android.spans.impl

import com.classdojo.android.spans.interfaces.NodeBuilder
import com.classdojo.android.spans.interfaces.NodeReader

class NodeBuilderImpl(private val nodeReader:NodeReader) : NodeBuilder {
    override fun build() = nodeReader
}