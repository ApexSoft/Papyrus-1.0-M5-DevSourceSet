package kr.co.apexsoft.modellipse.uml.diagram.sequence.aspect;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.util.AdvancedFragmentsOrdererHelper;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;

@Aspect
public class FragmentsOrderedHelperAspect {
	
	@Pointcut("execution(* FragmentsOrdererHelper.createOrderingFragmentsCommand(..))")
	public void createCommandMethod() {
		
	}
	
	@Around("createCommandMethod() && args(editPart, request)")
	public ICommand createCommandMethod1(final EditPart editPart, final CreateViewAndElementRequest request) {
		return AdvancedFragmentsOrdererHelper.createOrderingFragmentsCommand(editPart, request);
	}
	
	@Around("createCommandMethod() && args(editPart, request)")
	public Command createCommandMethod2(final EditPart editPart, final CreateConnectionViewAndElementRequest request) {
		return new ICommandProxy(AdvancedFragmentsOrdererHelper.createOrderingFragmentsCommand(editPart, request));
	}

	@Around("createCommandMethod() && args(editPart, request)")
	public Command createCommandMethod3(EditPart editPart, ReconnectRequest request) {
		return null;
	}
}
