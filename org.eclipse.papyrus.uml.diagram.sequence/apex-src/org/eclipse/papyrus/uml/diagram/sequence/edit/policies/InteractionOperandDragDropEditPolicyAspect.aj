package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.InteractionOperandDragDropEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;

public aspect InteractionOperandDragDropEditPolicyAspect {

	Command around(InteractionOperandDragDropEditPolicy editPolicy, ChangeBoundsRequest request) :
		execution(* InteractionOperandDragDropEditPolicy.getResizeCommand(ChangeBoundsRequest)) &&
		this(editPolicy) && args(request) {
		EditPart host = editPolicy.getHost();
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
				return createIOEPResizeCommand(currentIOEP, heightDelta, compartEP, request);
			}
		}
		return null;
	}
	
	Command createIOEPResizeCommand(InteractionOperandEditPart currentIOEP, int heightDelta,
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP, ChangeBoundsRequest request) {
		CompositeCommand compositeCommand = new CompositeCommand("Resize Operand");
		
		int direction = request.getResizeDirection();
		boolean isSnap = request.isSnapToEnabled();	// ALT key
		boolean isParentResize = false;
		
		InteractionOperandEditPart previousIOEP = null;
		InteractionOperandEditPart latterIPEP = null;
		if ((direction & PositionConstants.NORTH) != 0) {
			previousIOEP = OperandBoundsComputeHelper.findPreviousIOEP(compartEP, currentIOEP);
			latterIPEP = currentIOEP;
		} else if ((direction & PositionConstants.SOUTH) != 0) {
			previousIOEP = currentIOEP;
			latterIPEP = OperandBoundsComputeHelper.findLatterIOEP(compartEP, currentIOEP);
		}
		
		if (previousIOEP == null || latterIPEP == null) {
			Rectangle rect = currentIOEP.getFigure().getClientArea();
			rect.height += heightDelta;
			ICommand updateBoundsCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(currentIOEP, rect);
			if (updateBoundsCommand != null) {
				compositeCommand.add(updateBoundsCommand);
			}
			
//			isParentResize = currentIOEP == OperandBoundsComputeHelper.findLastIOEP(compartEP);
		} else {
			if ((direction & PositionConstants.NORTH) != 0) {
				heightDelta = -heightDelta;
			}			
			
			Rectangle previousIOEPRect = previousIOEP.getFigure().getClientArea();
			Rectangle latterIPEORect = latterIPEP.getFigure().getClientArea();
			
			if (heightDelta < 0) {
				Dimension minSize = previousIOEP.getFigure().getMinimumSize(previousIOEPRect.width, previousIOEPRect.height);
				int height = Math.max(previousIOEPRect.height + heightDelta, minSize.height);
				heightDelta = height - previousIOEPRect.height;
			} else {
				Dimension minSize = latterIPEP.getFigure().getMinimumSize(latterIPEORect.width, latterIPEORect.height);
				int height = Math.max(latterIPEORect.height - heightDelta, minSize.height);
				heightDelta = latterIPEORect.height - height;
			}
			
			previousIOEPRect.height += heightDelta;
			compositeCommand.add(OperandBoundsComputeHelper
					.createUpdateEditPartBoundsCommand(previousIOEP, previousIOEPRect));
			
			if (!isSnap) {
				latterIPEORect.x += heightDelta;
				latterIPEORect.height -= heightDelta;
				compositeCommand.add(OperandBoundsComputeHelper
						.createUpdateEditPartBoundsCommand(latterIPEP, latterIPEORect));
			} else {
				isParentResize = previousIOEP == OperandBoundsComputeHelper.findFirstIOEP(compartEP);
			}
		}
		
		if (isParentResize && compartEP.getParent() instanceof CombinedFragmentEditPart) {
			CombinedFragmentEditPart cfEP = (CombinedFragmentEditPart) compartEP.getParent();
			Rectangle cfEPRect = cfEP.getFigure().getClientArea();
			cfEPRect.height += heightDelta;
			compositeCommand.add(OperandBoundsComputeHelper
					.createUpdateEditPartBoundsCommand(cfEP, cfEPRect));
		}
		
		return new ICommandProxy(compositeCommand);
	}
}
