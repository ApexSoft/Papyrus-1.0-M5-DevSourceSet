/*
* generated by Xtext
*/
package org.eclipse.papyrus.uml.textedit.collaborationuse.xtext.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators= {org.eclipse.xtext.validation.ImportUriValidator.class, org.eclipse.xtext.validation.NamesAreUniqueValidator.class})
public class AbstractUmlCollaborationUseJavaValidator extends org.eclipse.papyrus.uml.textedit.common.xtext.validation.UmlCommonJavaValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(org.eclipse.papyrus.uml.textedit.collaborationuse.xtext.umlCollaborationUse.UmlCollaborationUsePackage.eINSTANCE);
		return result;
	}

}
