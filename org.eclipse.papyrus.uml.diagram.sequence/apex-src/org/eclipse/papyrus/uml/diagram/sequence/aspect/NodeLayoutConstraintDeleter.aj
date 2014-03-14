package org.eclipse.papyrus.uml.diagram.sequence.aspect;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLViewProvider;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

public aspect NodeLayoutConstraintDeleter {

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
		cflowbelow((execCreateIOAndCF() || execCreateCompartment()) &&
				within(UMLViewProvider)) {
		proceed(SequenceUtil.IS_USE_EACH_LOCATION ? value : null);
	}
	
	public final static boolean SequenceUtil.IS_USE_EACH_LOCATION = true;

}
