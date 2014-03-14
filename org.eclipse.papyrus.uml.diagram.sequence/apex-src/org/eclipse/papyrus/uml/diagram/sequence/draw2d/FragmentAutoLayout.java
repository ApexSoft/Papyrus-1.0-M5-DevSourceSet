package org.eclipse.papyrus.uml.diagram.sequence.draw2d;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.InteractionFragment;

public class FragmentAutoLayout implements LayoutListener {

	private View view;
	private EditPartViewer viewer;
	
	public FragmentAutoLayout(IGraphicalEditPart editPart) {
		this.view = editPart.getNotationView();
		this.viewer = editPart.getViewer();
	}
	
	public void invalidate(IFigure container) {
	}

	public boolean layout(IFigure container) {
		return false;
	}

	public void postLayout(IFigure container) {
		EObject eObject = ViewUtil.resolveSemanticElement(view);
		if (eObject instanceof InteractionFragment) {
			InteractionFragment fragment = (InteractionFragment) eObject;
			FragmentLayoutDelegator delegator = new FragmentLayoutDelegator(fragment, viewer);
			delegator.getPreferredSize(container);
			delegator.layout(container);;
		}
	}

	public void remove(IFigure child) {
	}

	public void setConstraint(IFigure child, Object constraint) {
	}

}
