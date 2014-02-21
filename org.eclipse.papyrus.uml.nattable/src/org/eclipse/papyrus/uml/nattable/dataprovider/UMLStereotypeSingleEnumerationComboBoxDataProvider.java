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
package org.eclipse.papyrus.uml.nattable.dataprovider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.nattable.edit.editor.IComboBoxDataProvider;
import org.eclipse.papyrus.infra.nattable.manager.table.ITableAxisElementProvider;
import org.eclipse.papyrus.infra.nattable.utils.AxisUtils;
import org.eclipse.papyrus.uml.nattable.utils.UMLTableUtils;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * 
 * @author Vincent Lorenzo
 *         This class provides the possibles enumeration literal for properties of stereotype typed with a UMLEnumerationLiteral
 */
public class UMLStereotypeSingleEnumerationComboBoxDataProvider implements IComboBoxDataProvider {

	/**
	 * The table axis element provider
	 */
	private ITableAxisElementProvider elementProvider;

	/**
	 * the obejct represented by the axis
	 */
	private Object axisElement;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param axisElement
	 *        the obejct represented by the axis
	 * @param elementProvider
	 *        The table axis element provider
	 */
	public UMLStereotypeSingleEnumerationComboBoxDataProvider(final Object axisElement, final ITableAxisElementProvider elementProvider) {
		this.axisElement = axisElement;
		this.elementProvider = elementProvider;
	}

	/**
	 * 
	 * @see org.eclipse.nebula.widgets.nattable.edit.editor.IComboBoxDataProvider#getValues(int, int)
	 * 
	 * @param columnIndex
	 * @param rowIndex
	 * 
	 * @return
	 *         the list of the available enumeration literal
	 */
	public List<?> getValues(int columnIndex, int rowIndex) {
		final List<Object> literals = new ArrayList<Object>();
		Object el = this.elementProvider.getColumnElement(columnIndex);
		Object rowElement = this.elementProvider.getRowElement(rowIndex);
		el = AxisUtils.getRepresentedElement(el);
		rowElement = AxisUtils.getRepresentedElement(rowElement);
		Element modelElement = null;
		if(rowElement instanceof Element && el == this.axisElement) {
			modelElement = (Element)rowElement;
		} else if(rowElement == this.axisElement && el instanceof Element) {
			modelElement = (Element)el;
		}
		if(modelElement != null) {
			final String id = AxisUtils.getPropertyId(this.axisElement);
			final Property property = UMLTableUtils.getRealStereotypeProperty(modelElement, id);
			final List<Stereotype> ste = UMLTableUtils.getAppliedStereotypesWithThisProperty(modelElement, id);
			if(ste.size() == 1) {
				final Stereotype current = ste.get(0);
				final EObject steAppl = modelElement.getStereotypeApplication(current);
				final EStructuralFeature feature = steAppl.eClass().getEStructuralFeature(property.getName());
				final EEnum eenum = (EEnum)feature.getEType();
				for(final EEnumLiteral instances : eenum.getELiterals()) {
					literals.add(instances.getInstance());
				}
			}
		}
		return literals;
	}

}
