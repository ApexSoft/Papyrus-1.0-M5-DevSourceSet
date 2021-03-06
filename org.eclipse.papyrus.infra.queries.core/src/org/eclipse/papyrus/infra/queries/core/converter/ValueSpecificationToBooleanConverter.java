/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.queries.core.converter;

import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * Converter from a {@link ValueSpecification} to a {@link Boolean} value
 */
public class ValueSpecificationToBooleanConverter extends Converter implements IConverter {

	/**
	 * Creates a new ValueSpecificationToBooleanConverter.
	 */
	public ValueSpecificationToBooleanConverter() {
		super(ValueSpecification.class, boolean.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public Object convert(Object fromObject) {
		return ((ValueSpecification)fromObject).booleanValue();
	}

}
