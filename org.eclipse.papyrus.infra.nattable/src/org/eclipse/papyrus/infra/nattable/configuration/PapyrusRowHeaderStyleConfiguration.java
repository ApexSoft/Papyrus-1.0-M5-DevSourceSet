/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.nattable.configuration;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.layer.config.DefaultRowHeaderStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.BeveledBorderDecorator;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.CellPainterDecorator;
import org.eclipse.nebula.widgets.nattable.ui.util.CellEdgeEnum;
import org.eclipse.papyrus.infra.nattable.painter.CustomImagePainter;
import org.eclipse.papyrus.infra.nattable.painter.CustomizedCellPainter;

/**
 * 
 * The default style for the row header. We provide a specific label provider and image painter
 * 
 */
public class PapyrusRowHeaderStyleConfiguration extends DefaultRowHeaderStyleConfiguration {


	/**
	 * 
	 * @see org.eclipse.nebula.widgets.nattable.layer.config.DefaultRowHeaderStyleConfiguration#configureRegistry(org.eclipse.nebula.widgets.nattable.config.IConfigRegistry)
	 * 
	 * @param configRegistry
	 */
	@Override
	public void configureRegistry(final IConfigRegistry configRegistry) {
		this.cellPainter = new BeveledBorderDecorator(new CellPainterDecorator(new CustomizedCellPainter(), CellEdgeEnum.LEFT, new CustomImagePainter()));
		//		configureHeaderHasSelectionStyle(configRegistry);
		super.configureRegistry(configRegistry);
	}

	//	public Color selectedHeaderBgColor = GUIHelper.COLOR_GRAY;
	//
	//	public Color selectedHeaderFgColor = GUIHelper.COLOR_WHITE;
	//
	//	public Font selectedHeaderFont = GUIHelper.getFont(new FontData("Verdana", 10, SWT.BOLD)); //$NON-NLS-1$
	//
	//	public BorderStyle selectedHeaderBorderStyle = new BorderStyle(-1, selectedHeaderFgColor, LineStyleEnum.SOLID);
	//
	//	protected void configureHeaderHasSelectionStyle(IConfigRegistry configRegistry) {
	//		Style cellStyle = new Style();
	//
	//		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, selectedHeaderFgColor);
	//		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, selectedHeaderBgColor);
	//		cellStyle.setAttributeValue(CellStyleAttributes.FONT, selectedHeaderFont);
	//		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, selectedHeaderBorderStyle);
	//
	//		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, GridRegion.COLUMN_HEADER);
	//		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, GridRegion.CORNER);
	//		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT, GridRegion.ROW_HEADER);
	//	}
}
