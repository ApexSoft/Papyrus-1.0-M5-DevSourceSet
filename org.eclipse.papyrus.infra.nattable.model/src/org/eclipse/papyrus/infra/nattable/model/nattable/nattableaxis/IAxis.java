/**
 * Copyright (c) 2013 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ILabelProviderConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IAxis</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * Common interface for the axis of the table. (An axis is a specific row or a specific column)
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IAxis#getManager <em>Manager</em>}</li>
 * <li>{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IAxis#getAlias <em>Alias</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.NattableaxisPackage#getIAxis()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IAxis extends EObject {

	/**
	 * Returns the value of the '<em><b>Manager</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This field can't be null. Reference the axis manager providing this IAxis.
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Manager</em>' reference.
	 * @see #setManager(AxisManagerRepresentation)
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.NattableaxisPackage#getIAxis_Manager()
	 * @model required="true"
	 * @generated
	 */
	AxisManagerRepresentation getManager();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IAxis#getManager <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Manager</em>' reference.
	 * @see #getManager()
	 * @generated
	 */
	void setManager(AxisManagerRepresentation value);

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An element can be displayed using an alias instead of its real name.
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.NattableaxisPackage#getIAxis_Alias()
	 * @model
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IAxis#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the elements represented by the IAxis
	 * <!-- end-model-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Object getElement();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the local label configuration to use for this IAxis. This configuration
	 * will override the label configuration defined in the AxisManagerConfiguration.
	 * <!-- end-model-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	ILabelProviderConfiguration getLocalLabelConfiguration();

} // IAxis
