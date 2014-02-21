/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
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
package org.eclipse.papyrus.infra.nattable.formatter;

import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.export.excel.DefaultExportFormatter;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;

/**
 * The export formatter to use to export the same text as the text displayed in the cells
 * 
 * @author Vincent Lorenzo
 * 
 */
public class PapyrusExportFormatter extends DefaultExportFormatter {

	/**
	 * 
	 * @see org.eclipse.nebula.widgets.nattable.export.IExportFormatter#formatForExport(org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell,
	 *      org.eclipse.nebula.widgets.nattable.config.IConfigRegistry)
	 * 
	 * @param cell
	 * @param configRegistry
	 * @return
	 */
	public Object formatForExport(ILayerCell cell, IConfigRegistry configRegistry) {
		Object dataValue = cell.getDataValue();
		IDisplayConverter displayConverter = configRegistry.getConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER, cell.getDisplayMode(), cell.getConfigLabels().getLabels());
		return displayConverter.canonicalToDisplayValue(cell, configRegistry, dataValue);
	}

}
