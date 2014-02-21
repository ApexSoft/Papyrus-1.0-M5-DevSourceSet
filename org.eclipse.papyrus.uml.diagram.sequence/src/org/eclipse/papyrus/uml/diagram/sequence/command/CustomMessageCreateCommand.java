/*****************************************************************************
 * Copyright (c) 2010 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Soyatec - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.MessageCreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.UMLBaseItemSemanticEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.util.CommandHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.MessageConnectionHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.OccurrenceSpecificationHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageSort;

/**
 * @author Jin Liu (jin.liu@soyatec.com)
 */
public class CustomMessageCreateCommand extends MessageCreateCommand {

	/**
	 * Constructor.
	 * 
	 * @param request
	 * @param source
	 * @param target
	 */
	public CustomMessageCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
		super(request, source, target);
	}

	/**
	 * Add a condition on the MOS container
	 * 
	 * @Override
	 */
	@Override
	public boolean canExecute() {
		if(source == null && target == null) {
			return false;
		}
		if(source != null && false == source instanceof Element) {
			return false;
		}
		if(target != null && false == target instanceof Element) {
			return false;
		}
		if(getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if(getContainer() == null) {
			return false;
		}
		if(getSource() != null && getTarget() != null) {
			if(!CommandHelper.hasValidContainer(getRequest())) {
				return false;
			}
		}
		if(!UMLBaseItemSemanticEditPolicy.getLinkConstraints().canCreateMessage_4003(getContainer(), getSource(), getTarget())) {
			return false;
		}
		return MessageConnectionHelper.canExist(MessageSort.SYNCH_CALL_LITERAL, (Element)source, (Element)target);
	}

	/**
	 * Create a MessageOccurenceSpecification and the call event when a message is created
	 * 
	 * @Override
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if(!canExecute()) {
			throw new ExecutionException("Invalid arguments in create link command"); //$NON-NLS-1$
		}
		InteractionFragment sourceContainer = (InteractionFragment)getRequest().getParameters().get(SequenceRequestConstant.SOURCE_MODEL_CONTAINER);
		InteractionFragment targetContainer = (InteractionFragment)getRequest().getParameters().get(SequenceRequestConstant.TARGET_MODEL_CONTAINER);
		Element source = getSource();
		if(source instanceof ExecutionOccurrenceSpecification) {
			source = ((ExecutionOccurrenceSpecification)source).getExecution();
		}
		Message message = CommandHelper.doCreateMessage(container, MessageSort.SYNCH_CALL_LITERAL, source, getTarget(), sourceContainer, targetContainer);
		if(message != null) {
			//Do reset message end to target ExecutionSpecification. See https://bugs.eclipse.org/bugs/show_bug.cgi?id=402975
			if(getTarget() instanceof ExecutionSpecification) {
				OccurrenceSpecificationHelper.resetExecutionStart((ExecutionSpecification)getTarget(), message.getReceiveEvent());
			}
			if(getSource() instanceof ExecutionOccurrenceSpecification) {
				ExecutionSpecification execution = ((ExecutionOccurrenceSpecification)getSource()).getExecution();
				if(execution != null) {
					OccurrenceSpecificationHelper.resetExecutionStart(execution, message.getSendEvent());
				}
			}
			doConfigure(message, monitor, info);
			((CreateElementRequest)getRequest()).setNewElement(message);
			return CommandResult.newOKCommandResult(message);
		}
		return CommandResult.newErrorCommandResult("There is now valid container for events"); //$NON-NLS-1$
	}
}
