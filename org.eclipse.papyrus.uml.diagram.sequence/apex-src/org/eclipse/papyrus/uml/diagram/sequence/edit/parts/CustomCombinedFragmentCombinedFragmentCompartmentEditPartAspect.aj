package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.OperandOrderingLayout;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomCombinedFragmentCombinedFragmentCompartmentEditPart;

public aspect CustomCombinedFragmentCombinedFragmentCompartmentEditPartAspect {

	after() returning(IFigure retVal) :
		execution(* CustomCombinedFragmentCombinedFragmentCompartmentEditPart.createFigure()) {
		if (retVal instanceof ResizableCompartmentFigure) {
			ResizableCompartmentFigure figure = (ResizableCompartmentFigure) retVal;
			figure.getScrollPane().setHorizontalScrollBarVisibility(ScrollPane.NEVER);
			figure.getScrollPane().setVerticalScrollBarVisibility(ScrollPane.NEVER);	// add
			figure.setFitContents(true);	// add
			IFigure contentPane = figure.getContentPane();
			if (contentPane != null) {
				contentPane.setLayoutManager(new OperandOrderingLayout(false));	// changed from XYLayout
			}
		}
	}
}
