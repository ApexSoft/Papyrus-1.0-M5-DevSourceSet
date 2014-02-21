/*
* generated by Xtext
*/
grammar InternalUmlParameter;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package org.eclipse.papyrus.uml.textedit.parameter.xtext.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.eclipse.papyrus.uml.textedit.parameter.xtext.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.eclipse.papyrus.uml.textedit.parameter.xtext.services.UmlParameterGrammarAccess;

}

@parser::members {
 
 	private UmlParameterGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(UmlParameterGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }
    
    @Override
    protected String getValueForTokenName(String tokenName) {
    	return tokenName;
    }

}




// Entry rule entryRuleParameterRule
entryRuleParameterRule 
:
{ before(grammarAccess.getParameterRuleRule()); }
	 ruleParameterRule
{ after(grammarAccess.getParameterRuleRule()); } 
	 EOF 
;

// Rule ParameterRule
ruleParameterRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getParameterRuleAccess().getGroup()); }
(rule__ParameterRule__Group__0)
{ after(grammarAccess.getParameterRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleModifiersRule
entryRuleModifiersRule 
:
{ before(grammarAccess.getModifiersRuleRule()); }
	 ruleModifiersRule
{ after(grammarAccess.getModifiersRuleRule()); } 
	 EOF 
;

// Rule ModifiersRule
ruleModifiersRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getModifiersRuleAccess().getGroup()); }
(rule__ModifiersRule__Group__0)
{ after(grammarAccess.getModifiersRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleModifierSpecification
entryRuleModifierSpecification 
:
{ before(grammarAccess.getModifierSpecificationRule()); }
	 ruleModifierSpecification
{ after(grammarAccess.getModifierSpecificationRule()); } 
	 EOF 
;

// Rule ModifierSpecification
ruleModifierSpecification
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getModifierSpecificationAccess().getValueAssignment()); }
(rule__ModifierSpecification__ValueAssignment)
{ after(grammarAccess.getModifierSpecificationAccess().getValueAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleEffectRule
entryRuleEffectRule 
:
{ before(grammarAccess.getEffectRuleRule()); }
	 ruleEffectRule
{ after(grammarAccess.getEffectRuleRule()); } 
	 EOF 
;

// Rule EffectRule
ruleEffectRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getEffectRuleAccess().getGroup()); }
(rule__EffectRule__Group__0)
{ after(grammarAccess.getEffectRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleQualifiedName
entryRuleQualifiedName 
:
{ before(grammarAccess.getQualifiedNameRule()); }
	 ruleQualifiedName
{ after(grammarAccess.getQualifiedNameRule()); } 
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getQualifiedNameAccess().getGroup()); }
(rule__QualifiedName__Group__0)
{ after(grammarAccess.getQualifiedNameAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleTypeRule
entryRuleTypeRule 
:
{ before(grammarAccess.getTypeRuleRule()); }
	 ruleTypeRule
{ after(grammarAccess.getTypeRuleRule()); } 
	 EOF 
;

// Rule TypeRule
ruleTypeRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getTypeRuleAccess().getGroup()); }
(rule__TypeRule__Group__0)
{ after(grammarAccess.getTypeRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleMultiplicityRule
entryRuleMultiplicityRule 
:
{ before(grammarAccess.getMultiplicityRuleRule()); }
	 ruleMultiplicityRule
{ after(grammarAccess.getMultiplicityRuleRule()); } 
	 EOF 
;

// Rule MultiplicityRule
ruleMultiplicityRule
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getGroup()); }
(rule__MultiplicityRule__Group__0)
{ after(grammarAccess.getMultiplicityRuleAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleBoundSpecification
entryRuleBoundSpecification 
:
{ before(grammarAccess.getBoundSpecificationRule()); }
	 ruleBoundSpecification
{ after(grammarAccess.getBoundSpecificationRule()); } 
	 EOF 
;

// Rule BoundSpecification
ruleBoundSpecification
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getBoundSpecificationAccess().getValueAssignment()); }
(rule__BoundSpecification__ValueAssignment)
{ after(grammarAccess.getBoundSpecificationAccess().getValueAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleUnlimitedLiteral
entryRuleUnlimitedLiteral 
:
{ before(grammarAccess.getUnlimitedLiteralRule()); }
	 ruleUnlimitedLiteral
{ after(grammarAccess.getUnlimitedLiteralRule()); } 
	 EOF 
;

// Rule UnlimitedLiteral
ruleUnlimitedLiteral
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getUnlimitedLiteralAccess().getAlternatives()); }
(rule__UnlimitedLiteral__Alternatives)
{ after(grammarAccess.getUnlimitedLiteralAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}




// Rule ModifierKind
ruleModifierKind
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierKindAccess().getAlternatives()); }
(rule__ModifierKind__Alternatives)
{ after(grammarAccess.getModifierKindAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Rule EffectKind
ruleEffectKind
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectKindAccess().getAlternatives()); }
(rule__EffectKind__Alternatives)
{ after(grammarAccess.getEffectKindAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Rule VisibilityKind
ruleVisibilityKind
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVisibilityKindAccess().getAlternatives()); }
(rule__VisibilityKind__Alternatives)
{ after(grammarAccess.getVisibilityKindAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Rule Direction
ruleDirection
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getDirectionAccess().getAlternatives()); }
(rule__Direction__Alternatives)
{ after(grammarAccess.getDirectionAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__ParameterRule__Alternatives_4
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getTypeAssignment_4_0()); }
(rule__ParameterRule__TypeAssignment_4_0)
{ after(grammarAccess.getParameterRuleAccess().getTypeAssignment_4_0()); }
)

    |(
{ before(grammarAccess.getParameterRuleAccess().getUndefinedKeyword_4_1()); }

	'<Undefined>' 

{ after(grammarAccess.getParameterRuleAccess().getUndefinedKeyword_4_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__UnlimitedLiteral__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getUnlimitedLiteralAccess().getINTTerminalRuleCall_0()); }
	RULE_INT
{ after(grammarAccess.getUnlimitedLiteralAccess().getINTTerminalRuleCall_0()); }
)

    |(
{ before(grammarAccess.getUnlimitedLiteralAccess().getAsteriskKeyword_1()); }

	'*' 

{ after(grammarAccess.getUnlimitedLiteralAccess().getAsteriskKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierKind__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierKindAccess().getEXCEPTIONEnumLiteralDeclaration_0()); }
(	'exception' 
)
{ after(grammarAccess.getModifierKindAccess().getEXCEPTIONEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getModifierKindAccess().getSTREAMEnumLiteralDeclaration_1()); }
(	'stream' 
)
{ after(grammarAccess.getModifierKindAccess().getSTREAMEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getModifierKindAccess().getORDEREDEnumLiteralDeclaration_2()); }
(	'ordered' 
)
{ after(grammarAccess.getModifierKindAccess().getORDEREDEnumLiteralDeclaration_2()); }
)

    |(
{ before(grammarAccess.getModifierKindAccess().getUNIQUEEnumLiteralDeclaration_3()); }
(	'unique' 
)
{ after(grammarAccess.getModifierKindAccess().getUNIQUEEnumLiteralDeclaration_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__EffectKind__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectKindAccess().getCREATEEnumLiteralDeclaration_0()); }
(	'create' 
)
{ after(grammarAccess.getEffectKindAccess().getCREATEEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getEffectKindAccess().getREADEnumLiteralDeclaration_1()); }
(	'read' 
)
{ after(grammarAccess.getEffectKindAccess().getREADEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getEffectKindAccess().getUPDATEEnumLiteralDeclaration_2()); }
(	'update' 
)
{ after(grammarAccess.getEffectKindAccess().getUPDATEEnumLiteralDeclaration_2()); }
)

    |(
{ before(grammarAccess.getEffectKindAccess().getDELETEEnumLiteralDeclaration_3()); }
(	'delete' 
)
{ after(grammarAccess.getEffectKindAccess().getDELETEEnumLiteralDeclaration_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__VisibilityKind__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getVisibilityKindAccess().getPublicEnumLiteralDeclaration_0()); }
(	'+' 
)
{ after(grammarAccess.getVisibilityKindAccess().getPublicEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getVisibilityKindAccess().getPrivateEnumLiteralDeclaration_1()); }
(	'-' 
)
{ after(grammarAccess.getVisibilityKindAccess().getPrivateEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getVisibilityKindAccess().getProtectedEnumLiteralDeclaration_2()); }
(	'#' 
)
{ after(grammarAccess.getVisibilityKindAccess().getProtectedEnumLiteralDeclaration_2()); }
)

    |(
{ before(grammarAccess.getVisibilityKindAccess().getPackageEnumLiteralDeclaration_3()); }
(	'~' 
)
{ after(grammarAccess.getVisibilityKindAccess().getPackageEnumLiteralDeclaration_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Direction__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getDirectionAccess().getINEnumLiteralDeclaration_0()); }
(	'in' 
)
{ after(grammarAccess.getDirectionAccess().getINEnumLiteralDeclaration_0()); }
)

    |(
{ before(grammarAccess.getDirectionAccess().getOUTEnumLiteralDeclaration_1()); }
(	'out' 
)
{ after(grammarAccess.getDirectionAccess().getOUTEnumLiteralDeclaration_1()); }
)

    |(
{ before(grammarAccess.getDirectionAccess().getINOUTEnumLiteralDeclaration_2()); }
(	'inout' 
)
{ after(grammarAccess.getDirectionAccess().getINOUTEnumLiteralDeclaration_2()); }
)

    |(
{ before(grammarAccess.getDirectionAccess().getRETURNEnumLiteralDeclaration_3()); }
(	'return' 
)
{ after(grammarAccess.getDirectionAccess().getRETURNEnumLiteralDeclaration_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__ParameterRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__0__Impl
	rule__ParameterRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getVisibilityAssignment_0()); }
(rule__ParameterRule__VisibilityAssignment_0)
{ after(grammarAccess.getParameterRuleAccess().getVisibilityAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__1__Impl
	rule__ParameterRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getDirectionAssignment_1()); }
(rule__ParameterRule__DirectionAssignment_1)
{ after(grammarAccess.getParameterRuleAccess().getDirectionAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__2__Impl
	rule__ParameterRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getNameAssignment_2()); }
(rule__ParameterRule__NameAssignment_2)
{ after(grammarAccess.getParameterRuleAccess().getNameAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__3__Impl
	rule__ParameterRule__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getColonKeyword_3()); }

	':' 

{ after(grammarAccess.getParameterRuleAccess().getColonKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__4__Impl
	rule__ParameterRule__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getAlternatives_4()); }
(rule__ParameterRule__Alternatives_4)
{ after(grammarAccess.getParameterRuleAccess().getAlternatives_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__5
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__5__Impl
	rule__ParameterRule__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__5__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getMultiplicityAssignment_5()); }
(rule__ParameterRule__MultiplicityAssignment_5)?
{ after(grammarAccess.getParameterRuleAccess().getMultiplicityAssignment_5()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__6
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__6__Impl
	rule__ParameterRule__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__6__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getModifiersAssignment_6()); }
(rule__ParameterRule__ModifiersAssignment_6)?
{ after(grammarAccess.getParameterRuleAccess().getModifiersAssignment_6()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ParameterRule__Group__7
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ParameterRule__Group__7__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__Group__7__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getEffectAssignment_7()); }
(rule__ParameterRule__EffectAssignment_7)
{ after(grammarAccess.getParameterRuleAccess().getEffectAssignment_7()); }
)

