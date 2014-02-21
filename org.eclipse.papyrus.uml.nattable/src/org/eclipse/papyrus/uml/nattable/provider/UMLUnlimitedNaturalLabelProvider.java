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
package org.eclipse.papyrus.uml.nattable.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.papyrus.infra.nattable.utils.Constants;
import org.eclipse.papyrus.infra.nattable.utils.ILabelProviderCellContextElementWrapper;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * This label provider allows to display '*' instead of -1 for unlimitednatural
 * 
 * @author Vincent Lorenzo
 * 
 */
public class UMLUnlimitedNaturalLabelProvider extends AbstractUMLNattableCellLabelProvider {

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.provider.GenericCellLabelProvider#accept(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 */
	@Override
	public boolean accept(Object element) {
		if(element instanceof ILabelProviderCellContextElementWrapper) {
			final ILabelProviderCellContextElementWrapper cellWrapperContext = ((ILabelProviderCellContextElementWrapper)element);
			final IConfigRegistry registry = cellWrapperContext.getConfigRegistry();

			//we do some quick test on the value
			final Object value = cellWrapperContext.getObject();
			if(value instanceof Collection<?> && !((Collection<?>)value).iterator().hasNext()) {//if the value is en empty collection, we return false;
				return false;
			} else if(!(value instanceof Collection<?>) && !(value instanceof Integer)) { //if the value is not a collection and not an integer, we return false
				return false;
			}

			//now it is possible that we accepts the element
			final Object rowObject = getRowObject(cellWrapperContext, registry);
			final Object columnObject = getColumnObject(cellWrapperContext, registry);

			final List<Object> objects = getUMLObjects(rowObject, columnObject);
			if(objects.size() == 2) {
				final Object feature = objects.get(1);
				if(feature instanceof Property) {
					return UMLUtil.isUnlimitedNatural(((Property)feature).getType());
				}
			}
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.provider.GenericCellLabelProvider#getText(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 */
	@Override
	public String getText(Object element) {
		final String str = super.getText(element);
		return str.replaceAll(Constants.INFINITE_MINUS_ONE, Constants.INFINITY_STAR);
	}
}
