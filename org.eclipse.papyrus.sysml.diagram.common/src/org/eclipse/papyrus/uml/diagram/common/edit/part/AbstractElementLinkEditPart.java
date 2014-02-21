/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.common.edit.part;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.gmf.diagram.common.edit.policy.DefaultGraphicalNodeEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editparts.UMLConnectionNodeEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLinkLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.figure.EdgeDecorationType;

/**
 * Abstract non-diagram specific edit part for links.
 * This class is adapted from edit parts generated by GMF Tooling.
 */
public abstract class AbstractElementLinkEditPart extends UMLConnectionNodeEditPart {

	/**
	 * Constructor.
	 */
	public AbstractElementLinkEditPart(View view) {
		super(view);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new DefaultGraphicalNodeEditPolicy());
		installEditPolicy(AppliedStereotypeLinkLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new AppliedStereotypeLinkLabelDisplayEditPolicy());
	}

	/**
	 * Add fixed child edit part.
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof AppliedStereotypeLinkLabelEditPart) {
			((AppliedStereotypeLinkLabelEditPart)childEditPart).setLabel(getPrimaryShape().getAppliedStereotypeLabel());
			return true;
		}
		return false;
	}

	/**
	 * Remove fixed child edit part.
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof AppliedStereotypeLinkLabelEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if(addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		if(removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);

		// Update the figure when the line width changes
		Object feature = event.getFeature();
		if((getModel() != null) && (getModel() == event.getNotifier())) {

			if(NotationPackage.eINSTANCE.getLineStyle_LineWidth().equals(feature)) {
				refreshLineWidth();
				refreshArrowSource();
				refreshArrowTarget();
			} else if(NotationPackage.eINSTANCE.getLineTypeStyle_LineType().equals(feature)) {
				refreshLineType();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLineType();
		refreshLineWidth();
		refreshArrowSource();
		refreshArrowTarget();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setLineWidth(int width) {
		if(width < 0) {
			width = 1;
		}
		getPrimaryShape().setLineWidth(width);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setLineType(int style) {
		getPrimaryShape().setLineStyle(style);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setArrowSource(RotatableDecoration arrowDecoration) {
		getPrimaryShape().setSourceDecoration(arrowDecoration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setArrowTarget(RotatableDecoration arrowDecoration) {
		getPrimaryShape().setTargetDecoration(arrowDecoration);
	}

	/**
	 * <pre>
	 * {@inheritDoc}
	 * 
	 * This method override parent implementation to add a new set of 
	 * predefined decoration type.
	 * 
	 * The arrowType available constants are given in {@link EdgeDecorationType}.
	 * </pre>
	 */
	@Override
	protected RotatableDecoration getArrowDecoration(int arrowType) {
		RotatableDecoration decoration = null;
		int width = getLineWidth();
		if(width < 0) {
			width = 1;
		}
		if(arrowType == EdgeDecorationType.OPEN_ARROW) {
			IMapMode mm = getMapMode();
			decoration = new PolylineDecoration();
			((PolylineDecoration)decoration).setScale(mm.DPtoLP(15 + width), mm.DPtoLP(5 + width));
			((PolylineDecoration)decoration).setTemplate(PolylineDecoration.TRIANGLE_TIP);
			((PolylineDecoration)decoration).setLineWidth(mm.DPtoLP(width));

		} else if(arrowType == EdgeDecorationType.SOLID_ARROW_FILLED) {
			IMapMode mm = getMapMode();
			decoration = new PolygonDecoration();
			((PolygonDecoration)decoration).setScale(mm.DPtoLP(15 + width), mm.DPtoLP(5 + width));
			((PolygonDecoration)decoration).setTemplate(PolygonDecoration.TRIANGLE_TIP);
			((PolygonDecoration)decoration).setLineWidth(mm.DPtoLP(width));
			((PolygonDecoration)decoration).setFill(true);

		} else if(arrowType == EdgeDecorationType.SOLID_ARROW_EMPTY) {
			IMapMode mm = getMapMode();
			decoration = new PolygonDecoration();
			((PolygonDecoration)decoration).setScale(mm.DPtoLP(15 + width), mm.DPtoLP(5 + width));
			((PolygonDecoration)decoration).setTemplate(PolygonDecoration.TRIANGLE_TIP);
			((PolygonDecoration)decoration).setLineWidth(mm.DPtoLP(width));

			// Not really empty... filled with white color.
			((PolygonDecoration)decoration).setFill(true);
			((PolygonDecoration)decoration).setBackgroundColor(ColorConstants.white);

		} else if(arrowType == EdgeDecorationType.SOLID_DIAMOND_FILLED) {
			IMapMode mm = getMapMode();
			decoration = new PolygonDecoration();
			((PolygonDecoration)decoration).setScale(mm.DPtoLP(12 + width), mm.DPtoLP(6 + width));

			PointList diamondPointList = new PointList();
			diamondPointList.addPoint(0, 0);
			diamondPointList.addPoint(-1, 1);
			diamondPointList.addPoint(-2, 0);
			diamondPointList.addPoint(-1, -1);

			((PolygonDecoration)decoration).setTemplate(diamondPointList);
			((PolygonDecoration)decoration).setLineWidth(mm.DPtoLP(width));
			((PolygonDecoration)decoration).setFill(true);

		} else if(arrowType == EdgeDecorationType.SOLID_DIAMOND_EMPTY) {
			IMapMode mm = getMapMode();
			decoration = new PolygonDecoration();
			((PolygonDecoration)decoration).setScale(mm.DPtoLP(12 + width), mm.DPtoLP(6 + width));

			PointList diamondPointList = new PointList();
			diamondPointList.addPoint(0, 0);
			diamondPointList.addPoint(-1, 1);
			diamondPointList.addPoint(-2, 0);
			diamondPointList.addPoint(-1, -1);

			((PolygonDecoration)decoration).setTemplate(diamondPointList);
			((PolygonDecoration)decoration).setLineWidth(mm.DPtoLP(width));

			// Not really empty... filled with white color.
			((PolygonDecoration)decoration).setFill(true);
			((PolygonDecoration)decoration).setBackgroundColor(ColorConstants.white);
		}

		return decoration;
	}
}
