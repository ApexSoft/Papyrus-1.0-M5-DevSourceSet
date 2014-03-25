package kr.co.apexsoft.modellipse.uml.diagram.sequence.providers;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedCombinedFragmentCreationEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionCompartmentXYLayoutEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionOperandComponentEditPolicy;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies.AdvancedInteractionOperandResizableEditPolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.PapyrusCreationEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionInteractionCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.PackageEditPart;

public class AdvancedEditPolicyProvider implements IEditPolicyProvider {

	@Override
	public void addProviderChangeListener(IProviderChangeListener listener) {
	}

	@Override
	public boolean provides(IOperation operation) {
		CreateEditPoliciesOperation epOperation = (CreateEditPoliciesOperation)operation;
		if(!(epOperation.getEditPart() instanceof GraphicalEditPart) && !(epOperation.getEditPart() instanceof ConnectionEditPart)) {
			return false;
		}
		EditPart gep = epOperation.getEditPart();
		String diagramType = ((View)gep.getModel()).getDiagram().getType();
		if(PackageEditPart.MODEL_ID.equals(diagramType)) {
			return true;
		}
		return false;
	}

	@Override
	public void removeProviderChangeListener(IProviderChangeListener listener) {
	}

	@Override
	public void createEditPolicies(EditPart editPart) {
		installCombinedFragmentCreationEditPolicy(editPart);
		installLayoutEditPolicy(editPart);
		installPrimaryDragEditPolicy(editPart);
		installCreationEditPolicy(editPart);
		installComponentEditPolicy(editPart);
	}
	
	private void installCombinedFragmentCreationEditPolicy(EditPart editPart) {
		if (editPart instanceof InteractionInteractionCompartmentEditPart ||
				editPart instanceof InteractionOperandEditPart) {
			editPart.installEditPolicy(EditPolicyRoles.CREATION_ROLE,
					new AdvancedCombinedFragmentCreationEditPolicy());
		}
	}

	private void installLayoutEditPolicy(EditPart editPart) {
		if (editPart instanceof InteractionInteractionCompartmentEditPart) {
			editPart.installEditPolicy(EditPolicy.LAYOUT_ROLE,
					new AdvancedInteractionCompartmentXYLayoutEditPolicy());
		}
	}
	
	private void installPrimaryDragEditPolicy(EditPart editPart) {
		if (editPart instanceof InteractionOperandEditPart) {
			editPart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
					new AdvancedInteractionOperandResizableEditPolicy());
		}
	}

	private void installCreationEditPolicy(EditPart editPart) {
		if (editPart instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {
			editPart.installEditPolicy(EditPolicyRoles.CREATION_ROLE,
					new PapyrusCreationEditPolicy());
		}
	}
	
	private void installComponentEditPolicy(EditPart editPart) {
		if (editPart instanceof InteractionOperandEditPart) {
			editPart.installEditPolicy(EditPolicy.COMPONENT_ROLE,
					new AdvancedInteractionOperandComponentEditPolicy());
		}
	}
}
