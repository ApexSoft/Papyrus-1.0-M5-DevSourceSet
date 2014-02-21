/*
* generated by Xtext
*/
grammar InternalUmlParameter;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.eclipse.papyrus.uml.textedit.parameter.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.papyrus.uml.textedit.parameter.xtext.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.papyrus.uml.textedit.parameter.xtext.services.UmlParameterGrammarAccess;

}

@parser::members {

 	private UmlParameterGrammarAccess grammarAccess;
 	
    public InternalUmlParameterParser(TokenStream input, UmlParameterGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "ParameterRule";	
   	}
   	
   	@Override
   	protected UmlParameterGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleParameterRule
entryRuleParameterRule returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getParameterRuleRule()); }
	 iv_ruleParameterRule=ruleParameterRule 
	 { $current=$iv_ruleParameterRule.current; } 
	 EOF 
;

// Rule ParameterRule
ruleParameterRule returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getParameterRuleAccess().getVisibilityVisibilityKindEnumRuleCall_0_0()); 
	    }
		lv_visibility_0_0=ruleVisibilityKind		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRuleRule());
	        }
       		set(
       			$current, 
       			"visibility",
        		lv_visibility_0_0, 
        		"VisibilityKind");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getParameterRuleAccess().getDirectionDirectionEnumRuleCall_1_0()); 
	    }
		lv_direction_1_0=ruleDirection		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRuleRule());
	        }
       		set(
       			$current, 
       			"direction",
        		lv_direction_1_0, 
        		"Direction");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		lv_name_2_0=RULE_ID
		{
			newLeafNode(lv_name_2_0, grammarAccess.getParameterRuleAccess().getNameIDTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getParameterRuleRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"ID");
	    }

)
)	otherlv_3=':' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getParameterRuleAccess().getColonKeyword_3());
    }
((
(
		{ 
	        newCompositeNode(grammarAccess.getParameterRuleAccess().getTypeTypeRuleParserRuleCall_4_0_0()); 
	    }
		lv_type_4_0=ruleTypeRule		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRuleRule());
	        }
       		set(
       			$current, 
       			"type",
        		lv_type_4_0, 
        		"TypeRule");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |	otherlv_5='<Undefined>' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getParameterRuleAccess().getUndefinedKeyword_4_1());
    }
)(
(
		{ 
	        newCompositeNode(grammarAccess.getParameterRuleAccess().getMultiplicityMultiplicityRuleParserRuleCall_5_0()); 
	    }
		lv_multiplicity_6_0=ruleMultiplicityRule		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRuleRule());
	        }
       		set(
       			$current, 
       			"multiplicity",
        		lv_multiplicity_6_0, 
        		"MultiplicityRule");
	        afterParserOrEnumRuleCall();
	    }

)
)?(
(
		{ 
	        newCompositeNode(grammarAccess.getParameterRuleAccess().getModifiersModifiersRuleParserRuleCall_6_0()); 
	    }
		lv_modifiers_7_0=ruleModifiersRule		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRuleRule());
	        }
       		set(
       			$current, 
       			"modifiers",
        		lv_modifiers_7_0, 
        		"ModifiersRule");
	        afterParserOrEnumRuleCall();
	    }

)
)?(
(
		{ 
	        newCompositeNode(grammarAccess.getParameterRuleAccess().getEffectEffectRuleParserRuleCall_7_0()); 
	    }
		lv_effect_8_0=ruleEffectRule		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getParameterRuleRule());
	        }
       		set(
       			$current, 
       			"effect",
        		lv_effect_8_0, 
        		"EffectRule");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleModifiersRule
entryRuleModifiersRule returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModifiersRuleRule()); }
	 iv_ruleModifiersRule=ruleModifiersRule 
	 { $current=$iv_ruleModifiersRule.current; } 
	 EOF 
;

// Rule ModifiersRule
ruleModifiersRule returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='{' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getModifiersRuleAccess().getLeftCurlyBracketKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_1_0()); 
	    }
		lv_values_1_0=ruleModifierSpecification		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModifiersRuleRule());
	        }
       		add(
       			$current, 
       			"values",
        		lv_values_1_0, 
        		"ModifierSpecification");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2=',' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getModifiersRuleAccess().getCommaKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_2_1_0()); 
	    }
		lv_values_3_0=ruleModifierSpecification		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModifiersRuleRule());
	        }
       		add(
       			$current, 
       			"values",
        		lv_values_3_0, 
        		"ModifierSpecification");
	        afterParserOrEnumRuleCall();
	    }

)
))*	otherlv_4='}' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getModifiersRuleAccess().getRightCurlyBracketKeyword_3());
    }
)
;





