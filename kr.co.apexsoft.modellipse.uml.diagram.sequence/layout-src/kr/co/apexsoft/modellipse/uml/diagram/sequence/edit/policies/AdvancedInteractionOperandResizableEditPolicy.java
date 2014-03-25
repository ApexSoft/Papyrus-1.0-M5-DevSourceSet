package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.util.AdvancedOperandBoundsComputeHelper;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;

public class AdvancedInteractionOperandResizableEditPolicy extends ResizableEditPolicy {

	public AdvancedInteractionOperandResizableEditPolicy() {
		super();
		setDragAllowed(false);
	}
	
	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		EditPart host = getHost();
		if((request.getResizeDirection() & PositionConstants.EAST_WEST) != 0) {
			EditPart parent = host.getParent().getParent();
			return parent.getCommand(request);
		} else {
			if(host instanceof InteractionOperandEditPart && host.getParent()
					instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {
				InteractionOperandEditPart currentIOEP = (InteractionOperandEditPart)host;
				CombinedFragmentCombinedFragmentCompartmentEditPart compartEP =
						(CombinedFragmentCombinedFragmentCompartmentEditPart)host.getParent();
				int heightDelta = request.getSizeDelta().height();
				return AdvancedOperandBoundsComputeHelper.createIOEPResizeCommand(
						currentIOEP, heightDelta, compartEP, request);
			}
		}
		return null;
	}

}
