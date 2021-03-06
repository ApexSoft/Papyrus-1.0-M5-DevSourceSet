/******************************************************************************
 * Copyright (c) 2002, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.papyrus.infra.gmfdiag.common.snap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToHelperUtil;
import org.eclipse.papyrus.infra.gmfdiag.common.snap.copy.DragEditPartsTrackerEx;

/**
 * A dervied DragEditPartsTRacker that sends REQ_DRAG instead of REQ_ORPHAN
 * and REQ_DROP instead of REQ_ADD
 * 
 * @author melaasar
 */
//TODO see Bug 424007. CustomDragEditPartsTracker and others classes in package snap.copy will be destroyed when the gef bug 424007 will be resolved.
@SuppressWarnings("restriction")
public class PapyrusDragEditPartsTrackerEx extends DragEditPartsTrackerEx {

	/**
	 * if true, we snap on the four corner of the figures
	 */
	protected final boolean snapOnCorners;

	/**
	 * if true, we snap on the four middle side of the figures
	 */
	protected final boolean snapOnMiddles;

	/**
	 * if true, we snap on the center of the figure
	 */
	protected final boolean snapOnCenter;


	/**
	 * @param sourceEditPart
	 */
	public PapyrusDragEditPartsTrackerEx(final EditPart sourceEditPart) {
		this(sourceEditPart, true, false, false);
	}

	/**
	 * 
	 * Constructor.
	 * 
	 * @param sourceEditPart
	 * @param snapOnCorners
	 * @param snapOnMiddles
	 * @param snapOnCenter
	 */
	public PapyrusDragEditPartsTrackerEx(final EditPart sourceEditPart, final boolean snapOnCorners, final boolean snapOnMiddles, final boolean snapOnCenter) {
		super(sourceEditPart);
		this.snapOnCorners = snapOnCorners;
		this.snapOnMiddles = snapOnMiddles;
		this.snapOnCenter = snapOnCenter;
	}