// Entry rule entryRuleModifierSpecification
entryRuleModifierSpecification returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModifierSpecificationRule()); }
	 iv_ruleModifierSpecification=ruleModifierSpecification 
	 { $current=$iv_ruleModifierSpecification.current; } 
	 EOF 
;

// Rule ModifierSpecification
ruleModifierSpecification returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getModifierSpecificationAccess().getValueModifierKindEnumRuleCall_0()); 
	    }
		lv_value_0_0=ruleModifierKind		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModifierSpecificationRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"ModifierKind");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleEffectRule
entryRuleEffectRule returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getEffectRuleRule()); }
	 iv_ruleEffectRule=ruleEffectRule 
	 { $current=$iv_ruleEffectRule.current; } 
	 EOF 
;

// Rule EffectRule
ruleEffectRule returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='{' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getEffectRuleAccess().getLeftCurlyBracketKeyword_0());
    }
	otherlv_1='effect: ' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getEffectRuleAccess().getEffectKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getEffectRuleAccess().getEffectKindEffectKindEnumRuleCall_2_0()); 
	    }
		lv_effectKind_2_0=ruleEffectKind		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getEffectRuleRule());
	        }
       		set(
       			$current, 
       			"effectKind",
        		lv_effectKind_2_0, 
        		"EffectKind");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_3='}' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getEffectRuleAccess().getRightCurlyBracketKeyword_3());
    }
)
;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); }
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current; } 
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getQualifiedNameRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getQualifiedNameAccess().getPathNamespaceCrossReference_0_0()); 
	}

)
)	otherlv_1='::' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getQualifiedNameAccess().getRemainingQualifiedNameParserRuleCall_2_0()); 
	    }
		lv_remaining_2_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getQualifiedNameRule());
	        }
       		set(
       			$current, 
       			"remaining",
        		lv_remaining_2_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)?)
;





// Entry rule entryRuleTypeRule
entryRuleTypeRule returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getTypeRuleRule()); }
	 iv_ruleTypeRule=ruleTypeRule 
	 { $current=$iv_ruleTypeRule.current; } 
	 EOF 
;

// Rule TypeRule
ruleTypeRule returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getTypeRuleAccess().getPathQualifiedNameParserRuleCall_0_0()); 
	    }
		lv_path_0_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTypeRuleRule());
	        }
       		set(
       			$current, 
       			"path",
        		lv_path_0_0, 
        		"QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)?(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTypeRuleRule());
	        }
        }
	otherlv_1=RULE_ID
	{
		newLeafNode(otherlv_1, grammarAccess.getTypeRuleAccess().getTypeTypeCrossReference_1_0()); 
	}

)
))
;





// Entry rule entryRuleMultiplicityRule
entryRuleMultiplicityRule returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMultiplicityRuleRule()); }
	 iv_ruleMultiplicityRule=ruleMultiplicityRule 
	 { $current=$iv_ruleMultiplicityRule.current; } 
	 EOF 
;

// Rule MultiplicityRule
ruleMultiplicityRule returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='[' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getMultiplicityRuleAccess().getLeftSquareBracketKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_1_0()); 
	    }
		lv_bounds_1_0=ruleBoundSpecification		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMultiplicityRuleRule());
	        }
       		add(
       			$current, 
       			"bounds",
        		lv_bounds_1_0, 
        		"BoundSpecification");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2='..' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getMultiplicityRuleAccess().getFullStopFullStopKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_2_1_0()); 
	    }
		lv_bounds_3_0=ruleBoundSpecification		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMultiplicityRuleRule());
	        }
       		add(
       			$current, 
       			"bounds",
        		lv_bounds_3_0, 
        		"BoundSpecification");
	        afterParserOrEnumRuleCall();
	    }

)
))?	otherlv_4=']' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getMultiplicityRuleAccess().getRightSquareBracketKeyword_3());
    }
)
;





// Entry rule entryRuleBoundSpecification
entryRuleBoundSpecification returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getBoundSpecificationRule()); }
	 iv_ruleBoundSpecification=ruleBoundSpecification 
	 { $current=$iv_ruleBoundSpecification.current; } 
	 EOF 
;

// Rule BoundSpecification
ruleBoundSpecification returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getBoundSpecificationAccess().getValueUnlimitedLiteralParserRuleCall_0()); 
	    }
		lv_value_0_0=ruleUnlimitedLiteral		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getBoundSpecificationRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"UnlimitedLiteral");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleUnlimitedLiteral
