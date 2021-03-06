/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.uml.diagram.clazz.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.extendedtypes.types.IExtendedHintedElementType;
import org.eclipse.papyrus.infra.extendedtypes.util.ElementTypeUtils;
import org.eclipse.papyrus.uml.diagram.clazz.edit.commands.NestedClassForComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.clazz.edit.commands.NestedDataTypeForComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.clazz.edit.commands.NestedEnumerationForComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.clazz.edit.commands.NestedInterfaceForComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.clazz.edit.commands.NestedPrimitiveTypeForComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.clazz.edit.commands.NestedSignalForComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;

/**
 * @generated
 */
public class ComponentNestedClassifierCompartmentItemSemanticEditPolicyCN extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ComponentNestedClassifierCompartmentItemSemanticEditPolicyCN() {
		super(UMLElementTypes.Component_3021);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		IElementType requestElementType = req.getElementType();
		if(requestElementType == null) {
			return super.getCreateCommand(req);
		}
		IElementType baseElementType = requestElementType;
		boolean isExtendedType = false;
		if(requestElementType instanceof IExtendedHintedElementType) {
			baseElementType = ElementTypeUtils.getClosestDiagramType(requestElementType);
			if(baseElementType != null) {
				isExtendedType = true;
			} else {
				// no reference element type ID. using the closest super element type to give more opportunities, but can lead to bugs.
				baseElementType = ElementTypeUtils.findClosestNonExtendedElementType((IExtendedHintedElementType)requestElementType);
				isExtendedType = true;
			}
		}
		if(UMLElementTypes.Class_3004 == baseElementType) {
			if(isExtendedType) {
				return getExtendedTypeCreationCommand(req, (IExtendedHintedElementType)requestElementType);
			}
			return getGEFWrapper(new NestedClassForComponentCreateCommand(req));
		}
		if(UMLElementTypes.Interface_3037 == baseElementType) {
			if(isExtendedType) {
				return getExtendedTypeCreationCommand(req, (IExtendedHintedElementType)requestElementType);
			}
			return getGEFWrapper(new NestedInterfaceForComponentCreateCommand(req));
		}
		if(UMLElementTypes.Enumeration_3053 == baseElementType) {
			if(isExtendedType) {
				return getExtendedTypeCreationCommand(req, (IExtendedHintedElementType)requestElementType);
			}
			return getGEFWrapper(new NestedEnumerationForComponentCreateCommand(req));
		}
		if(UMLElementTypes.PrimitiveType_3046 == baseElementType) {
			if(isExtendedType) {
				return getExtendedTypeCreationCommand(req, (IExtendedHintedElementType)requestElementType);
			}
			return getGEFWrapper(new NestedPrimitiveTypeForComponentCreateCommand(req));
		}
		if(UMLElementTypes.DataType_3045 == baseElementType) {
			if(isExtendedType) {
				return getExtendedTypeCreationCommand(req, (IExtendedHintedElementType)requestElementType);
			}
			return getGEFWrapper(new NestedDataTypeForComponentCreateCommand(req));
		}
		if(UMLElementTypes.Signal_3051 == baseElementType) {
			if(isExtendedType) {
				return getExtendedTypeCreationCommand(req, (IExtendedHintedElementType)requestElementType);
			}
			return getGEFWrapper(new NestedSignalForComponentCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
