/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.service.types.matcher;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;
import org.eclipse.papyrus.sysml.requirements.Satisfy;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Test if current {@link Abstraction} is a {@link Satisfy}
 */
public class SatisfyMatcher implements IElementMatcher {

	public boolean matches(EObject eObject) {

		boolean isMatch = false;
		if(eObject instanceof Abstraction) {

			Abstraction element = (Abstraction)eObject;
			if(UMLUtil.getStereotypeApplication(element, Satisfy.class) != null) {
				isMatch = true;
			}
		}
		return isMatch;
	}

}
