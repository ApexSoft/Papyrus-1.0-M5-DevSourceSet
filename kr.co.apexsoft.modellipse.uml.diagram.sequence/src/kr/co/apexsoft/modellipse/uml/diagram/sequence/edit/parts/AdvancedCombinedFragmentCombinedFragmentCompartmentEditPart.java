package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d.OperandOrderingLayout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.PapyrusCreationEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomCombinedFragmentCombinedFragmentCompartmentEditPart;

public class AdvancedCombinedFragmentCombinedFragmentCompartmentEditPart extends
		CustomCombinedFragmentCombinedFragmentCompartmentEditPart {

	public AdvancedCombinedFragmentCombinedFragmentCompartmentEditPart(View view) {
		super(view);
	}

	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new PapyrusCreationEditPolicy());
	}

	@Override
	public IFigure createFigure() {
		IFigure f = super.createFigure();
		if (f instanceof ResizableCompartmentFigure) {
			ResizableCompartmentFigure figure = (ResizableCompartmentFigure) f;
			figure.getScrollPane().setHorizontalScrollBarVisibility(ScrollPane.NEVER);
			figure.getScrollPane().setVerticalScrollBarVisibility(ScrollPane.NEVER);	// add
			figure.setFitContents(true);	// add
			IFigure contentPane = figure.getContentPane();
			if (contentPane != null) {
				contentPane.setLayoutManager(new OperandOrderingLayout(false));	// changed from XYLayout
			}
		}
		return f;
	}
}
