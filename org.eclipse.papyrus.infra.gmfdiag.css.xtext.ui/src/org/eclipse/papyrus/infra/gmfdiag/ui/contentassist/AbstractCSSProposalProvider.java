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
package org.eclipse.papyrus.infra.gmfdiag.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.*;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;

/**
 * Represents a generated, default implementation of interface {@link IProposalProvider}.
 * Methods are dynamically dispatched on the first parameter, i.e., you can override them 
 * with a more concrete subtype. 
 */
@SuppressWarnings("all")
public class AbstractCSSProposalProvider extends org.eclipse.xtext.ui.editor.contentassist.AbstractJavaBasedContentProposalProvider {
		
	public void completeStylesheet_Charset(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeStylesheet_Imports(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeStylesheet_Contents(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeImport_String(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeImport_Uri(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeImport_Media(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeMedia_Media(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeMedia_Rules(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePage_Pseudo(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePage_Declarations(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePseudoPage_Id(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeRuleset_Selectors(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeRuleset_Properties(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeCompositeSelector_Left(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeCompositeSelector_Combinator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeCompositeSelector_Right(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeSimpleSelector_ElementName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)((Alternatives)assignment.getTerminal()).getElements().get(0)), context, acceptor);
		// subclasses may override
	}
	public void completeSimpleSelector_Condition(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeCombinator_Kind(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeDeclaration_Property(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeDeclaration_Expression(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeDeclaration_Important(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeExpression_Terms(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeExpression_Subterms(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeSubterm_Operator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeSubterm_Term(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeFunction_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeFunction_Args(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeName_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeNumber_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeNumber_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePercentage_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePercentage_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePercentage_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeLength_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeLength_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeLength_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeEms_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeEms_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeEms_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeExs_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeExs_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeExs_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAngle_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAngle_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAngle_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeTime_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeTime_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeTime_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeFrequency_Op(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeFrequency_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeFrequency_Unit(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeUri_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeStringValue_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeHexColor_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeClass_Class(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAttribute_Name(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAttribute_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAttributeValue_Operator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeAttributeValue_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)((Alternatives)assignment.getTerminal()).getElements().get(0)), context, acceptor);
		completeRuleCall(((RuleCall)((Alternatives)assignment.getTerminal()).getElements().get(1)), context, acceptor);
	}
	public void completePseudo_Pseudo(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completePseudo_Args(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeCSSId_Value(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	public void completeUnaryOperator_Operator(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
    
	public void complete_Stylesheet(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Content(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Import(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Media(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Page(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_PseudoPage(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Ruleset(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Selector(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_CompositeSelector(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_SimpleSelector(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_SelectorCondition(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Combinator(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Declaration(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Expression(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Subterm(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Term(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Function(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Name(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Number(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Percentage(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Length(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Ems(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Exs(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Angle(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Time(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Frequency(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Uri(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_StringValue(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_HexColor(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Operator(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Class(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Attribute(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_AttributeValue(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_Pseudo(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_CSSId(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_UnaryOperator(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_UNARY(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_KIND(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_ATTRIBUTE_OP(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_HASH(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_T_CLASS(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_T_IMPORTANT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_PC_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_LEN_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_EM_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_EX_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_ANGLE_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_TIME_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_FREQ_UNIT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_T_NUM(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_ID(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_STRING(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_CDO(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_CDC(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_ML_COMMENT(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_WS(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
	public void complete_ANY_OTHER(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// subclasses may override
	}
}
