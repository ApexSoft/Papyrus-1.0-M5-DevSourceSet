/*****************************************************************************
 * Copyright (c) 2010 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Soyatec - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.semantic.CustomConstraintConstrainedElementItemSemanticEditPolicy;

/**
 * @author Jin Liu (jin.liu@soyatec.com)
 */
public class CustomConstraintConstrainedElementEditPart extends ConstraintConstrainedElementEditPart {

	/**
	 * Constructor.
	 * 
	 * @param view
	 */
	public CustomConstraintConstrainedElementEditPart(View view) {
		super(view);
	}

	/**
	 * @Override
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CustomConstraintConstrainedElementItemSemanticEditPolicy());
	}
}
