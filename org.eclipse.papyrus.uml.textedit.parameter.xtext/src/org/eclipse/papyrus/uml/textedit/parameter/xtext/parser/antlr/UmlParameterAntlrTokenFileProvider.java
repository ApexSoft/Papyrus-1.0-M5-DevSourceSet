/*
* generated by Xtext
*/
package org.eclipse.papyrus.uml.textedit.parameter.xtext.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class UmlParameterAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/papyrus/uml/textedit/parameter/xtext/parser/antlr/internal/InternalUmlParameter.tokens");
	}
}
