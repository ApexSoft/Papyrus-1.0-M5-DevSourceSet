package kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.uml2.uml.InteractionFragment;

public class FragmentLayoutDelegator {

	private InteractionFragment fContainer;
	private EditPartViewer fViewer;
	
	private Dimension fPreferredSize;
	
	public FragmentLayoutDelegator(InteractionFragment container, EditPartViewer viewer) {
		this.fContainer = container;
		this.fViewer = viewer;
	}
	
	public void invalidate() {
		fPreferredSize = null;
	}

	public void layout(IFigure parent) {
		
	}
	
	public Dimension getPreferredSize(IFigure parent) {
		if (fPreferredSize == null) {
			fPreferredSize = calculatePreferredSize(parent);
		}
		return fPreferredSize;
	}
	
	private Dimension calculatePreferredSize(IFigure f) {
		return null;
	}

}
