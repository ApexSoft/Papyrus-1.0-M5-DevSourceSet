/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.search.ui.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

/**
 * 
 * A generic implementation of participant validator that works on EMF basis
 * 
 */
public class ParticipantValidator implements IParticipantValidator {

	private static ParticipantValidator instance = null;

	private ParticipantValidator() {
		super();
	}

	public final static ParticipantValidator getInstance() {

		if(ParticipantValidator.instance == null) {
			synchronized(ParticipantValidator.class) {
				if(ParticipantValidator.instance == null) {
					ParticipantValidator.instance = new ParticipantValidator();
				}
			}
		}
		return ParticipantValidator.instance;
	}

	public Collection<EObject> getParticipants(EObject root, Object[] participantsTypes) {

		List<Object> participantsTypesList = Arrays.asList(participantsTypes);
		List<EObject> results = new ArrayList<EObject>();



		// ... and all its content
		TreeIterator<EObject> it = root.eAllContents();
		while(it.hasNext()) {
			EObject modelElement = (EObject)it.next();

			//Check that metaclass of this element is a supported metaclass
			EClass e = modelElement.eClass();
			if(participantsTypesList.contains(modelElement.eClass())) {
				results.add(modelElement);
			}
		}

		return results;
	}


	public Collection<EObject> getParticipantsStereotype(EObject root, Object[] participantsTypes) {

		List<Object> participantsTypesList = Arrays.asList(participantsTypes);
		List<EObject> results = new ArrayList<EObject>();

		// Evaluate root...
		if(participantsTypesList.contains(root.eClass())) {
			results.add(root);
		}

		// ... and all its content
		TreeIterator<EObject> it = root.eAllContents();
		while(it.hasNext()) {
			EObject modelElement = (EObject)it.next();
			if(modelElement instanceof Element) {
				for(Stereotype appliedStereotype : ((Element)modelElement).getAppliedStereotypes()) {
					//Check that metaclass of this element is a supported metaclass
					for(Object stereotypeToGet : participantsTypesList) {
						if(EcoreUtil.equals(appliedStereotype, (EObject)stereotypeToGet)) {
							results.add(modelElement);
						}
					}
				}
			}
		}

		return results;
	}
}
