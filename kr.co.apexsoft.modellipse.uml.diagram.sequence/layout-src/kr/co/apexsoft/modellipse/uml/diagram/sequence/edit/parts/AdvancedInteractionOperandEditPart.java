package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts;

import java.util.Iterator;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d.OperandXYLayout;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedCombinedFragmentCreationEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionOperandComponentEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionOperandItemSemanticEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionOperandResizableEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomInteractionOperandEditPart;

public class AdvancedInteractionOperandEditPart extends
		CustomInteractionOperandEditPart {

	public AdvancedInteractionOperandEditPart(View view) {
		super(view);
	}

	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new AdvancedInteractionOperandItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new AdvancedCombinedFragmentCreationEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new AdvancedInteractionOperandResizableEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AdvancedInteractionOperandComponentEditPolicy());
	}

	@Override
	protected IFigure createNodeShape() {
		IFigure figure = super.createNodeShape();
		LayoutManager oldLayout = figure.getLayoutManager();
		LayoutManager newLayout = new OperandXYLayout(this);
		Iterator<?> iter = figure.getChildren().iterator();
		while (iter.hasNext()) {
			Object child = iter.next();
			if (child instanceof IFigure) {
				Object constraint = oldLayout.getConstraint((IFigure) child);
				newLayout.setConstraint((IFigure) child, constraint);
			}
		}
		figure.setLayoutManager(newLayout);
		return figure;
	}

}
