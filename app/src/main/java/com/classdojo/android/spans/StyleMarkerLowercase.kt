package com.classdojo.android.immutable.spannable

//package com.classdojo.android.utility.spannable;
//
//import android.text.SpannableString;
//
////TODO: this can be eliminated and its magic can be built into the stylebuilder (or whatever the stylebuilder builds)
//public class StyleMarkerLowercase extends StyleMarkerImpl implements StyleMarker {
//	public StyleMarkerLowercase(Integer startIndex, Integer length) {
//		super(startIndex, length);
//	}
//
//	public SpannableString applyStyle(SpannableString spannableString) {
//		//TODO: Validate that this does not break shit!
//		Object[] spans = spannableString.getSpans(0, spannableString.length(), Object.class);
//		SpannableString copy = new SpannableString(spannableString.toString().toLowerCase());
//		for (Object span : spans) {
//			spannableString.setSpan(
//				span,
//				spannableString.getSpanStart(span),
//				spannableString.getSpanEnd(span),
//				0
//			);
//		}
//		return copy;
//	}
//
//	public StyleMarker move(Integer amount) {
//		return new StyleMarkerLowercase(startIndex + amount, length);
//	}
//}