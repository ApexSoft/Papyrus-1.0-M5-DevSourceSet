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
package org.eclipse.papyrus.uml.diagram.clazz.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ViewComponentEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.ConnectionEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.custom.figure.AddedLinkFigure;
import org.eclipse.papyrus.uml.diagram.clazz.custom.policies.CustomViewComponentEditPolicy;
import org.eclipse.papyrus.uml.diagram.clazz.custom.policies.itemsemantic.CustomContainmentSubLinkItemSemanticEditPolicy;

/**
 * @generated
 */
public class ContainmentSubLinkEditPart extends ConnectionEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4022;

	/**
	 * @generated
	 */
	public ContainmentSubLinkEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ViewComponentEditPolicy());
		removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CustomViewComponentEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CustomContainmentSubLinkItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected Connection createConnectionFigure() {
		return new AddedLinkFigure();
	}

	/**
	 * @generated
	 */
	public AddedLinkFigure getPrimaryShape() {
		return (AddedLinkFigure)getFigure();
	}
}
