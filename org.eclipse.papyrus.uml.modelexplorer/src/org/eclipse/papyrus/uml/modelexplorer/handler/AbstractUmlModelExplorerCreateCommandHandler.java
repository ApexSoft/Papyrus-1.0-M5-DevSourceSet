/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Juan Cadavid (CEA LIST) juan.cadavid@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.modelexplorer.handler;

import org.eclipse.papyrus.uml.modelexplorer.util.ModelExplorerUtils;
import org.eclipse.papyrus.uml.service.types.handlers.AbstractUmlCreateCommandHandler;
import org.eclipse.papyrus.uml.service.types.utils.ICommandContext;


/**
 * Abstract handler supertype for every generated handler for the creation of UML elements from the model explorer.
 * ("Create child")
 */
public abstract class AbstractUmlModelExplorerCreateCommandHandler extends AbstractUmlCreateCommandHandler {

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.service.types.handlers.AbstractCommandHandler#getCommandContext()
	 * 
	 * @return
	 */
	protected ICommandContext getCommandContext() {
		return ModelExplorerUtils.getSelectionCommandContext();
	}



}