	/**
	 * This method can be overridden by clients to customize the snapping
	 * behavior.
	 * 
	 * @param request
	 *        the <code>ChangeBoundsRequest</code> from which the move delta
	 *        can be extracted and updated
	 * @since 3.4
	 */
	@SuppressWarnings({ "unchecked" })
	protected void snapPoint(ChangeBoundsRequest request) {
		if(getSnapToHelper() != null && request.isSnapToEnabled()) {
			int restrictedDirection = 0;
			restrictedDirection = restrictedDirection | PositionConstants.EAST;
			restrictedDirection = restrictedDirection | PositionConstants.WEST;
			restrictedDirection = restrictedDirection | PositionConstants.SOUTH;
			restrictedDirection = restrictedDirection | PositionConstants.NORTH;
			request.getExtendedData().put(SnapToHelperUtil.RESTRICTED_DIRECTIONS, restrictedDirection);

			final Map<Double, PrecisionPoint> distVSPoint = new HashMap<Double, PrecisionPoint>();
			if(this.snapOnCorners) {
				distVSPoint.putAll(getCornerDistances(request));
			}

			if(this.snapOnMiddles) {
				distVSPoint.putAll(getMiddleDistances(request));
			}

			if(this.snapOnCenter) {
				distVSPoint.putAll(getCenterDistances(request));
			}

			final List<Double> distances = new ArrayList<Double>(distVSPoint.keySet());
			if(distances.size() > 0) {
				double min = distances.get(0);
				for(int i = 1; i < distances.size() - 1; i++) {
					min = Math.min(min, distances.get(i));
				}
				request.setMoveDelta(distVSPoint.get(min));
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 *         a map with the couple distance and delta point to anchor by the corner of the figure
	 */
	protected Map<Double, PrecisionPoint> getCornerDistances(final ChangeBoundsRequest request) {
		final Map<Double, PrecisionPoint> distVSPoints = new HashMap<Double, PrecisionPoint>();
		if(getSnapToHelper() != null && request.isSnapToEnabled()) {
			final Point moveDelta = request.getMoveDelta();
			PrecisionRectangle jointRect = getCompoundSourceRectangle();
			jointRect.translate(moveDelta);

			//calculate the delta to anchor on the top left corner
			final PrecisionPoint topLeftCornerDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectTopLeft = getSourceRectangle();
			baseRectTopLeft.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectTopLeft, jointRect }, topLeftCornerDelta);

			//calculate the delta to anchor on the top right corner
			final PrecisionPoint topRightCornerDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectTopRight = getSourceRectangle();
			baseRectTopRight.setX(baseRectTopRight.x + baseRectTopRight.width);
			baseRectTopRight.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectTopRight, jointRect }, topRightCornerDelta);

			//calculate the delta to anchor on the bottom left corner
			final PrecisionPoint bottomLeftCornerDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectBottomLeft = getSourceRectangle();
			baseRectBottomLeft.setY(baseRectBottomLeft.y + baseRectBottomLeft.height);
			baseRectBottomLeft.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectBottomLeft, jointRect }, bottomLeftCornerDelta);

			//calculate the delta to anchor on the bottom right corner
			final PrecisionPoint bottomRightCornerDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectBottomRight = getSourceRectangle();
			baseRectBottomRight.setX(baseRectBottomRight.x + baseRectBottomRight.width);
			baseRectBottomRight.setY(baseRectBottomRight.y + baseRectBottomRight.height);
			baseRectBottomRight.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectBottomRight, jointRect }, bottomRightCornerDelta);

			final Point ref = baseRectTopLeft.getTopLeft();
			distVSPoints.put(distance(ref, topLeftCornerDelta), topLeftCornerDelta);
			distVSPoints.put(distance(ref, topRightCornerDelta), topRightCornerDelta);
			distVSPoints.put(distance(ref, bottomLeftCornerDelta), bottomLeftCornerDelta);
			distVSPoints.put(distance(ref, bottomRightCornerDelta), bottomRightCornerDelta);
		}
		return distVSPoints;
	}

	/**
	 * 
	 * @param request
	 * @return
	 *         a map with the couple distance and delta point to anchor by the middle of each side of the figure
	 */
	protected Map<Double, PrecisionPoint> getMiddleDistances(final ChangeBoundsRequest request) {
		final Map<Double, PrecisionPoint> distVSPoints = new HashMap<Double, PrecisionPoint>();
		if(getSnapToHelper() != null && request.isSnapToEnabled()) {
			final Point moveDelta = request.getMoveDelta();

			PrecisionRectangle jointRect = getCompoundSourceRectangle();
			jointRect.translate(moveDelta);
			//calculate the delta to anchor on the middle top point
			final PrecisionPoint middleTopDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectMiddleTop = getSourceRectangle();
			baseRectMiddleTop.setPreciseLocation(baseRectMiddleTop.preciseX() + (baseRectMiddleTop.preciseWidth() / 2), baseRectMiddleTop.preciseY());
			baseRectMiddleTop.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectMiddleTop, jointRect }, middleTopDelta);

			//calculate the delta to anchor on the middle left point
			final PrecisionPoint middleLeftDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectMiddleLeft = getSourceRectangle();
			baseRectMiddleLeft.setPreciseLocation(baseRectMiddleLeft.preciseX(), baseRectMiddleLeft.preciseY() + (baseRectMiddleLeft.preciseWidth() / 2));
			baseRectMiddleLeft.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectMiddleLeft, jointRect }, middleLeftDelta);

			//calculate the delta to anchor on the middle right point
			final PrecisionPoint middleRightDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectMiddleRight = getSourceRectangle();
			baseRectMiddleRight.setPreciseLocation(baseRectMiddleRight.preciseX() + baseRectMiddleRight.preciseWidth(), baseRectMiddleRight.preciseY() + (baseRectMiddleRight.preciseHeight() / 2));
			baseRectMiddleRight.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectMiddleRight, jointRect }, middleRightDelta);

			//calculate the delta to anchor on the middle bottom
			final PrecisionPoint middleBottomDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectMiddleBottom = getSourceRectangle();
			baseRectMiddleBottom.setPreciseLocation(baseRectMiddleBottom.preciseX() + (baseRectMiddleBottom.preciseWidth() / 2), baseRectMiddleBottom.preciseY() + baseRectMiddleBottom.preciseHeight());
			baseRectMiddleBottom.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectMiddleBottom, jointRect }, middleBottomDelta);

			final Point ref = baseRectMiddleTop.getTopLeft();
			distVSPoints.put(distance(ref, middleTopDelta), middleTopDelta);
			distVSPoints.put(distance(ref, middleLeftDelta), middleLeftDelta);
			distVSPoints.put(distance(ref, middleRightDelta), middleRightDelta);
			distVSPoints.put(distance(ref, middleBottomDelta), middleBottomDelta);
		}

		return distVSPoints;
	}

	/**
	 * 
	 * @param request
	 * @return
	 *         a map with the couple distance and delta point to anchor by the center of the figure
	 */
	protected Map<Double, PrecisionPoint> getCenterDistances(final ChangeBoundsRequest request) {
		final Map<Double, PrecisionPoint> distVSPoints = new HashMap<Double, PrecisionPoint>();
		if(getSnapToHelper() != null && request.isSnapToEnabled()) {
			final Point moveDelta = request.getMoveDelta();

			PrecisionRectangle jointRect = getCompoundSourceRectangle();
			jointRect.translate(moveDelta);
			//calculate the delta to anchor on the middle top point
			final PrecisionPoint centerDelta = new PrecisionPoint(moveDelta);
			final PrecisionRectangle baseRectCenter = getSourceRectangle();
			baseRectCenter.setPreciseLocation(baseRectCenter.preciseX() + (baseRectCenter.preciseWidth() / 2), baseRectCenter.preciseY() + (baseRectCenter.preciseHeight() / 2));
			baseRectCenter.translate(moveDelta);
			getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[]{ baseRectCenter, jointRect }, centerDelta);

			final Point ref = baseRectCenter.getTopLeft();
			distVSPoints.put(distance(ref, centerDelta), centerDelta);
		}

		return distVSPoints;
	}

	/**
	 * 
	 * @param pt1
	 *        a first point
	 * @param pt2
	 *        the second point
	 * @return
	 *         the distance between the two points
	 */
	protected final double distance(final Point pt1, final Point pt2) {
		double deltaX = pt1.preciseX() - pt2.preciseX();
		double deltaY = pt1.preciseY() - pt2.preciseY();
		return Math.hypot(deltaX, deltaY);
	}
}
