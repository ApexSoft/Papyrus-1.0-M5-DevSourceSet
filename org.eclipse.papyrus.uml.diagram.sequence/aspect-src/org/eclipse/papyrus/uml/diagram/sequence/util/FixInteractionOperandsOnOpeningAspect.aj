package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.FixInteractionOperandsOnOpening;

public aspect FixInteractionOperandsOnOpeningAspect {

	void around(FixInteractionOperandsOnOpening util, Diagram diagram) :
		execution(* fix(Diagram)) && target(util) && args(diagram) {
		String IAO_ID = "" + InteractionOperandEditPart.VISUAL_ID;
		// Parse diagram content
		Iterator<EObject> it = diagram.eAllContents();
		while(it.hasNext()) {
			EObject current = it.next();
			// Select only shapes
			if(!(current instanceof Shape)) {
				continue;
			}
			String currentType = ((Shape)current).getType();
			if(IAO_ID.equals(currentType)) {
				Shape iaOperandShape = (Shape)current;
				View parentDecoration = ViewUtil.getViewContainer(iaOperandShape);
				if(parentDecoration != null) {
					View parentParentView = ViewUtil.getViewContainer(parentDecoration);
					if(parentParentView instanceof Shape) {
						Shape parentShape = (Shape)parentParentView;
						Bounds iaOperandShapeBounds = (Bounds)iaOperandShape.getLayoutConstraint();
						Bounds parentShapeBounds = (Bounds)parentShape.getLayoutConstraint();
						
						if (iaOperandShapeBounds == null || parentShapeBounds == null) {
							continue;
						}
						
						if((iaOperandShapeBounds.getX() == 0) && (iaOperandShapeBounds.getY() == 0) && (iaOperandShapeBounds.getWidth() == -1)) {
							// distribute operands equally within the combined fragment.
							int size = parentDecoration.getChildren().size();
							int index = parentDecoration.getChildren().indexOf(iaOperandShape);
							int height = (parentShapeBounds.getHeight() - util.TOP_HEIGHT) / size;
							int y = index * height;
							final Rectangle newBounds = new Rectangle(0, y, parentShapeBounds.getWidth() - 2, height);
							// Fix when current location is not the valid location (only possible if parent size is set)
							TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);
							Command fixCommand = util.new FixLocationCommand(editingDomain, "Fix combined fragment on opening", iaOperandShapeBounds, newBounds);
							editingDomain.getCommandStack().execute(fixCommand);
						}
					}
				}
			}
		}
	}
	
}