;
finally {
	restoreStackSize(stackSize);
}


















rule__ModifiersRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__0__Impl
	rule__ModifiersRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getLeftCurlyBracketKeyword_0()); }

	'{' 

{ after(grammarAccess.getModifiersRuleAccess().getLeftCurlyBracketKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__1__Impl
	rule__ModifiersRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesAssignment_1()); }
(rule__ModifiersRule__ValuesAssignment_1)
{ after(grammarAccess.getModifiersRuleAccess().getValuesAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__2__Impl
	rule__ModifiersRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getGroup_2()); }
(rule__ModifiersRule__Group_2__0)*
{ after(grammarAccess.getModifiersRuleAccess().getGroup_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getRightCurlyBracketKeyword_3()); }

	'}' 

{ after(grammarAccess.getModifiersRuleAccess().getRightCurlyBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__ModifiersRule__Group_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group_2__0__Impl
	rule__ModifiersRule__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getCommaKeyword_2_0()); }

	',' 

{ after(grammarAccess.getModifiersRuleAccess().getCommaKeyword_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__ModifiersRule__Group_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__ModifiersRule__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__Group_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesAssignment_2_1()); }
(rule__ModifiersRule__ValuesAssignment_2_1)
{ after(grammarAccess.getModifiersRuleAccess().getValuesAssignment_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__EffectRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__EffectRule__Group__0__Impl
	rule__EffectRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EffectRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectRuleAccess().getLeftCurlyBracketKeyword_0()); }

	'{' 

