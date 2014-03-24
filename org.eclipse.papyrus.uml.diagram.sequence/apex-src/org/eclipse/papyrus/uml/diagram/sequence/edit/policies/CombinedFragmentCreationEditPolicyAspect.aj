package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.CombinedFragmentCreationEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.util.FragmentsOrdererHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.InteractionFragment;

privileged aspect CombinedFragmentCreationEditPolicyAspect {

	Command around(CombinedFragmentCreationEditPolicy editpolicy, CreateViewAndElementRequest request) :
		execution(* CombinedFragmentCreationEditPolicy.getCreateElementAndViewCommand(CreateViewAndElementRequest)) &&
		args(request) && this(editpolicy) {
		Command createElementAndViewCmd = editpolicy.super_getCreateElementAndViewCommand(request);
		if(CombinedFragmentCreationEditPolicy.isDerivedCombinedFragment(request.getViewAndElementDescriptor().getSemanticHint())) {
			Rectangle selectionRect = editpolicy.getSelectionRectangle(request);
			Set<InteractionFragment> coveredInteractionFragments = SequenceUtil.getCoveredInteractionFragments(selectionRect, editpolicy.getHost(), null);
			request.getExtendedData().put(SequenceRequestConstant.COVERED_INTERACTIONFRAGMENTS, coveredInteractionFragments);
		}
		//Ordering fragments
		if(createElementAndViewCmd != null && createElementAndViewCmd.canExecute()) {
			ICommand orderingFragmentsCommand = FragmentsOrdererHelper.createOrderingFragmentsCommand(editpolicy.getHost(), request);
			if(orderingFragmentsCommand != null) {
				createElementAndViewCmd = createElementAndViewCmd.chain(new ICommandProxy(orderingFragmentsCommand));
			}
		}
		return createElementAndViewCmd;
	}
	
	Command around(CombinedFragmentCreationEditPolicy editpolicy, CreateViewRequest request) :
		execution(* CombinedFragmentCreationEditPolicy.getCreateCommand(CreateViewRequest)) &&
		args(request) && this(editpolicy) {
		Command createViewCmd = (Command) editpolicy.super_getCreateCommand(request);
//		if(createViewCmd instanceof ICommandProxy) {
//			ICommandProxy commandProxy = (ICommandProxy)createViewCmd;
//			CompositeCommand compositeCommand = null;
//			ICommand realCmd = commandProxy.getICommand();
//			if(realCmd instanceof CompositeCommand) {
//				compositeCommand = (CompositeCommand)realCmd;
//			} else {
//				compositeCommand = new CompositeCommand(commandProxy.getLabel());
//				compositeCommand.add(realCmd);
//				realCmd = compositeCommand;
//			}
//			createViewCmd = new ICommandProxy(compositeCommand.reduce());
//		}
		return createViewCmd;
	}
	
	public Command CombinedFragmentCreationEditPolicy.super_getCreateElementAndViewCommand(
			CreateViewAndElementRequest request) {
		return super.getCreateElementAndViewCommand(request); 
	}
	
	public Command CombinedFragmentCreationEditPolicy.super_getCreateCommand(CreateViewRequest request) {
		return super.getCreateCommand(request); 
	}
}

