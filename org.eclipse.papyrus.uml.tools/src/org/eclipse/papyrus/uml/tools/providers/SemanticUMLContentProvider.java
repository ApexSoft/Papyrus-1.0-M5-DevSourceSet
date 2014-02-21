/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.tools.providers;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.emf.providers.strategy.SemanticEMFContentProvider;
import org.eclipse.papyrus.infra.widgets.Activator;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.papyrus.uml.tools.utils.UMLUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

/**
 * A semantic Hierarchic Content Provider for UML
 * 
 * @author Camille Letavernier
 */
public class SemanticUMLContentProvider extends SemanticEMFContentProvider {


	public SemanticUMLContentProvider() {
		//Empty (@see #inputChanged(Viewer, Object, Object))
	}

	public SemanticUMLContentProvider(EObject editedEObject, EStructuralFeature feature, EObject[] roots) {
		super(editedEObject, feature, roots);
	}

	public SemanticUMLContentProvider(EObject editedEObject, EStructuralFeature feature) {
		this(editedEObject, feature, findRoots(editedEObject));
	}

	public SemanticUMLContentProvider(EObject[] roots) {
		super(roots);
	}

	public SemanticUMLContentProvider(ResourceSet root) {
		this(null, null, root);
	}

	public SemanticUMLContentProvider(EObject editedEObject, EStructuralFeature feature, ResourceSet root) {
		this(editedEObject, feature, getRoots(root));
	}

	protected static EObject[] findRoots(EObject source) {
		if(source.eResource() == null || source.eResource().getResourceSet() == null) {
			return SemanticEMFContentProvider.findRoots(source);
		}

		//We have a full resourceSet : we return its contents
		return getRoots(source.eResource().getResourceSet());
	}

	protected static EObject[] getRoots(ResourceSet root) {
		if(root instanceof ModelSet) {
			ModelSet modelSet = (ModelSet)root;
			//TODO : Find all semantic roots, including Models and Libraries
			//This is related to the ModelSet evolution 
			try {
				EObject rootElement = UmlUtils.getUmlModel(modelSet).lookupRoot();
				if(rootElement == null) {
					return new EObject[0];
				}

				Resource rootResource = rootElement.eResource();
				if(rootResource == null) {
					return new EObject[]{ rootElement };
				}

				List<EObject> rootObjects = new LinkedList<EObject>();
				for(EObject rootObject : rootResource.getContents()) {
					if(rootObject instanceof Element) {
						rootObjects.add(rootObject);
					}
				}

				if(rootObjects.isEmpty()) {
					return new EObject[]{ rootElement };
				}

				return rootObjects.toArray(new EObject[0]);
			} catch (NotFoundException ex) {
				Activator.log.error(ex);
			}
		}

		return SemanticEMFContentProvider.getRoots(root);
	}

	@Override
	protected boolean isCompatibleMetaclass(Object containerElement, Object metaclass) {
		Element semanticElement = UMLUtil.resolveUMLElement(containerElement);

		if(semanticElement == null) {
			return false;
		}

		if(metaclass instanceof Stereotype) {
			Stereotype stereotype = (Stereotype)metaclass;
			boolean res = semanticElement.getAppliedStereotype(stereotype.getQualifiedName()) != null;
			if(!res) {
				EClass definition = stereotype.getDefinition();
				for(EObject e : semanticElement.getStereotypeApplications()) {
					EClass c = e.eClass();
					if(definition != null && definition.isSuperTypeOf(c)) {
						res = true;
						break;
					}
				}
			}
			return res;
		}

		//TODO : We should use super.isCompatibleMetaclass(), but the super-implementation 
		//may not be compatible with our implementation of getAdaptedValue()
		if(metaclass instanceof EClassifier) {
			return ((EClassifier)metaclass).isInstance(semanticElement);
		}

		return false;
	}

	/**
	 * This method should return either the StereotypeApplication (For Sto - Sto associations),
	 * or the UML Element (For Sto - UML associations)
	 * 
	 * This depends on the wanted metaclass.
	 */
	//TODO : In some cases, we may have a filter based on both a UML Metaclass and a Stereotype
	//In such a specific case, a specific implementation is probably needed
	//
	//This case may especially occur in the case of dynamic creation of stereotype associations.
	@Override
	public Object getAdaptedValue(Object containerElement) {
		Object semanticElement = super.getAdaptedValue(containerElement);

		if(semanticElement instanceof Element) {
			Element element = (Element)semanticElement;
			//Looks for a compatible Stereotype application
			for(Object metaclassWanted : getWantedMetaclasses()) {

				if(metaclassWanted instanceof Stereotype) {
					EObject stereotypeApplication = null;

					stereotypeApplication = element.getStereotypeApplication((Stereotype)metaclassWanted);
					if(stereotypeApplication == null) {
						List<Stereotype> subStereotypes = element.getAppliedSubstereotypes((Stereotype)metaclassWanted);
						for(Stereotype subSteretoype : subStereotypes) {
							stereotypeApplication = element.getStereotypeApplication(subSteretoype);
							if(stereotypeApplication != null) {
								break;
							}
						}
					}

					if(stereotypeApplication != null) {
						return stereotypeApplication;
					}
				}
			}
		}

		//If no stereotype application is found, return the UML Element
		return semanticElement;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		ResourceSet root = null;

		if(newInput instanceof ResourceSet) {
			root = (ResourceSet)newInput;
		} else if(newInput instanceof ServicesRegistry) {
			try {
				root = ServiceUtils.getInstance().getModelSet((ServicesRegistry)newInput);
			} catch (Exception ex) {
				Activator.log.error(ex);
			}
		}

		if(root != null) {
			this.roots = getRoots(root);
		}

		super.inputChanged(viewer, oldInput, newInput);
	}
}
