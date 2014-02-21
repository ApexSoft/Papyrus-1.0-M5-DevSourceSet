/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.common.edit.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.gmf.diagram.common.edit.policy.CompartmentSemanticEditPolicy;
import org.eclipse.papyrus.infra.services.edit.commands.ConfigureFeatureCommandFactory;
import org.eclipse.papyrus.infra.services.edit.commands.IConfigureCommandFactory;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.papyrus.uml.tools.utils.NamedElementUtil;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Semantic edit policy for {@link ConstraintBlock} parameters (Property) compartment.
 */
public class ConstraintParameterCompartmentSemanticEditPolicy extends CompartmentSemanticEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {

		if(UMLElementTypes.PROPERTY == req.getElementType()) {
			String name = NamedElementUtil.getDefaultNameWithIncrementFromBase("parameter", req.getContainer().eContents()); //$NON-NLS-1$
			req.setParameter(IConfigureCommandFactory.CONFIGURE_COMMAND_FACTORY_ID, new ConfigureFeatureCommandFactory(UMLPackage.eINSTANCE.getNamedElement_Name(), name));
		}

		return super.getCreateCommand(req);
	}
}
