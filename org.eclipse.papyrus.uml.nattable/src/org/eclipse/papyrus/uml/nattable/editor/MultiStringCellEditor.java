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
package org.eclipse.papyrus.uml.nattable.editor;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.infra.nattable.manager.table.ITableAxisElementProvider;
import org.eclipse.papyrus.infra.widgets.creation.ReferenceValueFactory;
import org.eclipse.papyrus.infra.widgets.creation.StringEditionFactory;
import org.eclipse.papyrus.infra.widgets.editors.IElementSelector;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.papyrus.infra.widgets.selectors.StringSelector;

/**
 * The Multi String Cell Editor
 * 
 * @author Vincent Lorenzo
 * 
 */
public class MultiStringCellEditor extends AbstractUMLMultiValueCellEditor {

	/**
	 * 
	 * Constructor.
	 * 
	 * @param axisElement
	 * @param elementProvider
	 */
	public MultiStringCellEditor(Object axisElement, ITableAxisElementProvider elementProvider) {
		super(axisElement, elementProvider);
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.nattable.editor.AbstractUMLMultiValueCellEditor#getFactory()
	 * 
	 * @return
	 */
	@Override
	protected ReferenceValueFactory getFactory() {
		return new StringEditionFactory();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.nattable.editor.AbstractUMLMultiValueCellEditor#getElementSelector(boolean,
	 *      org.eclipse.jface.viewers.ILabelProvider, org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider)
	 * 
	 * @param isUnique
	 * @param labelProvider
	 * @param contentProvider
	 * @return
	 */
	@Override
	protected IElementSelector getElementSelector(boolean isUnique, ILabelProvider labelProvider, IStaticContentProvider contentProvider) {
		return new StringSelector();
	}

}
