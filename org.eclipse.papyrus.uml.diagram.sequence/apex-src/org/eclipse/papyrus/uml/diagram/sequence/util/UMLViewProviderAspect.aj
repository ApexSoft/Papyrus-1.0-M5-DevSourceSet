package org.eclipse.papyrus.uml.diagram.sequence.util;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLViewProvider;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

public aspect UMLViewProviderAspect {

	pointcut calledSetLayoutConstraint() :
		call(* org.eclipse.gmf.runtime.notation.Node+.setLayoutConstraint(..));
	
	pointcut execCreateIO() :
		execution(* UMLViewProvider+.createInteractionOperand_3005(..));
	
	pointcut execCreateCF() :
		execution(* UMLViewProvider+.createCombinedFragment_3004(..));
	
	pointcut execCreateCompartment() :
		execution(* UMLViewProvider+.createCompartment(..));

	pointcut execCreateIOAndCF() :
		execCreateIO() && cflowbelow(execCreateCF());
	
	/**
	 * LayoutConstraint(Bounds)을 기본적으로 null로 세팅
	 * x, y, w, h 값이 없는 경우 불필요한 메모리 사용을 줄이는 효과
	 * 
	 * @param value
	 */
	void around(Bounds value) :
		calledSetLayoutConstraint() &&
		within(UMLViewProvider+) && args(value) &&
		cflowbelow((execCreateIO() || execCreateCompartment()) &&
				within(UMLViewProvider)) {
		proceed(SequenceUtil.IS_USE_EACH_LOCATION ? value : null);
	}
	
	public final static boolean SequenceUtil.IS_USE_EACH_LOCATION = false;
	
	ICommand around(GraphicalEditPart ep, Rectangle r) :
		execution(* OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(..)) &&
		args(ep, r) {
		final GraphicalEditPart editpart = ep;
		final Rectangle rect = r;
		ICommand cmd = new AbstractTransactionalCommand(editpart.getEditingDomain(), "Update Operand Bounds", null) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				if(editpart.getModel() instanceof Node) {
					Node node = (Node)editpart.getModel();
					if (node.getLayoutConstraint() == null) {
						node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
					}
					if (node.getLayoutConstraint() instanceof Bounds) {
						Bounds bounds = (Bounds)node.getLayoutConstraint();
						OperandBoundsComputeHelper.fillBounds(bounds, rect);
					}
				}
				return CommandResult.newOKCommandResult();
			}
		};
		return cmd;
	}

}
