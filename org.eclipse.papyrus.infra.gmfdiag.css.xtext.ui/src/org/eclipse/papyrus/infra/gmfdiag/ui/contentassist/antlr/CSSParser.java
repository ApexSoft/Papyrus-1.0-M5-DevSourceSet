/*****************************************************************************
 * Copyright (c) 2012-2013 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.papyrus.infra.gmfdiag.services.CSSGrammarAccess;

public class CSSParser extends AbstractContentAssistParser {
	
	@Inject
	private CSSGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.papyrus.infra.gmfdiag.ui.contentassist.antlr.internal.InternalCSSParser createParser() {
		org.eclipse.papyrus.infra.gmfdiag.ui.contentassist.antlr.internal.InternalCSSParser result = new org.eclipse.papyrus.infra.gmfdiag.ui.contentassist.antlr.internal.InternalCSSParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getStylesheetAccess().getAlternatives_1(), "rule__Stylesheet__Alternatives_1");
					put(grammarAccess.getStylesheetAccess().getAlternatives_2_1(), "rule__Stylesheet__Alternatives_2_1");
					put(grammarAccess.getStylesheetAccess().getAlternatives_3_1(), "rule__Stylesheet__Alternatives_3_1");
					put(grammarAccess.getContentAccess().getAlternatives(), "rule__Content__Alternatives");
					put(grammarAccess.getImportAccess().getAlternatives_1(), "rule__Import__Alternatives_1");
					put(grammarAccess.getSelectorAccess().getAlternatives(), "rule__Selector__Alternatives");
					put(grammarAccess.getCompositeSelectorAccess().getAlternatives_1(), "rule__CompositeSelector__Alternatives_1");
					put(grammarAccess.getSimpleSelectorAccess().getAlternatives(), "rule__SimpleSelector__Alternatives");
					put(grammarAccess.getSimpleSelectorAccess().getElementNameAlternatives_0_0_0(), "rule__SimpleSelector__ElementNameAlternatives_0_0_0");
					put(grammarAccess.getSelectorConditionAccess().getAlternatives(), "rule__SelectorCondition__Alternatives");
					put(grammarAccess.getTermAccess().getAlternatives_0(), "rule__Term__Alternatives_0");
					put(grammarAccess.getOperatorAccess().getAlternatives(), "rule__Operator__Alternatives");
					put(grammarAccess.getAttributeValueAccess().getValueAlternatives_2_0(), "rule__AttributeValue__ValueAlternatives_2_0");
					put(grammarAccess.getPseudoAccess().getAlternatives_1(), "rule__Pseudo__Alternatives_1");
					put(grammarAccess.getUNARYAccess().getAlternatives(), "rule__UNARY__Alternatives");
					put(grammarAccess.getKINDAccess().getAlternatives(), "rule__KIND__Alternatives");
					put(grammarAccess.getATTRIBUTE_OPAccess().getAlternatives(), "rule__ATTRIBUTE_OP__Alternatives");
					put(grammarAccess.getStylesheetAccess().getGroup(), "rule__Stylesheet__Group__0");
					put(grammarAccess.getStylesheetAccess().getGroup_0(), "rule__Stylesheet__Group_0__0");
					put(grammarAccess.getStylesheetAccess().getGroup_2(), "rule__Stylesheet__Group_2__0");
					put(grammarAccess.getStylesheetAccess().getGroup_2_1_0(), "rule__Stylesheet__Group_2_1_0__0");
					put(grammarAccess.getStylesheetAccess().getGroup_2_1_1(), "rule__Stylesheet__Group_2_1_1__0");
					put(grammarAccess.getStylesheetAccess().getGroup_3(), "rule__Stylesheet__Group_3__0");
					put(grammarAccess.getStylesheetAccess().getGroup_3_1_0(), "rule__Stylesheet__Group_3_1_0__0");
					put(grammarAccess.getStylesheetAccess().getGroup_3_1_1(), "rule__Stylesheet__Group_3_1_1__0");
					put(grammarAccess.getImportAccess().getGroup(), "rule__Import__Group__0");
					put(grammarAccess.getImportAccess().getGroup_1_0(), "rule__Import__Group_1_0__0");
					put(grammarAccess.getImportAccess().getGroup_1_1(), "rule__Import__Group_1_1__0");
					put(grammarAccess.getImportAccess().getGroup_3(), "rule__Import__Group_3__0");
					put(grammarAccess.getImportAccess().getGroup_3_2(), "rule__Import__Group_3_2__0");
					put(grammarAccess.getMediaAccess().getGroup(), "rule__Media__Group__0");
					put(grammarAccess.getMediaAccess().getGroup_4(), "rule__Media__Group_4__0");
					put(grammarAccess.getPageAccess().getGroup(), "rule__Page__Group__0");
					put(grammarAccess.getPageAccess().getGroup_7(), "rule__Page__Group_7__0");
					put(grammarAccess.getPseudoPageAccess().getGroup(), "rule__PseudoPage__Group__0");
					put(grammarAccess.getRulesetAccess().getGroup(), "rule__Ruleset__Group__0");
					put(grammarAccess.getRulesetAccess().getGroup_1(), "rule__Ruleset__Group_1__0");
					put(grammarAccess.getRulesetAccess().getGroup_5(), "rule__Ruleset__Group_5__0");
					put(grammarAccess.getSelectorAccess().getGroup_0(), "rule__Selector__Group_0__0");
					put(grammarAccess.getCompositeSelectorAccess().getGroup(), "rule__CompositeSelector__Group__0");
					put(grammarAccess.getCompositeSelectorAccess().getGroup_1_1(), "rule__CompositeSelector__Group_1_1__0");
					put(grammarAccess.getSimpleSelectorAccess().getGroup_0(), "rule__SimpleSelector__Group_0__0");
					put(grammarAccess.getCombinatorAccess().getGroup(), "rule__Combinator__Group__0");
					put(grammarAccess.getDeclarationAccess().getGroup(), "rule__Declaration__Group__0");
					put(grammarAccess.getDeclarationAccess().getGroup_5(), "rule__Declaration__Group_5__0");
					put(grammarAccess.getExpressionAccess().getGroup(), "rule__Expression__Group__0");
					put(grammarAccess.getSubtermAccess().getGroup(), "rule__Subterm__Group__0");
					put(grammarAccess.getTermAccess().getGroup(), "rule__Term__Group__0");
					put(grammarAccess.getFunctionAccess().getGroup(), "rule__Function__Group__0");
					put(grammarAccess.getNumberAccess().getGroup(), "rule__Number__Group__0");
					put(grammarAccess.getPercentageAccess().getGroup(), "rule__Percentage__Group__0");
					put(grammarAccess.getLengthAccess().getGroup(), "rule__Length__Group__0");
					put(grammarAccess.getEmsAccess().getGroup(), "rule__Ems__Group__0");
					put(grammarAccess.getExsAccess().getGroup(), "rule__Exs__Group__0");
					put(grammarAccess.getAngleAccess().getGroup(), "rule__Angle__Group__0");
					put(grammarAccess.getTimeAccess().getGroup(), "rule__Time__Group__0");
					put(grammarAccess.getFrequencyAccess().getGroup(), "rule__Frequency__Group__0");
					put(grammarAccess.getUriAccess().getGroup(), "rule__Uri__Group__0");
					put(grammarAccess.getOperatorAccess().getGroup_0(), "rule__Operator__Group_0__0");
					put(grammarAccess.getOperatorAccess().getGroup_1(), "rule__Operator__Group_1__0");
					put(grammarAccess.getAttributeAccess().getGroup(), "rule__Attribute__Group__0");
					put(grammarAccess.getAttributeValueAccess().getGroup(), "rule__AttributeValue__Group__0");
					put(grammarAccess.getPseudoAccess().getGroup(), "rule__Pseudo__Group__0");
					put(grammarAccess.getPseudoAccess().getGroup_1_1(), "rule__Pseudo__Group_1_1__0");
					put(grammarAccess.getPseudoAccess().getGroup_1_1_3(), "rule__Pseudo__Group_1_1_3__0");
					put(grammarAccess.getStylesheetAccess().getCharsetAssignment_0_1(), "rule__Stylesheet__CharsetAssignment_0_1");
					put(grammarAccess.getStylesheetAccess().getImportsAssignment_2_0(), "rule__Stylesheet__ImportsAssignment_2_0");
					put(grammarAccess.getStylesheetAccess().getContentsAssignment_3_0(), "rule__Stylesheet__ContentsAssignment_3_0");
					put(grammarAccess.getImportAccess().getStringAssignment_1_0_1(), "rule__Import__StringAssignment_1_0_1");
					put(grammarAccess.getImportAccess().getUriAssignment_1_1_1(), "rule__Import__UriAssignment_1_1_1");
					put(grammarAccess.getImportAccess().getMediaAssignment_3_0(), "rule__Import__MediaAssignment_3_0");
					put(grammarAccess.getImportAccess().getMediaAssignment_3_2_2(), "rule__Import__MediaAssignment_3_2_2");
					put(grammarAccess.getMediaAccess().getMediaAssignment_2(), "rule__Media__MediaAssignment_2");
					put(grammarAccess.getMediaAccess().getMediaAssignment_4_2(), "rule__Media__MediaAssignment_4_2");
					put(grammarAccess.getMediaAccess().getRulesAssignment_7(), "rule__Media__RulesAssignment_7");
					put(grammarAccess.getPageAccess().getPseudoAssignment_3(), "rule__Page__PseudoAssignment_3");
					put(grammarAccess.getPageAccess().getDeclarationsAssignment_6(), "rule__Page__DeclarationsAssignment_6");
					put(grammarAccess.getPageAccess().getDeclarationsAssignment_7_2(), "rule__Page__DeclarationsAssignment_7_2");
					put(grammarAccess.getPseudoPageAccess().getIdAssignment_1(), "rule__PseudoPage__IdAssignment_1");
					put(grammarAccess.getRulesetAccess().getSelectorsAssignment_0(), "rule__Ruleset__SelectorsAssignment_0");
					put(grammarAccess.getRulesetAccess().getSelectorsAssignment_1_2(), "rule__Ruleset__SelectorsAssignment_1_2");
					put(grammarAccess.getRulesetAccess().getPropertiesAssignment_4(), "rule__Ruleset__PropertiesAssignment_4");
					put(grammarAccess.getRulesetAccess().getPropertiesAssignment_5_2(), "rule__Ruleset__PropertiesAssignment_5_2");
					put(grammarAccess.getCompositeSelectorAccess().getLeftAssignment_0(), "rule__CompositeSelector__LeftAssignment_0");
					put(grammarAccess.getCompositeSelectorAccess().getCombinatorAssignment_1_1_1(), "rule__CompositeSelector__CombinatorAssignment_1_1_1");
					put(grammarAccess.getCompositeSelectorAccess().getRightAssignment_2(), "rule__CompositeSelector__RightAssignment_2");
					put(grammarAccess.getSimpleSelectorAccess().getElementNameAssignment_0_0(), "rule__SimpleSelector__ElementNameAssignment_0_0");
					put(grammarAccess.getSimpleSelectorAccess().getConditionAssignment_0_1(), "rule__SimpleSelector__ConditionAssignment_0_1");
					put(grammarAccess.getSimpleSelectorAccess().getConditionAssignment_1(), "rule__SimpleSelector__ConditionAssignment_1");
					put(grammarAccess.getCombinatorAccess().getKindAssignment_0(), "rule__Combinator__KindAssignment_0");
					put(grammarAccess.getDeclarationAccess().getPropertyAssignment_0(), "rule__Declaration__PropertyAssignment_0");
					put(grammarAccess.getDeclarationAccess().getExpressionAssignment_4(), "rule__Declaration__ExpressionAssignment_4");
					put(grammarAccess.getDeclarationAccess().getImportantAssignment_5_0(), "rule__Declaration__ImportantAssignment_5_0");
					put(grammarAccess.getExpressionAccess().getTermsAssignment_0(), "rule__Expression__TermsAssignment_0");
					put(grammarAccess.getExpressionAccess().getSubtermsAssignment_1(), "rule__Expression__SubtermsAssignment_1");
					put(grammarAccess.getSubtermAccess().getOperatorAssignment_0(), "rule__Subterm__OperatorAssignment_0");
					put(grammarAccess.getSubtermAccess().getTermAssignment_1(), "rule__Subterm__TermAssignment_1");
					put(grammarAccess.getFunctionAccess().getNameAssignment_0(), "rule__Function__NameAssignment_0");
					put(grammarAccess.getFunctionAccess().getArgsAssignment_3(), "rule__Function__ArgsAssignment_3");
					put(grammarAccess.getNameAccess().getValueAssignment(), "rule__Name__ValueAssignment");
					put(grammarAccess.getNumberAccess().getOpAssignment_0(), "rule__Number__OpAssignment_0");
					put(grammarAccess.getNumberAccess().getValueAssignment_1(), "rule__Number__ValueAssignment_1");
					put(grammarAccess.getPercentageAccess().getOpAssignment_0(), "rule__Percentage__OpAssignment_0");
					put(grammarAccess.getPercentageAccess().getValueAssignment_1(), "rule__Percentage__ValueAssignment_1");
					put(grammarAccess.getPercentageAccess().getUnitAssignment_2(), "rule__Percentage__UnitAssignment_2");
					put(grammarAccess.getLengthAccess().getOpAssignment_0(), "rule__Length__OpAssignment_0");
					put(grammarAccess.getLengthAccess().getValueAssignment_1(), "rule__Length__ValueAssignment_1");
					put(grammarAccess.getLengthAccess().getUnitAssignment_2(), "rule__Length__UnitAssignment_2");
					put(grammarAccess.getEmsAccess().getOpAssignment_0(), "rule__Ems__OpAssignment_0");
					put(grammarAccess.getEmsAccess().getValueAssignment_1(), "rule__Ems__ValueAssignment_1");
					put(grammarAccess.getEmsAccess().getUnitAssignment_2(), "rule__Ems__UnitAssignment_2");
					put(grammarAccess.getExsAccess().getOpAssignment_0(), "rule__Exs__OpAssignment_0");
					put(grammarAccess.getExsAccess().getValueAssignment_1(), "rule__Exs__ValueAssignment_1");
					put(grammarAccess.getExsAccess().getUnitAssignment_2(), "rule__Exs__UnitAssignment_2");
					put(grammarAccess.getAngleAccess().getOpAssignment_0(), "rule__Angle__OpAssignment_0");
					put(grammarAccess.getAngleAccess().getValueAssignment_1(), "rule__Angle__ValueAssignment_1");
					put(grammarAccess.getAngleAccess().getUnitAssignment_2(), "rule__Angle__UnitAssignment_2");
					put(grammarAccess.getTimeAccess().getOpAssignment_0(), "rule__Time__OpAssignment_0");
					put(grammarAccess.getTimeAccess().getValueAssignment_1(), "rule__Time__ValueAssignment_1");
					put(grammarAccess.getTimeAccess().getUnitAssignment_2(), "rule__Time__UnitAssignment_2");
					put(grammarAccess.getFrequencyAccess().getOpAssignment_0(), "rule__Frequency__OpAssignment_0");
					put(grammarAccess.getFrequencyAccess().getValueAssignment_1(), "rule__Frequency__ValueAssignment_1");
					put(grammarAccess.getFrequencyAccess().getUnitAssignment_2(), "rule__Frequency__UnitAssignment_2");
					put(grammarAccess.getUriAccess().getValueAssignment_2(), "rule__Uri__ValueAssignment_2");
					put(grammarAccess.getStringValueAccess().getValueAssignment(), "rule__StringValue__ValueAssignment");
					put(grammarAccess.getHexColorAccess().getValueAssignment(), "rule__HexColor__ValueAssignment");
					put(grammarAccess.getClassAccess().getClassAssignment(), "rule__Class__ClassAssignment");
					put(grammarAccess.getAttributeAccess().getNameAssignment_2(), "rule__Attribute__NameAssignment_2");
					put(grammarAccess.getAttributeAccess().getValueAssignment_4(), "rule__Attribute__ValueAssignment_4");
					put(grammarAccess.getAttributeValueAccess().getOperatorAssignment_0(), "rule__AttributeValue__OperatorAssignment_0");
					put(grammarAccess.getAttributeValueAccess().getValueAssignment_2(), "rule__AttributeValue__ValueAssignment_2");
					put(grammarAccess.getPseudoAccess().getPseudoAssignment_1_0(), "rule__Pseudo__PseudoAssignment_1_0");
					put(grammarAccess.getPseudoAccess().getPseudoAssignment_1_1_0(), "rule__Pseudo__PseudoAssignment_1_1_0");
					put(grammarAccess.getPseudoAccess().getArgsAssignment_1_1_3_0(), "rule__Pseudo__ArgsAssignment_1_1_3_0");
					put(grammarAccess.getCSSIdAccess().getValueAssignment(), "rule__CSSId__ValueAssignment");
					put(grammarAccess.getUnaryOperatorAccess().getOperatorAssignment(), "rule__UnaryOperator__OperatorAssignment");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.papyrus.infra.gmfdiag.ui.contentassist.antlr.internal.InternalCSSParser typedParser = (org.eclipse.papyrus.infra.gmfdiag.ui.contentassist.antlr.internal.InternalCSSParser) parser;
			typedParser.entryRuleStylesheet();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_ML_COMMENT" };
	}
	
	public CSSGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(CSSGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
