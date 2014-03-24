package org.eclipse.papyrus.uml.diagram.sequence.util;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;

public privileged aspect OperandBoundsComputeHelperAspect {

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
