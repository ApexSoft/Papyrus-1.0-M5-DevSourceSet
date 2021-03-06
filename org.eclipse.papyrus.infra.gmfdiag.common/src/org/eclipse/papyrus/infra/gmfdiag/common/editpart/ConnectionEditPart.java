/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.common.editpart;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.IntValueStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.StringValueStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.PapyrusConnectionEndEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.figure.edge.PapyrusEdgeFigure;


/**
 * Abstract edit part for all connection nodes.
 */
public abstract class ConnectionEditPart extends ConnectionNodeEditPart implements IPapyrusEditPart {

	/**
	 * CSS property for the line style
	 */
	protected static final String LINE_STYLE = "lineStyle";

	/**
	 * CSS property for the line dashes' length
	 */
	protected static final String LINE_DASH_LENGTH = "lineDashLength";

	/**
	 * CSS property for the length between line dashes
	 */
	protected static final String LINE_DASH_GAP = "lineDashGap";

	public ConnectionEditPart(View view) {
		super(view);
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refresh()
	 */
	@Override
	public void refresh() {
		super.refresh();
		IFigure figure = this.getFigure();
		Object model = this.getModel();
		if (figure instanceof PapyrusEdgeFigure && model instanceof Connector) {
			Connector connector = (Connector) model;
			PapyrusEdgeFigure edge = (PapyrusEdgeFigure) figure;
			String lineStyle = extract((StringValueStyle) connector.getNamedStyle(NotationPackage.eINSTANCE.getStringValueStyle(), LINE_STYLE));
			int lineDashLength = extract((IntValueStyle) connector.getNamedStyle(NotationPackage.eINSTANCE.getIntValueStyle(), LINE_DASH_LENGTH));
			int lineDashGap = extract((IntValueStyle) connector.getNamedStyle(NotationPackage.eINSTANCE.getIntValueStyle(), LINE_DASH_GAP));
			if (lineStyle != null) {
				setupLineStyle(edge, lineStyle, connector.getLineWidth(), lineDashLength, lineDashGap);
			} else {
				edge.resetStyle();
			}
		}
	}

	/**
	 * Extracts the primitive value from the given style
	 * 
	 * @param style
	 *            The style
	 * @return The primitive value
	 */
	private String extract(StringValueStyle style) {
		if (style == null || style.getStringValue() == null || style.getStringValue().isEmpty()) {
			return null;
		}
		return style.getStringValue();
	}

	/**
	 * Extracts the primitive value from the given style
	 * 
	 * @param style
	 *            The style
	 * @return The primitive value
	 */
	private int extract(IntValueStyle style) {
		if (style == null) {
			return 0;
		}
		return style.getIntValue();
	}

	/**
	 * Setups the line style of the edge according to the given CSS style
	 * 
	 * @param edge
	 *            The shape to setup
	 * @param style
	 *            The CSS style
	 * @param originalWidth
	 *            Original width of the connector
	 * @param lineDashLength
	 *            Length of the dashes
	 * @param lineDashGap
	 *            Length of the gap between dashes
	 */
	private void setupLineStyle(PapyrusEdgeFigure edge, String style, int originalWidth, int lineDashLength, int lineDashGap) {
		if ("none".equals(style)) {
			edge.resetStyle();
		} else {
			if ("hidden".equals(style)) {
				edge.setLineStyle(Graphics.LINE_SOLID);
				edge.setLineWidth(0);
				edge.setVisible(false);
			} else if ("dotted".equals(style)) {
				edge.setLineStyle(Graphics.LINE_DOT);
				edge.setLineWidth(originalWidth);
			} else if ("dashed".equals(style)) {
				edge.setLineStyle(Graphics.LINE_CUSTOM);
				edge.setLineWidth(originalWidth);
				edge.setLineDash(new int[] { lineDashLength, lineDashGap });
			} else if ("solid".equals(style)) {
				edge.setLineStyle(Graphics.LINE_SOLID);
				edge.setLineWidth(originalWidth);
			} else if ("double".equals(style)) {
				edge.setLineWidth(originalWidth * 2);
			}
		}
	}



	/**
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart#createDefaultEditPolicies()
	 * 
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new PapyrusConnectionEndEditPolicy());
	}

}
