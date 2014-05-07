package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts;

import java.util.Iterator;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d.OperandXYLayout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomInteractionOperandEditPart;

public class AdvancedInteractionOperandEditPart extends
		CustomInteractionOperandEditPart {

	public AdvancedInteractionOperandEditPart(View view) {
		super(view);
	}

	@Override
	protected IFigure createNodeShape() {
		IFigure figure = super.createNodeShape();
		LayoutManager oldLayout = figure.getLayoutManager();
		LayoutManager newLayout = new OperandXYLayout(this);
		Iterator<?> iter = figure.getChildren().iterator();
		while (iter.hasNext()) {
			Object child = iter.next();
			if (child instanceof IFigure) {
				Object constraint = oldLayout.getConstraint((IFigure) child);
				newLayout.setConstraint((IFigure) child, constraint);
			}
		}
		figure.setLayoutManager(newLayout);
		return figure;
	}

}
