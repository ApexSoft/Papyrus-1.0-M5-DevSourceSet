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
package org.eclipse.papyrus.uml.diagram.sequence.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;

/**
 * @author Jin Liu (jin.liu@soyatec.com)
 */
public class MessageSync extends MessageFigure {

	/**
	 * Constructor.
	 * 
	 */
	public MessageSync() {
	}

	/**
	 * Constructor.
	 * 
	 * @param mapMode
	 */
	public MessageSync(IMapMode mapMode) {
		super(mapMode);
		this.setForegroundColor(ColorConstants.black);
	}

	/**
	 * @see org.eclipse.papyrus.uml.diagram.sequence.figures.MessageFigure#createTargetDecoration()
	 * 
	 * @return
	 */
	@Override
	protected RotatableDecoration createTargetDecoration() {
		PolygonDecoration df = new PolygonDecoration();
		df.setFill(true);
		df.setForegroundColor(getForegroundColor());
		df.setBackgroundColor(ColorConstants.black);
		PointList pl = new PointList();
		pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(2));
		pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
		pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(-2));
		pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(2));
		df.setTemplate(pl);
		df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
		return df;
	}
}
