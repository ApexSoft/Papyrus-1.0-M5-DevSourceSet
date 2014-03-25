package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d.FragmentAutoLayout;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedCombinedFragmentCreationEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionCompartmentXYLayoutEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomInteractionInteractionCompartmentEditPart;

public class AdvancedInteractionInteractionCompartmentEditPart extends
		CustomInteractionInteractionCompartmentEditPart {

	public AdvancedInteractionInteractionCompartmentEditPart(View view) {
		super(view);
	}

	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new AdvancedCombinedFragmentCreationEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AdvancedInteractionCompartmentXYLayoutEditPolicy());
	}

	@Override
	public IFigure createFigure() {
		ResizableCompartmentFigure f = (ResizableCompartmentFigure) super.createFigure();
		f.getContentPane().addLayoutListener(new FragmentAutoLayout(this));
		return f;
	}

}
