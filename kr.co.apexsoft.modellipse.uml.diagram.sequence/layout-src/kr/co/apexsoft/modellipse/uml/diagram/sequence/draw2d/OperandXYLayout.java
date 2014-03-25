package kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.InteractionFragment;

public class OperandXYLayout extends XYLayout {

	private View view;
	private InteractionFragment container;
	private EditPartViewer viewer;

	public OperandXYLayout(IGraphicalEditPart editPart) {
		if (editPart != null) {
			this.view = editPart.getNotationView();
			this.container = (InteractionFragment) ViewUtil.resolveSemanticElement(view);
			this.viewer = editPart.getViewer();
		}
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
		Dimension preferredSize = super.calculatePreferredSize(f, wHint, hHint);
		Dimension d = getFragmentLayoutDelegator().getPreferredSize(f);
		d.union(getBorderPreferredSize(f));
		d.union(preferredSize);
		return d;
	}

	@Override
	public void layout(IFigure parent) {
		super.layout(parent);
		getFragmentLayoutDelegator().layout(parent);
	}
	
	
	@Override
	public void invalidate() {
		super.invalidate();
		getFragmentLayoutDelegator().invalidate();
	}

	/**
	 * fragment layout delegator
	 */
	private FragmentLayoutDelegator delegator;
	
	private FragmentLayoutDelegator getFragmentLayoutDelegator() {
		if (delegator == null) {
			delegator = new FragmentLayoutDelegator(container, viewer);
		}
		return delegator;
	}
}
