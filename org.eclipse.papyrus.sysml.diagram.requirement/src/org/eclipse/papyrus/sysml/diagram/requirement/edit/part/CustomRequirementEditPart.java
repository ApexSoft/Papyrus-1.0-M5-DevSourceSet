/*****************************************************************************
 * Copyright (c) 2012 ATOS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Olivier Mélois (ATOS) - Initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.papyrus.sysml.diagram.requirement.edit.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.requirement.figure.CustomRequirementFigure;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassEditPart;
import org.eclipse.papyrus.uml.diagram.common.Activator;
import org.eclipse.papyrus.uml.diagram.common.commands.ShowHideCompartmentRequest;
import org.eclipse.papyrus.uml.diagram.common.figure.node.PackageFigure;
import org.eclipse.uml2.uml.NamedElement;

public class CustomRequirementEditPart extends ClassEditPart {

	//protected static final String ICONS_PATH = "/org.eclipse.papyrus.sysml/icons/requirements/Requirement.gif"; //$NON-NLS-1$
	
	public CustomRequirementEditPart(View view) {
		super(view);
	}

	
	@Override
	protected IFigure createNodeShape() {
		//Showing the information compartment.
		View notationView = getNotationView();
		Request request = new ShowHideCompartmentRequest(ShowHideCompartmentRequest.SHOW, notationView);
		request.setType(ShowHideCompartmentRequest.SHOW_HIDE_COMPARTMENT);
		Command showCompartmentCommand = this.getCommand(request);
		getEditDomain().getCommandStack().execute(showCompartmentCommand);
		
		primaryShape = new CustomRequirementFigure(); //$NON-NLS-1$
		//((CustomRequirementFigure)primaryShape).setTagIcon(Activator.getPluginIconImage(Activator.ID, ICONS_PATH));
		return primaryShape;
	}

	public void refreshTitle() {
		NamedElement clazz = (NamedElement)((View)this.getModel()).getElement();
		String requirementName = clazz.getName();
		((CustomRequirementFigure)this.getFigure()).setName(requirementName);
	}

	@Override
	protected boolean addFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof CustomRequirementInformationCompartmentEditPart) {
			IFigure pane = ((CustomRequirementFigure)getPrimaryShape()).getRequirementIdInformationCompartmentFigure();
			setupContentPane(pane); // FIXME each compartment should handle his content pane in his own way 
			pane.add(((CustomRequirementInformationCompartmentEditPart)childEditPart).getFigure());
			return true;
		}
		return super.addFixedChild(childEditPart);
	}

	@Override
	protected boolean removeFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof CustomRequirementInformationCompartmentEditPart) {
			IFigure pane = ((CustomRequirementFigure)getPrimaryShape()).getRequirementIdInformationCompartmentFigure();
			setupContentPane(pane); // FIXME each compartment should handle his content pane in his own way 
			pane.remove(((CustomRequirementInformationCompartmentEditPart)childEditPart).getFigure());
			return true;
		}
		return super.removeFixedChild(childEditPart);
	}

	@Override
	public IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		if(editPart instanceof CustomRequirementInformationCompartmentEditPart) {
			return ((CustomRequirementFigure)getPrimaryShape()).getRequirementIdInformationCompartmentFigure();
		}
		return super.getContentPaneFor(editPart);
	}

}