entryRuleUnlimitedLiteral returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getUnlimitedLiteralRule()); } 
	 iv_ruleUnlimitedLiteral=ruleUnlimitedLiteral 
	 { $current=$iv_ruleUnlimitedLiteral.current.getText(); }  
	 EOF 
;

// Rule UnlimitedLiteral
ruleUnlimitedLiteral returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_INT_0=RULE_INT    {
		$current.merge(this_INT_0);
    }

    { 
    newLeafNode(this_INT_0, grammarAccess.getUnlimitedLiteralAccess().getINTTerminalRuleCall_0()); 
    }

    |
	kw='*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getUnlimitedLiteralAccess().getAsteriskKeyword_1()); 
    }
)
    ;





// Rule ModifierKind
ruleModifierKind returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='exception' 
	{
        $current = grammarAccess.getModifierKindAccess().getEXCEPTIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getModifierKindAccess().getEXCEPTIONEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='stream' 
	{
        $current = grammarAccess.getModifierKindAccess().getSTREAMEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getModifierKindAccess().getSTREAMEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='ordered' 
	{
        $current = grammarAccess.getModifierKindAccess().getORDEREDEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getModifierKindAccess().getORDEREDEnumLiteralDeclaration_2()); 
    }
)
    |(	enumLiteral_3='unique' 
	{
        $current = grammarAccess.getModifierKindAccess().getUNIQUEEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_3, grammarAccess.getModifierKindAccess().getUNIQUEEnumLiteralDeclaration_3()); 
    }
));



// Rule EffectKind
ruleEffectKind returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='create' 
	{
        $current = grammarAccess.getEffectKindAccess().getCREATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getEffectKindAccess().getCREATEEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='read' 
	{
        $current = grammarAccess.getEffectKindAccess().getREADEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getEffectKindAccess().getREADEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='update' 
	{
        $current = grammarAccess.getEffectKindAccess().getUPDATEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getEffectKindAccess().getUPDATEEnumLiteralDeclaration_2()); 
    }
)
    |(	enumLiteral_3='delete' 
	{
        $current = grammarAccess.getEffectKindAccess().getDELETEEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_3, grammarAccess.getEffectKindAccess().getDELETEEnumLiteralDeclaration_3()); 
    }
));



// Rule VisibilityKind
ruleVisibilityKind returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='+' 
	{
        $current = grammarAccess.getVisibilityKindAccess().getPublicEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getVisibilityKindAccess().getPublicEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='-' 
	{
        $current = grammarAccess.getVisibilityKindAccess().getPrivateEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getVisibilityKindAccess().getPrivateEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='#' 
	{
        $current = grammarAccess.getVisibilityKindAccess().getProtectedEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getVisibilityKindAccess().getProtectedEnumLiteralDeclaration_2()); 
    }
)
    |(	enumLiteral_3='~' 
	{
        $current = grammarAccess.getVisibilityKindAccess().getPackageEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_3, grammarAccess.getVisibilityKindAccess().getPackageEnumLiteralDeclaration_3()); 
    }
));



// Rule Direction
ruleDirection returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='in' 
	{
        $current = grammarAccess.getDirectionAccess().getINEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getDirectionAccess().getINEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='out' 
	{
        $current = grammarAccess.getDirectionAccess().getOUTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getDirectionAccess().getOUTEnumLiteralDeclaration_1()); 
    }
)
    |(	enumLiteral_2='inout' 
	{
        $current = grammarAccess.getDirectionAccess().getINOUTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_2, grammarAccess.getDirectionAccess().getINOUTEnumLiteralDeclaration_2()); 
    }
)
    |(	enumLiteral_3='return' 
	{
        $current = grammarAccess.getDirectionAccess().getRETURNEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_3, grammarAccess.getDirectionAccess().getRETURNEnumLiteralDeclaration_3()); 
    }
));



RULE_INTEGER_VALUE : (('0'|'1'..'9' ('_'? '0'..'9')*)|('0b'|'0B') '0'..'1' ('_'? '0'..'1')*|('0x'|'0X') ('0'..'9'|'a'..'f'|'A'..'F') ('_'? ('0'..'9'|'a'..'f'|'A'..'F'))*|'0' '_'? '0'..'7' ('_'? '0'..'7')*);

RULE_ID : (('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*|'\'' ( options {greedy=false;} : . )*'\'');

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_ML_COMMENT : '/*' ~('@') ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'|'@'))* ('\r'? '\n')?;

RULE_INT : ('0'..'9')+;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


