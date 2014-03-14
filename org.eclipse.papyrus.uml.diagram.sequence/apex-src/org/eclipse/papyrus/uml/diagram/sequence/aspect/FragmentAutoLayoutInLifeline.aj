package org.eclipse.papyrus.uml.diagram.sequence.aspect;

import org.eclipse.papyrus.uml.diagram.sequence.draw2d.LifelineXYLayout;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;

public privileged aspect FragmentAutoLayoutInLifeline {
	
	after(LifelineDotLineCustomFigure figure) returning() :
		execution(public LifelineDotLineCustomFigure.new(..)) && this(figure) {
		figure.normalModeManager = new LifelineXYLayout();
		figure.setLayoutManager(figure.normalModeManager);
	}

}
