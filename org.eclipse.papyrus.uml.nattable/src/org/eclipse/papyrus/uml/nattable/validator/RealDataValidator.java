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
package org.eclipse.papyrus.uml.nattable.validator;

import org.eclipse.nebula.widgets.nattable.data.validate.DataValidator;
import org.eclipse.papyrus.infra.widgets.validator.RealInputValidator;

/**
 * 
 * Validator for Real values
 * 
 */
public class RealDataValidator extends DataValidator {

	/**
	 * 
	 * @see org.eclipse.nebula.widgets.nattable.data.validate.DataValidator#validate(int, int, java.lang.Object)
	 * 
	 * @param columnIndex
	 * @param rowIndex
	 * @param newValue
	 * @return
	 */
	@Override
	public boolean validate(int columnIndex, int rowIndex, Object newValue) {
		final RealInputValidator validator = new RealInputValidator();
		return validator.isValid(newValue.toString()) == null;
	}

}
