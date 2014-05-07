package kr.co.apexsoft.modellipse.uml.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.PartDecomposition;

public class AdvancedFragmentsOrderer {

	private static final float HALF_UNIT = 0.5f;

	private static final float CONVERT_UNIT = 1.0f;

	private IGraphicalEditPart rootEditPart;
	
	private InteractionFragment fragmentContainer;
	
	private List<InteractionFragment> insertionFragments = new ArrayList<InteractionFragment>();
	
	private int insertionYPos;
	
	private int insertionIndex;
	
	public AdvancedFragmentsOrderer(IGraphicalEditPart rootEditPart) {
		this.rootEditPart = rootEditPart;
	}
	
	public void setFragmentContainer(InteractionFragment fragment) {
		this.fragmentContainer = fragment;
	}
	
	public void addInsertionFragment(InteractionFragment fragment) {
		insertionFragments.add(fragment);
	}
	
	public void addInsertionFragments(Collection<InteractionFragment> fragments) {
		insertionFragments.addAll(fragments);
	}
	
	public void setInsertionPosition(int yPos) {
		this.insertionYPos = yPos;
	}
	
	public void ordering() {
		if (prepared()) {
			safelyChangeOrder();
		}
	}
	
	private boolean prepared() {
		if (rootEditPart == null) {
			return false;
		}
		if (fragmentContainer == null) {
			return false;
		}
		
		insertionIndex = getInsertionIndex();
		return insertionIndex > -1;
	}
	
	protected void safelyChangeOrder() {
		EList<InteractionFragment> orderingFragments = getOrderingFragments();
		synchronized (orderingFragments) {
			for (int i = 0; i < insertionFragments.size(); i++) {
				orderingFragments.move(insertionIndex + i, insertionFragments.get(i));
			}
		}
	}
	
	/**
	 * 새 InteractionFragment들이 추가될 index을 구함
	 * @return
	 */
	private int getInsertionIndex() {
		int index = -1;
		EList<InteractionFragment> orderingFragments = getOrderingFragments();
		for (int i = 0; i < orderingFragments.size(); i++) {
			InteractionFragment ift = orderingFragments.get(i);
			if (insertionFragments.contains(ift)) {
				continue;
			}
			Float yPos = getPosition(ift);
			if (yPos > (float) insertionYPos) {
				index = i;
				break;
			}
		}
		return index;
	}

	private EList<InteractionFragment> getOrderingFragments() {
		if (fragmentContainer instanceof Interaction) {
			return ((Interaction) fragmentContainer).getFragments();
		} else if (fragmentContainer instanceof InteractionOperand) {
			return ((InteractionOperand) fragmentContainer).getFragments();
		}
		return null;
	}
	
	private Float getPosition(InteractionFragment fragment) {
		if(fragment == null) {
			return null;
		}
		View view = getGraphicalView(fragment);
		if(view == null) {
			return null;
		}
		EObject hostElement = ViewUtil.resolveSemanticElement(view);
		EditPart editPart = DiagramEditPartsUtil.getEditPartFromView(view, rootEditPart);
		if(fragment instanceof MessageOccurrenceSpecification && hostElement instanceof Message && editPart instanceof ConnectionNodeEditPart) {
			boolean isStart = fragment == ((Message) hostElement).getSendEvent();
			Point location = SequenceUtil.getAbsoluteEdgeExtremity((ConnectionNodeEditPart) editPart, isStart);
			if(location != null) {
				return isStart ? location.y - HALF_UNIT : location.y + HALF_UNIT;
			}
		} else if(fragment instanceof ExecutionOccurrenceSpecification && hostElement instanceof ExecutionSpecification && editPart instanceof IGraphicalEditPart) {
			Rectangle bounds = SequenceUtil.getAbsoluteBounds((IGraphicalEditPart) editPart);
			if(bounds != null) {
				if(bounds.height <= 0) {
					bounds.height = 50;//LifelineXYLayoutEditPolicy.EXECUTION_INIT_HEIGHT;
				}
				if(fragment == ((ExecutionSpecification) hostElement).getStart()) {
					return bounds.y - HALF_UNIT;
				} else {
					return bounds.bottom() + HALF_UNIT;
				}
			}
		} else if(view instanceof Shape && editPart instanceof IGraphicalEditPart) {
			Rectangle bounds = SequenceUtil.getAbsoluteBounds((IGraphicalEditPart) editPart);
			if(bounds != null) {
				return bounds.y * CONVERT_UNIT;
			}
		}
		return null;
	}

	private View getGraphicalView(EObject eObj) {
		if(eObj instanceof ExecutionOccurrenceSpecification) {
			ExecutionSpecification execution = ((ExecutionOccurrenceSpecification) eObj).getExecution();
			return getGraphicalView(execution);
		} else if(eObj instanceof MessageOccurrenceSpecification) {
			return getGraphicalView(((MessageOccurrenceSpecification) eObj).getMessage());
		} else if(eObj instanceof PartDecomposition) {
			EList<Lifeline> covereds = ((PartDecomposition) eObj).getCovereds();
			if(covereds.size() == 1) {
				return getGraphicalView(covereds.get(0));
			}
		} else if(eObj != null) {
			List<?> views = DiagramEditPartsUtil.getEObjectViews(eObj);
			if(views.size() == 1) {
				return (View)views.get(0);
			}
			return null;
		}
		return null;
	}
	
}