{ after(grammarAccess.getEffectRuleAccess().getLeftCurlyBracketKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__EffectRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__EffectRule__Group__1__Impl
	rule__EffectRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EffectRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectRuleAccess().getEffectKeyword_1()); }

	'effect: ' 

{ after(grammarAccess.getEffectRuleAccess().getEffectKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__EffectRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__EffectRule__Group__2__Impl
	rule__EffectRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EffectRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectRuleAccess().getEffectKindAssignment_2()); }
(rule__EffectRule__EffectKindAssignment_2)
{ after(grammarAccess.getEffectRuleAccess().getEffectKindAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__EffectRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__EffectRule__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EffectRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectRuleAccess().getRightCurlyBracketKeyword_3()); }

	'}' 

{ after(grammarAccess.getEffectRuleAccess().getRightCurlyBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__QualifiedName__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedName__Group__0__Impl
	rule__QualifiedName__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getPathAssignment_0()); }
(rule__QualifiedName__PathAssignment_0)
{ after(grammarAccess.getQualifiedNameAccess().getPathAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedName__Group__1__Impl
	rule__QualifiedName__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1()); }

	'::' 

{ after(grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__QualifiedName__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__QualifiedName__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2()); }
(rule__QualifiedName__RemainingAssignment_2)?
{ after(grammarAccess.getQualifiedNameAccess().getRemainingAssignment_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__TypeRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__TypeRule__Group__0__Impl
	rule__TypeRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getPathAssignment_0()); }
