package org.eclipse.papyrus.uml.diagram.sequence.aspect;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.FragmentAutoLayout;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomInteractionInteractionCompartmentEditPart;

public aspect FragmentAutoLayoutInInteraction {
	
	public IFigure CustomInteractionInteractionCompartmentEditPart.createFigure() {
		ResizableCompartmentFigure f = (ResizableCompartmentFigure) super.createFigure();
		f.getContentPane().addLayoutListener(new FragmentAutoLayout(this));
		return f;
	}

}
