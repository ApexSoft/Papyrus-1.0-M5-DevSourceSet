package kr.co.apexsoft.modellipse.uml.diagram.sequence.figures;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d.LifelineXYLayout;

import org.eclipse.draw2d.LayoutManager;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;

public class LifelineDotLineAdvancedFigure extends LifelineDotLineCustomFigure {

	/**
	 * Layout manager for the inline mode
	 */
	private LayoutManager normalModeManager;
	
	public LifelineDotLineAdvancedFigure() {
		super();
		normalModeManager = new LifelineXYLayout();
		setLayoutManager(normalModeManager);
	}

	@Override
	public void configure(boolean inlineMode, int innerConnectableElementsNumber) {
		super.configure(inlineMode, innerConnectableElementsNumber);
		if (!inlineMode) {
			setLayoutManager(normalModeManager);
		}
	}

}