(rule__TypeRule__PathAssignment_0)?
{ after(grammarAccess.getTypeRuleAccess().getPathAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__TypeRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__TypeRule__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getTypeAssignment_1()); }
(rule__TypeRule__TypeAssignment_1)
{ after(grammarAccess.getTypeRuleAccess().getTypeAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__MultiplicityRule__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__0__Impl
	rule__MultiplicityRule__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getLeftSquareBracketKeyword_0()); }

	'[' 

{ after(grammarAccess.getMultiplicityRuleAccess().getLeftSquareBracketKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__1__Impl
	rule__MultiplicityRule__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_1()); }
(rule__MultiplicityRule__BoundsAssignment_1)
{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__2__Impl
	rule__MultiplicityRule__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getGroup_2()); }
(rule__MultiplicityRule__Group_2__0)?
{ after(grammarAccess.getMultiplicityRuleAccess().getGroup_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getRightSquareBracketKeyword_3()); }

	']' 

{ after(grammarAccess.getMultiplicityRuleAccess().getRightSquareBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}










rule__MultiplicityRule__Group_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group_2__0__Impl
	rule__MultiplicityRule__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getFullStopFullStopKeyword_2_0()); }

	'..' 

{ after(grammarAccess.getMultiplicityRuleAccess().getFullStopFullStopKeyword_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__MultiplicityRule__Group_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__MultiplicityRule__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__Group_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_2_1()); }
(rule__MultiplicityRule__BoundsAssignment_2_1)
{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsAssignment_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}







