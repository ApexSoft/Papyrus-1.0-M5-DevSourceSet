package org.eclipse.papyrus.uml.diagram.sequence.aspect;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.OperandXYLayout;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomInteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart.CustomInteractionOperandFigure;

public aspect FragmentAutoLayoutInOperand {

	after(CustomInteractionOperandEditPart editPart) returning(IFigure retVal) :
		execution(protected * CustomInteractionOperandEditPart.createNodeShape()) &&
		this(editPart) {
		if (retVal instanceof CustomInteractionOperandFigure) {
			CustomInteractionOperandFigure figure = (CustomInteractionOperandFigure) retVal;
			LayoutManager oldLayout = figure.getLayoutManager();
			LayoutManager newLayout = new OperandXYLayout(editPart);
			Iterator<?> iter = figure.getChildren().iterator();
			while (iter.hasNext()) {
				Object child = iter.next();
				if (child instanceof IFigure) {
					Object constraint = oldLayout.getConstraint((IFigure) child);
					newLayout.setConstraint((IFigure) child, constraint);
				}
			}
			figure.setLayoutManager(newLayout);
		}
	}
	
}
