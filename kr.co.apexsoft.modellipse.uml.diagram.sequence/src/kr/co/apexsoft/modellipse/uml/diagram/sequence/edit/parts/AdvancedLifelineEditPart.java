package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.figures.LifelineDotLineAdvancedFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomLifelineEditPart;

public class AdvancedLifelineEditPart extends CustomLifelineEditPart {
	
	public class AdvancedLifelineFigure extends CustomLifelineFigure {

		@Override
		protected void createCompositeFigureStructure() {
			super.createCompositeFigureStructure();
			createContents();
		}
		
		private void createContents() {
			fFigureExecutionsContainerFigure.remove(fFigureLifelineDotLineFigure);
			fFigureLifelineDotLineFigure = new LifelineDotLineAdvancedFigure();
			fFigureExecutionsContainerFigure.add(fFigureLifelineDotLineFigure);
		}
		
	}

	public AdvancedLifelineEditPart(View view) {
		super(view);
	}

	@Override
	protected IFigure createNodeShape() {
		return primaryShape = new AdvancedLifelineFigure();
	}

}