rule__ParameterRule__VisibilityAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getVisibilityVisibilityKindEnumRuleCall_0_0()); }
	ruleVisibilityKind{ after(grammarAccess.getParameterRuleAccess().getVisibilityVisibilityKindEnumRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__DirectionAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getDirectionDirectionEnumRuleCall_1_0()); }
	ruleDirection{ after(grammarAccess.getParameterRuleAccess().getDirectionDirectionEnumRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__NameAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getNameIDTerminalRuleCall_2_0()); }
	RULE_ID{ after(grammarAccess.getParameterRuleAccess().getNameIDTerminalRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__TypeAssignment_4_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getTypeTypeRuleParserRuleCall_4_0_0()); }
	ruleTypeRule{ after(grammarAccess.getParameterRuleAccess().getTypeTypeRuleParserRuleCall_4_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__MultiplicityAssignment_5
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getMultiplicityMultiplicityRuleParserRuleCall_5_0()); }
	ruleMultiplicityRule{ after(grammarAccess.getParameterRuleAccess().getMultiplicityMultiplicityRuleParserRuleCall_5_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__ModifiersAssignment_6
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getModifiersModifiersRuleParserRuleCall_6_0()); }
	ruleModifiersRule{ after(grammarAccess.getParameterRuleAccess().getModifiersModifiersRuleParserRuleCall_6_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ParameterRule__EffectAssignment_7
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getParameterRuleAccess().getEffectEffectRuleParserRuleCall_7_0()); }
	ruleEffectRule{ after(grammarAccess.getParameterRuleAccess().getEffectEffectRuleParserRuleCall_7_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__ValuesAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_1_0()); }
	ruleModifierSpecification{ after(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifiersRule__ValuesAssignment_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_2_1_0()); }
	ruleModifierSpecification{ after(grammarAccess.getModifiersRuleAccess().getValuesModifierSpecificationParserRuleCall_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__ModifierSpecification__ValueAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModifierSpecificationAccess().getValueModifierKindEnumRuleCall_0()); }
	ruleModifierKind{ after(grammarAccess.getModifierSpecificationAccess().getValueModifierKindEnumRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__EffectRule__EffectKindAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEffectRuleAccess().getEffectKindEffectKindEnumRuleCall_2_0()); }
	ruleEffectKind{ after(grammarAccess.getEffectRuleAccess().getEffectKindEffectKindEnumRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__PathAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getPathNamespaceCrossReference_0_0()); }
(
{ before(grammarAccess.getQualifiedNameAccess().getPathNamespaceIDTerminalRuleCall_0_0_1()); }
	RULE_ID{ after(grammarAccess.getQualifiedNameAccess().getPathNamespaceIDTerminalRuleCall_0_0_1()); }
)
{ after(grammarAccess.getQualifiedNameAccess().getPathNamespaceCrossReference_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__QualifiedName__RemainingAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getQualifiedNameAccess().getRemainingQualifiedNameParserRuleCall_2_0()); }
	ruleQualifiedName{ after(grammarAccess.getQualifiedNameAccess().getRemainingQualifiedNameParserRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__PathAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getPathQualifiedNameParserRuleCall_0_0()); }
	ruleQualifiedName{ after(grammarAccess.getTypeRuleAccess().getPathQualifiedNameParserRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__TypeRule__TypeAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getTypeRuleAccess().getTypeTypeCrossReference_1_0()); }
(
{ before(grammarAccess.getTypeRuleAccess().getTypeTypeIDTerminalRuleCall_1_0_1()); }
	RULE_ID{ after(grammarAccess.getTypeRuleAccess().getTypeTypeIDTerminalRuleCall_1_0_1()); }
)
{ after(grammarAccess.getTypeRuleAccess().getTypeTypeCrossReference_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__BoundsAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_1_0()); }
	ruleBoundSpecification{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__MultiplicityRule__BoundsAssignment_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_2_1_0()); }
	ruleBoundSpecification{ after(grammarAccess.getMultiplicityRuleAccess().getBoundsBoundSpecificationParserRuleCall_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__BoundSpecification__ValueAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getBoundSpecificationAccess().getValueUnlimitedLiteralParserRuleCall_0()); }
	ruleUnlimitedLiteral{ after(grammarAccess.getBoundSpecificationAccess().getValueUnlimitedLiteralParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_INTEGER_VALUE : (('0'|'1'..'9' ('_'? '0'..'9')*)|('0b'|'0B') '0'..'1' ('_'? '0'..'1')*|('0x'|'0X') ('0'..'9'|'a'..'f'|'A'..'F') ('_'? ('0'..'9'|'a'..'f'|'A'..'F'))*|'0' '_'? '0'..'7' ('_'? '0'..'7')*);

RULE_ID : (('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*|'\'' ( options {greedy=false;} : . )*'\'');

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_ML_COMMENT : '/*' ~('@') ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'|'@'))* ('\r'? '\n')?;

RULE_INT : ('0'..'9')+;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


