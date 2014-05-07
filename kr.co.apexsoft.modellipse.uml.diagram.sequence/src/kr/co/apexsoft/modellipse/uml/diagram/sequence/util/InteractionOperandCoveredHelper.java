package kr.co.apexsoft.modellipse.uml.diagram.sequence.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.AbstractExecutionSpecificationEditPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;

public class InteractionOperandCoveredHelper {

	public static List<IGraphicalEditPart> findInnerExecutionSpecificationEditParts(View view, EditPartViewer viewer) {
		List<IGraphicalEditPart> result = new ArrayList<IGraphicalEditPart>();
		
		Set<InteractionFragment> coveringFragments = new HashSet<InteractionFragment>();
		EObject element = ViewUtil.resolveSemanticElement(view);
		if (element instanceof InteractionOperand) {
			InteractionOperand io = (InteractionOperand) element;
			coveringFragments.addAll(io.getFragments());
		}

		Iterator<InteractionFragment> iter = coveringFragments.iterator();
		while (iter.hasNext()) {
			InteractionFragment fragment = iter.next();
			if (!(fragment instanceof ExecutionSpecification)) {
				continue;
			}
			List<View> views = DiagramEditPartsUtil.findViews(fragment, viewer);
			for (View v : views) {
				Object o = viewer.getEditPartRegistry().get(v);
				if (o instanceof AbstractExecutionSpecificationEditPart && !result.contains(o)) {
					result.add((IGraphicalEditPart) o);
				}
			}
		}
		
		return result;
	}
	
	public static IFigure findFigure(View view, EditPartViewer viewer) {
		IGraphicalEditPart editPart = getGraphicalEditPart(view, viewer);
		if (editPart != null) {
			return editPart.getFigure();
		}
		return null;
	}
	
	public static IFigure findFigure(Element element, EditPartViewer viewer) {
		return findFigure(getGraphicalView(element), viewer);
	}
	
	private static IGraphicalEditPart getGraphicalEditPart(View view, EditPartViewer viewer) {
		if (viewer != null) {
			Object o = viewer.getEditPartRegistry().get(view);
			if (o instanceof IGraphicalEditPart) {
				return (IGraphicalEditPart) o;
			}
		}
		return null;
	}
	
	private static View getGraphicalView(Element element) {
		if (element instanceof ExecutionOccurrenceSpecification) {
			ExecutionSpecification es = ((ExecutionOccurrenceSpecification) element).getExecution();
			return getGraphicalView(es);
		} else if (element instanceof MessageOccurrenceSpecification) {
			Message message = ((MessageOccurrenceSpecification) element).getMessage();
			return getGraphicalView(message);
		}
		
		List<?> views = DiagramEditPartsUtil.getEObjectViews(element);
		if (views != null && views.size() == 1) {
			return (View) views.get(0);
		}
		return null;
	}
}
