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
package org.eclipse.papyrus.infra.nattable.utils;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;

/**
 * This object can used as context to find the best label provider and get the text to display.
 * Its allows to have the context of the value to use it in the label provider
 * 
 * @author Vincent Lorenzo
 * 
 */
public class LabelProviderContextElementWrapper implements ILabelProviderContextElementWrapper {

	/**
	 * the config registry of the table
	 */
	private IConfigRegistry registry;

	/**
	 * the object for which we want the label
	 */
	private Object object;

	/**
	 * *
	 * Constructor.
	 * 
	 * @param object
	 *        the cell for which we want the label/icon, ...
	 * @param registry
	 *        the registry used by nattable
	 */
	public LabelProviderContextElementWrapper(final Object object, final IConfigRegistry registry) {
		this.object = object;
		this.registry = registry;
	}


	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.utils.ILabelProviderContextElementWrapper#getConfigRegistry()
	 * 
	 * @return
	 */
	public IConfigRegistry getConfigRegistry() {
		return this.registry;
	}

	/**
	 * 
	 * @return
	 */
	public Object getObject() {
		return this.object;
	}

}
