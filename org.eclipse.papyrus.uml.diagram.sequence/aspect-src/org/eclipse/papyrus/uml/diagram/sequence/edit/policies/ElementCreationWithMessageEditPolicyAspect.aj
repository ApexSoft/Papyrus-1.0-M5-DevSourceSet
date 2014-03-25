package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.command.PromptCreateElementAndNodeCommand;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;

public privileged aspect ElementCreationWithMessageEditPolicyAspect {
	
	Command around(ElementCreationWithMessageEditPolicy editPolicy, CreateConnectionRequest request) :
		execution(* ElementCreationWithMessageEditPolicy.getConnectionCompleteCommand(..)) &&
		args(request) && this(editPolicy) {
		Command command = proceed(editPolicy, request);
		if (command == null || !command.canExecute()) {
			return command;
		}
		if (command instanceof ICommandProxy) {
			ICommand iCmd = ((ICommandProxy) command).getICommand();
			if (iCmd instanceof PromptCreateElementAndNodeCommand) {
				return command;
			}
		}
		
		if (request instanceof CreateConnectionViewAndElementRequest) {
			CreateConnectionViewAndElementRequest viewRequest = (CreateConnectionViewAndElementRequest) request;
			if (getAsyncMessageHint().equals(viewRequest.getConnectionViewDescriptor().getSemanticHint())) {
				EditPart targetEP = editPolicy.getTargetEditPart(viewRequest);
				EObject target = ViewUtil.resolveSemanticElement((View) targetEP.getModel());
				EditPart sourceEP = viewRequest.getSourceEditPart();
				EObject source = ViewUtil.resolveSemanticElement((View) sourceEP.getModel());
				
				if (target instanceof Lifeline || (target instanceof ExecutionSpecification && target.equals(source))) {
					InteractionFragment ift = SequenceUtil.findInteractionFragmentContainerAt(
							viewRequest.getLocation(), editPolicy.getHost());
					
					if (target instanceof ExecutionSpecification) {
						targetEP = targetEP.getParent();
						target = ViewUtil.resolveSemanticElement((View) targetEP.getModel());
					}
					
					command = new ICommandProxy(new PromptCreateElementAndNodeCommand(command, editPolicy.getEditingDomain(),
							viewRequest.getConnectionViewDescriptor(), (ShapeNodeEditPart) targetEP, target, sourceEP,
							viewRequest, ift));
				}
			}
		}
		return command;
	}
	
	private String getAsyncMessageHint() {
		IHintedType message = (IHintedType) UMLElementTypes.Message_4004;
		return message.getSemanticHint();
	}
}
