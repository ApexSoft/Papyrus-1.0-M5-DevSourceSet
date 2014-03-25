package org.eclipse.papyrus.uml.diagram.sequence.util;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;

public privileged aspect FragmentsOrderedHelperAspect {

	ICommand around(final EditPart editPart, final CreateViewAndElementRequest request) :
		execution(ICommand FragmentsOrdererHelper.createOrderingFragmentsCommand(..)) &&
		args(editPart, request) {
		return AdvancedFragmentOrdererHelper.createOrderingFragmentsCommand(editPart, request);
	}
	
	Command around(final EditPart editPart, final CreateConnectionViewAndElementRequest request) :
		execution(Command FragmentsOrdererHelper.createOrderingFragmentsCommand(..)) &&
		args(editPart, request) {
		return new ICommandProxy(AdvancedFragmentOrdererHelper.createOrderingFragmentsCommand(editPart, request));
	}

	Command around(EditPart editPart, ReconnectRequest request) :
		execution(Command FragmentsOrdererHelper.createOrderingFragmentsCommand(..)) &&
		args(editPart, request) {
		return null;
	}
	
}