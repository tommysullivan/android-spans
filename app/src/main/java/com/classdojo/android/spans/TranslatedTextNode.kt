package com.classdojo.android.immutable.spannable

//package com.classdojo.android.utility.spannable;
//
//import android.support.annotation.StringRes;
//
//import com.classdojo.android.ClassDojoApplication;
//import com.classdojo.android.utility.list.ImmutableList;
//import com.classdojo.android.utility.list.ImmutableLists;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class TranslatedTextNode extends BaseNode implements Node {
//	private final @StringRes Integer translationId;
//	private final ImmutableList<NodeReader> replacements;
//	private final ImmutableLists immutableLists;
//
//	public TranslatedTextNode(SpannableFactory spannableFactory, Integer translationId, ImmutableList<NodeReader> replacements, ImmutableLists immutableLists) {
//		super(spannableFactory);
//		this.translationId = translationId;
//		this.replacements = replacements;
//		this.immutableLists = immutableLists;
//	}
//
//	@Override
//	public String fullText() {
//		return ClassDojoApplication.getInstance().getString(
//			translationId,
//			replacements.map(r -> r.fullText()).toArray()
//		);
//	}
//
//	@Override
//	public ImmutableList<StyleMarker> styleMarkersFromOutermostToInnermost() {
//		String translationTemplate = ClassDojoApplication.getInstance().getString(translationId);
//		ImmutableList<TranslationSubstring> translationSubstrings = breakIntoSubstrings(translationTemplate);
//		return translationSubstrings.reduce(
//			spannableFactory.newContainerNodeBuilder(),
//			(builderSoFar, substring) -> substring.isPlainText()
//				? builderSoFar.addText(substring.plainText())
//				: builderSoFar.addText(substring.replacementPattern()) //todo: some other type here
//		).build().styleMarkersFromOutermostToInnermost();
//	}
//
//	interface TranslationSubstring {
//		Boolean isPlainText();
//		String plainText();
//		String replacementPattern();
//	}
//
//	private ImmutableList<TranslationSubstring> breakIntoSubstrings(String translationTemplate) {
//		Pattern catPattern = Pattern.compile("", Pattern.MULTILINE);
//		Matcher matcher = catPattern.matcher(translationTemplate);
//		while (matcher.find()) {
//
//		}
//		return immutableLists.newEmptyList();
//	}
//
//}
