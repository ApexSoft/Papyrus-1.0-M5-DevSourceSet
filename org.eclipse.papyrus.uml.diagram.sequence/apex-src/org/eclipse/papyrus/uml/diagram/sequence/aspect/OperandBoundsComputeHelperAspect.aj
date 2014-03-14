package org.eclipse.papyrus.uml.diagram.sequence.aspect;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;

public privileged aspect OperandBoundsComputeHelperAspect {

//	void around(GraphicalEditPart parent, CreateViewRequest request) :
//		execution(* OperandBoundsComputeHelper.updateCFAndIOBoundsForCFCreation(..)) &&
//		args(parent, request) {
//		
//		Object subEditPart = null;
//		if(parent instanceof InteractionInteractionCompartmentEditPart) {
//			InteractionInteractionCompartmentEditPart interactionInteractionCompartmentEditPart =
//					(InteractionInteractionCompartmentEditPart)parent;
//			subEditPart = interactionInteractionCompartmentEditPart.getChildren()
//					.get(interactionInteractionCompartmentEditPart.getChildren().size() - 1);
//		} else if(parent instanceof InteractionOperandEditPart) {
//			InteractionOperandEditPart interactionOperandEditPart = (InteractionOperandEditPart)parent;
//			subEditPart = interactionOperandEditPart.getChildren()
//					.get(interactionOperandEditPart.getChildren().size() - 1);
//		}
//		if(subEditPart != null && subEditPart instanceof CombinedFragmentEditPart) {
//			CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart)subEditPart;
//			// set bounds for CombinedFragmentEditPart
//			Rectangle cfEPAbsoluteRect = null;
//			Shape cfEPShape = (Shape)combinedFragmentEditPart.getModel();
//			
//			if(cfEPShape.getLayoutConstraint() == null) {
//				cfEPShape.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
//			}
//			
//			if(cfEPShape.getLayoutConstraint() instanceof Bounds) {
//				cfEPAbsoluteRect = new Rectangle();
//				cfEPAbsoluteRect.setLocation(request.getLocation());
//				if(request.getSize() != null) {
//					cfEPAbsoluteRect.setSize(request.getSize());
//				}
//				Rectangle cfEPRelativeRect = cfEPAbsoluteRect.getCopy();
//				combinedFragmentEditPart.getFigure().translateToRelative(cfEPRelativeRect);
//				Bounds cfEPBounds = (Bounds)cfEPShape.getLayoutConstraint();
//				OperandBoundsComputeHelper.fillBounds(cfEPBounds, cfEPRelativeRect);
//			}
//		}
//		
//	}
	
	ICommand around(EditPart editPart, CreateViewRequest request) :
		execution(* OperandBoundsComputeHelper.createUpdateCFAndIOBoundsForCFCreationCommand(..)) &&
		args(editPart, request) {
		return null;
	}

	Command around(CompoundCommand compoundCmd, ChangeBoundsRequest request, CombinedFragmentEditPart combinedFragmentEditPart) :
		execution(* OperandBoundsComputeHelper.createUpdateIOBoundsForCFResizeCommand(..)) &&
		args(compoundCmd, request, combinedFragmentEditPart) {
		return null;
	}
	
	void around() : execution(* OperandBoundsComputeHelper.addUpdateBoundsForIOCreationCommand(..)) {
		return;
	}
	
	void around() : execution(* OperandBoundsComputeHelper.addUpdateBoundsCommandForOperandDelelete(..)) {
		return;
	}
}
