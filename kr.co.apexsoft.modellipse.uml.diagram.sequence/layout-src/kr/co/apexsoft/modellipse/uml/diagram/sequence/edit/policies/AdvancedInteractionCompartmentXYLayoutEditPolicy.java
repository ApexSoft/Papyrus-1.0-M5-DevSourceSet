package kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.papyrus.uml.diagram.common.commands.PreserveAnchorsPositionCommand;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool.CreateAspectUnspecifiedTypeRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CustomLifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineFigure;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.util.HighlightUtil;
import org.eclipse.papyrus.uml.diagram.sequence.util.LifelineEditPartUtil;
import org.eclipse.papyrus.uml.diagram.sequence.util.LifelineMessageCreateHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.LifelineResizeHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

public class AdvancedInteractionCompartmentXYLayoutEditPolicy extends
		XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		CreateViewRequest req = (CreateViewRequest)request;
		Command cmd = super.getCreateCommand(request);
		if(cmd != null && req.getSize() != null) { // create lifeline with specific size
			TransactionalEditingDomain editingDomain = ((IGraphicalEditPart)getHost()).getEditingDomain();
			Iterator<? extends ViewDescriptor> iter = req.getViewDescriptors().iterator();
			while(iter.hasNext()) {
				CreateViewRequest.ViewDescriptor viewDescriptor = iter.next();
				if(((IHintedType)UMLElementTypes.Lifeline_3001).getSemanticHint()
						.equals(viewDescriptor.getSemanticHint())) {
					ICommand manualLabelSizeCommand = LifelineResizeHelper
							.createManualLabelSizeCommand(editingDomain, viewDescriptor);
					cmd = (new ICommandProxy(manualLabelSizeCommand)).chain(cmd);
				}
			}
		}
		return cmd;	}

	@Override
	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand compoundCmd = new CompoundCommand();
		compoundCmd.setLabel("Move or Resize");
		IFigure figure = getHostFigure();
		Rectangle hostBounds = figure.getBounds();
		for(Object o : request.getEditParts()) {
			GraphicalEditPart child = (GraphicalEditPart)o;
			Object constraintFor = getConstraintFor(request, child);
			if(constraintFor instanceof Rectangle) {
				Rectangle childBounds = (Rectangle)constraintFor;
				if(childBounds.x < 0 || childBounds.y < 0) {
					return UnexecutableCommand.INSTANCE;
				}
				if(child instanceof LifelineEditPart) {
					if(isVerticalMove(request)) {
//						addLifelineResizeChildrenCommand(compoundCmd, request, (LifelineEditPart)child, 1);
					}
				} else if(child instanceof CombinedFragmentEditPart) {
					// Add restrictions to change the size
					if(!OperandBoundsComputeHelper.checkRedistrictOnCFResize(request, child)) {
						return null;
					}
//					Command resizeChildrenCommand = getCombinedFragmentResizeChildrenCommand(request, (CombinedFragmentEditPart)child);
//					if(resizeChildrenCommand != null && resizeChildrenCommand.canExecute()) {
//						compoundCmd.add(resizeChildrenCommand);
//					}
				}
				boolean hasCreateLink = LifelineMessageCreateHelper.hasIncomingMessageCreate(child);
				if(hasCreateLink && !LifelineMessageCreateHelper.canMoveLifelineVertical((LifelineEditPart)child, (Rectangle)translateToModelConstraint(constraintFor))) {
					return UnexecutableCommand.INSTANCE;
				}
				if(!(child instanceof LifelineEditPart) || isVerticalMove(request) || hasCreateLink) {
					Command changeConstraintCommand = createChangeConstraintCommand(request, child, translateToModelConstraint(constraintFor));
					// When we change the width by mouse, it passe to manual mode. see https://bugs.eclipse.org/bugs/show_bug.cgi?id=383723 
					if(child instanceof LifelineEditPart && changeConstraintCommand != null && request.getSizeDelta().width != 0) {
						compoundCmd.add(new ICommandProxy(LifelineResizeHelper.createManualLabelSizeCommand((LifelineEditPart)child)));
					}
					compoundCmd.add(changeConstraintCommand);
				}
				
				int right = childBounds.right();
				int bottom = childBounds.bottom();
				int deltaX = 0;
				int deltaY = 0;
				if(right > hostBounds.width) {
					deltaX = right - hostBounds.width;
				}
				if(bottom > hostBounds.height) {
					deltaY = bottom - hostBounds.height;
				}
				if(deltaX != 0 || deltaY != 0) {
					ChangeBoundsRequest boundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
					boundsRequest.setSizeDelta(new Dimension(deltaX, deltaY));
					EditPart hostParent = getHost().getParent();
					boundsRequest.setEditParts(hostParent);
					Command cmd = hostParent.getCommand(boundsRequest);
					if(cmd != null && cmd.canExecute()) {
						compoundCmd.add(cmd);
					}
				}
			}
		}
		return compoundCmd.unwrap();
	}

	protected boolean isVerticalMove(ChangeBoundsRequest request) {
		if(request instanceof AlignmentRequest) {
			AlignmentRequest alignmentRequest = (AlignmentRequest)request;
			switch(alignmentRequest.getAlignment()) {
			case PositionConstants.BOTTOM:
			case PositionConstants.TOP:
			case PositionConstants.MIDDLE:
			case PositionConstants.VERTICAL:
			case PositionConstants.NORTH_EAST:
			case PositionConstants.NORTH_WEST:
			case PositionConstants.SOUTH_EAST:
			case PositionConstants.SOUTH_WEST:
				return false;
			}
		}
		Point point = request.getMoveDelta();
		return point.y == 0;
	}
	
	/**
	 * Change constraint for comportment by return null if the resize is lower than the minimun
	 * size.
	 */
	@Override
	protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
		Rectangle rect = new PrecisionRectangle(child.getFigure().getBounds());
		child.getFigure().translateToAbsolute(rect);
		rect = request.getTransformedRectangle(rect);
		child.getFigure().translateToRelative(rect);
		rect.translate(getLayoutOrigin().getNegated());
		Dimension sizeDelta = request.getSizeDelta();
		if(sizeDelta.width == 0 && sizeDelta.height == 0) {
			Rectangle cons = getCurrentConstraintFor(child);
			if(cons != null) {
				rect.setSize(cons.width, cons.height);
			}
		} else { // resize editpart
			Dimension minSize = getMinimumSizeFor(child);
			if(sizeDelta.width != 0 && rect.width < minSize.width) { // In manual mode, there is no minimal width
				return null;
			}
			if(sizeDelta.height != 0 && rect.height < minSize.height) {
				return null;
			}
		}
		rect = (Rectangle)getConstraintFor(rect);
		Rectangle cons = getCurrentConstraintFor(child);
		if(sizeDelta.width == 0) {
			rect.width = cons.width;
		}
		if(sizeDelta.height == 0) {
			rect.height = cons.height;
		}
		return rect;
	}

	/**
	 * Handle minimum size for lifeline
	 */
	@Override
	protected Dimension getMinimumSizeFor(GraphicalEditPart child) {
		Dimension minimunSize;
		if(child instanceof LifelineEditPart) {
			minimunSize = getMinimumSizeFor((LifelineEditPart)child);
		} else {
			minimunSize = new Dimension(8, 8);
		}
		return minimunSize;
	}

	/**
	 * Get minimun for a lifeline
	 * 
	 * @param child
	 *        The lifeline
	 * @return The minimun size
	 */
	private Dimension getMinimumSizeFor(LifelineEditPart child) {
		LifelineEditPart lifelineEditPart = child;
		Dimension minimunSize = lifelineEditPart.getFigure().getMinimumSize();
		for(LifelineEditPart lifelineEP : LifelineEditPartUtil.getInnerConnectableElementList(lifelineEditPart)) {
			minimunSize.union(getMinimumSizeFor(lifelineEP));
		}
		for(ShapeNodeEditPart executionSpecificationEP : LifelineEditPartUtil.getChildShapeNodeEditPart(lifelineEditPart)) {
			int minimunHeight = executionSpecificationEP.getFigure().getBounds().bottom();
			minimunSize.setSize(new Dimension(minimunSize.width, Math.max(minimunSize.height, minimunHeight)));
		}
		return minimunSize;
	}

	/**
	 * Block adding element by movement on Interaction
	 */
	@Override
	public Command getAddCommand(Request request) {
		if(request instanceof ChangeBoundsRequest) {
			return UnexecutableCommand.INSTANCE;
		}
		return super.getAddCommand(request);
	}

	@Override
	public Command getCommand(Request request) {
		if(request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest cbr = (ChangeBoundsRequest)request;
			if (cbr.getEditParts() == null){
				return super.getCommand(request);
			}
			int resizeDirection = cbr.getResizeDirection();
			CompoundCommand compoundCmd = new CompoundCommand("Resize of Interaction Compartment Elements");
			for(Object ep : cbr.getEditParts()) {
				if(ep instanceof LifelineEditPart && isVerticalMove(cbr)) {
					// Lifeline EditPart
					LifelineEditPart lifelineEP = (LifelineEditPart)ep;
					int preserveY = PreserveAnchorsPositionCommand.PRESERVE_Y;
					Dimension newSizeDelta = PreserveAnchorsPositionCommand.getSizeDeltaToFitAnchors(lifelineEP, cbr.getSizeDelta(), preserveY);
					// SetBounds command modifying the sizeDelta
					compoundCmd.add(getSetBoundsCommand(lifelineEP, cbr, newSizeDelta));
					// PreserveAnchors command
					compoundCmd.add(new ICommandProxy(new CustomLifelineEditPart.PreserveAnchorsPositionCommandEx(lifelineEP, newSizeDelta, preserveY, lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure(), resizeDirection)));
				}
			}
			if(compoundCmd.size() == 0) {
				return super.getCommand(request);
			} else {
				return compoundCmd;
			}
		}
		return super.getCommand(request);
	}

	/**
	 * It obtains an appropriate SetBoundsCommand for a LifelineEditPart. The
	 * newSizeDelta provided should be equal o less than the one contained in
	 * the request. The goal of this newDelta is to preserve the anchors'
	 * positions after the resize. It is recommended to obtain this newSizeDelta
	 * by means of calling
	 * PreserveAnchorsPositionCommand.getSizeDeltaToFitAnchors() operation
	 * 
	 * @param lifelineEP
	 *        The Lifeline that will be moved or resized
	 * @param cbr
	 *        The ChangeBoundsRequest for moving or resized the lifelineEP
	 * @param newSizeDelta
	 *        The sizeDelta to used instead of the one contained in the
	 *        request
	 * @return The SetBoundsCommand
	 */
	protected Command getSetBoundsCommand(LifelineEditPart lifelineEP, ChangeBoundsRequest cbr, Dimension newSizeDelta) {
		// Modify request
		List<?> epList = cbr.getEditParts();
		Dimension oldSizeDelta = cbr.getSizeDelta();
		cbr.setEditParts(lifelineEP);
		cbr.setSizeDelta(newSizeDelta);
		// Obtain the command with the modified request
		Command cmd = super.getCommand(cbr);
		// Restore the request
		cbr.setEditParts(epList);
		cbr.setSizeDelta(oldSizeDelta);
		// Return the SetBoundsCommand only for the Lifeline and with the
		// sizeDelta modified in order to preserve the links' anchors positions
		return cmd;
	}

	/**
	 * Align lifeline in vertical direction
	 * Fix https://bugs.eclipse.org/bugs/show_bug.cgi?id=364688
	 */
	@Override
	protected Rectangle getBoundsOffest(CreateViewRequest request, Rectangle bounds, CreateViewRequest.ViewDescriptor viewDescriptor) {
		int translate = request.getViewDescriptors().indexOf(viewDescriptor) * 10;
		Rectangle target = bounds.getCopy().translate(translate, translate);
		if(((IHintedType)UMLElementTypes.Lifeline_3001).getSemanticHint().equals(viewDescriptor.getSemanticHint())) {
			target.setY(SequenceUtil.LIFELINE_VERTICAL_OFFSET);
		}
		return target;
	}

	@Override
	protected void showSizeOnDropFeedback(CreateRequest request) {
		super.showSizeOnDropFeedback(request);
		if(request instanceof CreateAspectUnspecifiedTypeRequest) {
			CreateAspectUnspecifiedTypeRequest req = (CreateAspectUnspecifiedTypeRequest)request;
			if(req.getElementTypes().contains(UMLElementTypes.CombinedFragment_3004) || req.getElementTypes().contains(UMLElementTypes.ConsiderIgnoreFragment_3007)) {
				Rectangle rect = new Rectangle(request.getLocation(), request.getSize());
				if(!coveredLifelines.isEmpty()) {
					for(LifelineEditPart lifeline : coveredLifelines) {
						HighlightUtil.unhighlight(lifeline);
					}
					coveredLifelines.clear();
				}
				fillCoveredLifelines(rect);
				for(LifelineEditPart lifeline : coveredLifelines) {
					HighlightUtil.highlight(lifeline);
				}
			}
		}
	}

	private void fillCoveredLifelines(Rectangle rect) {
		coveredLifelines.clear();
		Collection<?> values = getHost().getViewer().getEditPartRegistry().values();
		for(Object object : values) {
			if(!(object instanceof LifelineEditPart)) {
				continue;
			}
			LifelineEditPart lifeline = ((LifelineEditPart)object);
			LifelineFigure primaryShape = lifeline.getPrimaryShape();
			RectangleFigure nameFigure = primaryShape.getFigureLifelineNameContainerFigure();
			if(nameFigure != null) {
				Rectangle r = nameFigure.getBounds().getCopy();
				nameFigure.translateToAbsolute(r);
				if(rect.intersects(r)) {
					coveredLifelines.add(lifeline);
					continue;
				}
			}
			LifelineDotLineCustomFigure dotLineFigure = primaryShape.getFigureLifelineDotLineFigure();
			if(dotLineFigure != null) {
				Rectangle r = dotLineFigure.getBounds().getCopy();
				if(r.width != 1) {
					r.shrink(r.width / 2 - 1, 0);
				}
				dotLineFigure.translateToAbsolute(r);
				if(rect.intersects(r)) {
					coveredLifelines.add(lifeline);
					continue;
				}
			}
		}
	}

	@Override
	protected void eraseSizeOnDropFeedback(Request request) {
		if(!coveredLifelines.isEmpty()) {
			for(LifelineEditPart lifeline : coveredLifelines) {
				HighlightUtil.unhighlight(lifeline);
			}
			coveredLifelines.clear();
		}
		super.eraseSizeOnDropFeedback(request);
	}

	private List<LifelineEditPart> coveredLifelines = new ArrayList<LifelineEditPart>(1);
}
