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
package org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ILabelProviderConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Axis Manager Representation</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * This class is used to represents in the model the java Axis Manager.
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getAxisManagerId <em>Axis Manager
 * Id</em>}</li>
 * <li>{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getLabelProviderContext <em>Label
 * Provider Context</em>}</li>
 * <li>{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getHeaderLabelConfiguration <em>
 * Header Label Configuration</em>}</li>
 * <li>{@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getSpecificAxisConfigurations <em>
 * Specific Axis Configurations</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage#getAxisManagerRepresentation()
 * @model
 * @generated
 */
public interface AxisManagerRepresentation extends EObject {

	/**
	 * Returns the value of the '<em><b>Axis Manager Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This id allows to find the Java Axis Manager represented by this element.
	 * To find it you must use AxisManagerFactory.INSTANCE.getAxisManager(AxisManagerRepresentation)
	 * To register a Java Axis Manager,you must use the extension point : org.eclipse.papyrus.infra.nattable.axismanager
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Axis Manager Id</em>' attribute.
	 * @see #setAxisManagerId(String)
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage#getAxisManagerRepresentation_AxisManagerId()
	 * @model required="true"
	 * @generated
	 */
	String getAxisManagerId();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getAxisManagerId
	 * <em>Axis Manager Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Axis Manager Id</em>' attribute.
	 * @see #getAxisManagerId()
	 * @generated
	 */
	void setAxisManagerId(String value);

	/**
	 * Returns the value of the '<em><b>Label Provider Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The context on which the label provider to use for this AxisManagerRepresentation are declared.
	 * (see the Papyrus Label Provider Service for further informations)
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Label Provider Context</em>' attribute.
	 * @see #setLabelProviderContext(String)
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage#getAxisManagerRepresentation_LabelProviderContext()
	 * @model
	 * @generated
	 */
	String getLabelProviderContext();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getLabelProviderContext
	 * <em>Label Provider Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Label Provider Context</em>' attribute.
	 * @see #getLabelProviderContext()
	 * @generated
	 */
	void setLabelProviderContext(String value);

	/**
	 * Returns the value of the '<em><b>Header Label Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The label provider configuration to use for the header of the axis provided by the represented AxisManager
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Header Label Configuration</em>' reference.
	 * @see #setHeaderLabelConfiguration(ILabelProviderConfiguration)
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage#getAxisManagerRepresentation_HeaderLabelConfiguration()
	 * @model required="true"
	 * @generated
	 */
	ILabelProviderConfiguration getHeaderLabelConfiguration();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation#getHeaderLabelConfiguration
	 * <em>Header Label Configuration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *        the new value of the '<em>Header Label Configuration</em>' reference.
	 * @see #getHeaderLabelConfiguration()
	 * @generated
	 */
	void setHeaderLabelConfiguration(ILabelProviderConfiguration value);

	/**
	 * Returns the value of the '<em><b>Specific Axis Configurations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.IAxisConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A list of specific configurations to use for this axis.
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Specific Axis Configurations</em>' reference list.
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage#getAxisManagerRepresentation_SpecificAxisConfigurations()
	 * @model
	 * @generated
	 */
	EList<IAxisConfiguration> getSpecificAxisConfigurations();

} // AxisManagerRepresentation
