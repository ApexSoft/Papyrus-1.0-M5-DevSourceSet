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
package org.eclipse.papyrus.infra.nattable.properties.observable;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.infra.nattable.model.nattable.Table;

/**
 * Observable value for the element type id
 * 
 * @author VL222926
 * 
 */
public class RowPasteObjectPostActionsObservableValue extends AbstractPasteObjectPostActionsObservableValue {

	/**
	 * 
	 * Constructor.
	 * 
	 * @param table
	 */
	public RowPasteObjectPostActionsObservableValue(final EditingDomain domain, final Table table) {
		super(domain, table, false);
	}
}
