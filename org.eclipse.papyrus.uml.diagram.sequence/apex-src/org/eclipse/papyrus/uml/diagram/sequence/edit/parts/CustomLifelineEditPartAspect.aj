package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.LifelineXYLayout;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomLifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomLifelineEditPart.CustomLifelineFigure;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;

public privileged aspect CustomLifelineEditPartAspect {
	
	after() returning(IFigure retVal) : execution(* CustomLifelineEditPart.createNodeShape()) {
		if (retVal instanceof CustomLifelineFigure) {
			CustomLifelineFigure figure = (CustomLifelineFigure) retVal;
			LifelineDotLineCustomFigure dotLineFigure = figure.getFigureLifelineDotLineFigure();
			dotLineFigure.normalModeManager = new LifelineXYLayout();
			dotLineFigure.setLayoutManager(dotLineFigure.normalModeManager);
		}
	}
}
