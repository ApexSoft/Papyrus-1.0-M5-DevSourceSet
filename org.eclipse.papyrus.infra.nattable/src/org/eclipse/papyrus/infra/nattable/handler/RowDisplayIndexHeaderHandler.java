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
package org.eclipse.papyrus.infra.nattable.handler;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage;

/**
 * The handler to change the boolean value to display index header for row
 * 
 * @author Vincent Lorenzo
 * 
 */
public class RowDisplayIndexHeaderHandler extends AbstractRowChangeHeaderConfigurationHandler {

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.handler.AbstractChangeHeaderConfigurationHandler#getEditedFeature()
	 * 
	 * @return
	 */
	@Override
	protected EStructuralFeature getEditedFeature() {
		return NattableaxisconfigurationPackage.eINSTANCE.getAbstractHeaderAxisConfiguration_DisplayIndex();
	}
}
