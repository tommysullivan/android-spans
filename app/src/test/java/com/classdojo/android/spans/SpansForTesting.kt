package com.classdojo.android.spans

import com.classdojo.android.spans.impl.SpansImpl
import com.classdojo.android.spans.interfaces.SpannableString

class SpansForTesting(
    private val mockSpannableString: SpannableString,
    getStringResourceWithoutPerformingReplacements:(resourceId:Int) -> String
) : SpansImpl(getStringResourceWithoutPerformingReplacements) {
    override fun newSpannableString(text: String): SpannableString = mockSpannableString
}