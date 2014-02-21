/*****************************************************************************
 * Copyright (c) 2014 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		 Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.common.editpolicies;

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;

/**
 * 
 * This edit policy allows to resize parent from NORTH, NORTH_WEST or WEST without moving its children
 * 
 */
public class XYLayoutWithConstrainedResizedEditPolicy extends XYLayoutEditPolicy {

	/**
	 * Returns the <code>Command</code> to resize a group of children.
	 * 
	 * @param request
	 *        the ChangeBoundsRequest
	 * @return the Command
	 */
	protected Command getChangeConstraintCommand(ChangeBoundsRequest request) {
		final CompoundCommand resize = new CompoundCommand("Resize Command");//$NON-NLS-1$
		GraphicalEditPart child;
		final List<?> children = request.getEditParts();
		final Point move = request.getMoveDelta();
		final int direction = request.getResizeDirection();
		boolean forceLocation = request.isConstrainedResize() && (direction == PositionConstants.WEST || direction == PositionConstants.NORTH || direction == PositionConstants.NORTH_WEST);
		for(int i = 0; i < children.size(); i++) {
			child = (GraphicalEditPart)children.get(i);
			resize.add(createChangeConstraintCommand(request, child, translateToModelConstraint(getConstraintFor(request, child))));
			if(forceLocation) {
				for(Object object : child.getChildren()) {
					if(object instanceof CompartmentEditPart) {
						final EditPolicy layoutPolicy = ((CompartmentEditPart)object).getEditPolicy(EditPolicy.LAYOUT_ROLE);
						if(layoutPolicy instanceof org.eclipse.gef.editpolicies.XYLayoutEditPolicy) {
							for(final Object current : ((CompartmentEditPart)object).getChildren()) {
								if(current instanceof NodeEditPart) {
									final ChangeBoundsRequest forceLocationRequest = new ChangeBoundsRequest();
									forceLocationRequest.setType("move");//$NON-NLS-1$
									forceLocationRequest.setMoveDelta(move.getNegated());
									forceLocationRequest.setEditParts((EditPart)current);
									final Command tmp = ((NodeEditPart)current).getCommand(forceLocationRequest);
									if(tmp != null) {
										resize.add(tmp);
									}
								}
							}
						}
					}
				}
			}
		}
		return resize.unwrap();
	}
}
