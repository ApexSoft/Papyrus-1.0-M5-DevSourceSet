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
package org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.infra.nattable.model.nattable.NattablePackage;

import org.eclipse.papyrus.infra.nattable.model.nattable.impl.NattablePackageImpl;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.NattableaxisPackage;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.impl.NattableaxisPackageImpl;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.impl.NattableaxisconfigurationPackageImpl;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisprovider.NattableaxisproviderPackage;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisprovider.impl.NattableaxisproviderPackageImpl;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattablecell.NattablecellPackage;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablecell.impl.NattablecellPackageImpl;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableconfiguration.NattableconfigurationPackage;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableconfiguration.impl.NattableconfigurationPackageImpl;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.FeatureLabelProviderConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ILabelProviderConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.NattablelabelproviderFactory;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.NattablelabelproviderPackage;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ObjectLabelProviderConfiguration;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableproblem.NattableproblemPackage;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableproblem.impl.NattableproblemPackageImpl;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattabletester.NattabletesterPackage;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattabletester.impl.NattabletesterPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class NattablelabelproviderPackageImpl extends EPackageImpl implements NattablelabelproviderPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass iLabelProviderConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass featureLabelProviderConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass objectLabelProviderConfigurationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package
	 * package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.NattablelabelproviderPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NattablelabelproviderPackageImpl() {
		super(eNS_URI, NattablelabelproviderFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link NattablelabelproviderPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
	 * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NattablelabelproviderPackage init() {
		if(isInited)
			return (NattablelabelproviderPackage)EPackage.Registry.INSTANCE.getEPackage(NattablelabelproviderPackage.eNS_URI);

		// Obtain or create and register package
		NattablelabelproviderPackageImpl theNattablelabelproviderPackage = (NattablelabelproviderPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NattablelabelproviderPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NattablelabelproviderPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		NattablePackageImpl theNattablePackage = (NattablePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattablePackage.eNS_URI) instanceof NattablePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattablePackage.eNS_URI) : NattablePackage.eINSTANCE);
		NattableconfigurationPackageImpl theNattableconfigurationPackage = (NattableconfigurationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattableconfigurationPackage.eNS_URI) instanceof NattableconfigurationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattableconfigurationPackage.eNS_URI) : NattableconfigurationPackage.eINSTANCE);
		NattableaxisproviderPackageImpl theNattableaxisproviderPackage = (NattableaxisproviderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattableaxisproviderPackage.eNS_URI) instanceof NattableaxisproviderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattableaxisproviderPackage.eNS_URI) : NattableaxisproviderPackage.eINSTANCE);
		NattableaxisconfigurationPackageImpl theNattableaxisconfigurationPackage = (NattableaxisconfigurationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattableaxisconfigurationPackage.eNS_URI) instanceof NattableaxisconfigurationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattableaxisconfigurationPackage.eNS_URI) : NattableaxisconfigurationPackage.eINSTANCE);
		NattabletesterPackageImpl theNattabletesterPackage = (NattabletesterPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattabletesterPackage.eNS_URI) instanceof NattabletesterPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattabletesterPackage.eNS_URI) : NattabletesterPackage.eINSTANCE);
		NattableaxisPackageImpl theNattableaxisPackage = (NattableaxisPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattableaxisPackage.eNS_URI) instanceof NattableaxisPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattableaxisPackage.eNS_URI) : NattableaxisPackage.eINSTANCE);
		NattablecellPackageImpl theNattablecellPackage = (NattablecellPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattablecellPackage.eNS_URI) instanceof NattablecellPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattablecellPackage.eNS_URI) : NattablecellPackage.eINSTANCE);
		NattableproblemPackageImpl theNattableproblemPackage = (NattableproblemPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(NattableproblemPackage.eNS_URI) instanceof NattableproblemPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(NattableproblemPackage.eNS_URI) : NattableproblemPackage.eINSTANCE);

		// Create package meta-data objects
		theNattablelabelproviderPackage.createPackageContents();
		theNattablePackage.createPackageContents();
		theNattableconfigurationPackage.createPackageContents();
		theNattableaxisproviderPackage.createPackageContents();
		theNattableaxisconfigurationPackage.createPackageContents();
		theNattabletesterPackage.createPackageContents();
		theNattableaxisPackage.createPackageContents();
		theNattablecellPackage.createPackageContents();
		theNattableproblemPackage.createPackageContents();

		// Initialize created meta-data
		theNattablelabelproviderPackage.initializePackageContents();
		theNattablePackage.initializePackageContents();
		theNattableconfigurationPackage.initializePackageContents();
		theNattableaxisproviderPackage.initializePackageContents();
		theNattableaxisconfigurationPackage.initializePackageContents();
		theNattabletesterPackage.initializePackageContents();
		theNattableaxisPackage.initializePackageContents();
		theNattablecellPackage.initializePackageContents();
		theNattableproblemPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNattablelabelproviderPackage.freeze();


		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NattablelabelproviderPackage.eNS_URI, theNattablelabelproviderPackage);
		return theNattablelabelproviderPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getILabelProviderConfiguration() {
		return iLabelProviderConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFeatureLabelProviderConfiguration() {
		return featureLabelProviderConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFeatureLabelProviderConfiguration_DisplayIsDerived() {
		return (EAttribute)featureLabelProviderConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFeatureLabelProviderConfiguration_DisplayType() {
		return (EAttribute)featureLabelProviderConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFeatureLabelProviderConfiguration_DisplayMultiplicity() {
		return (EAttribute)featureLabelProviderConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFeatureLabelProviderConfiguration_DisplayName() {
		return (EAttribute)featureLabelProviderConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getObjectLabelProviderConfiguration() {
		return objectLabelProviderConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getObjectLabelProviderConfiguration_DisplayIcon() {
		return (EAttribute)objectLabelProviderConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getObjectLabelProviderConfiguration_DisplayLabel() {
		return (EAttribute)objectLabelProviderConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NattablelabelproviderFactory getNattablelabelproviderFactory() {
		return (NattablelabelproviderFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if(isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		iLabelProviderConfigurationEClass = createEClass(ILABEL_PROVIDER_CONFIGURATION);

		featureLabelProviderConfigurationEClass = createEClass(FEATURE_LABEL_PROVIDER_CONFIGURATION);
		createEAttribute(featureLabelProviderConfigurationEClass, FEATURE_LABEL_PROVIDER_CONFIGURATION__DISPLAY_IS_DERIVED);
		createEAttribute(featureLabelProviderConfigurationEClass, FEATURE_LABEL_PROVIDER_CONFIGURATION__DISPLAY_TYPE);
		createEAttribute(featureLabelProviderConfigurationEClass, FEATURE_LABEL_PROVIDER_CONFIGURATION__DISPLAY_MULTIPLICITY);
		createEAttribute(featureLabelProviderConfigurationEClass, FEATURE_LABEL_PROVIDER_CONFIGURATION__DISPLAY_NAME);

		objectLabelProviderConfigurationEClass = createEClass(OBJECT_LABEL_PROVIDER_CONFIGURATION);
		createEAttribute(objectLabelProviderConfigurationEClass, OBJECT_LABEL_PROVIDER_CONFIGURATION__DISPLAY_ICON);
		createEAttribute(objectLabelProviderConfigurationEClass, OBJECT_LABEL_PROVIDER_CONFIGURATION__DISPLAY_LABEL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if(isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		iLabelProviderConfigurationEClass.getESuperTypes().add(ecorePackage.getEModelElement());
		featureLabelProviderConfigurationEClass.getESuperTypes().add(this.getObjectLabelProviderConfiguration());
		objectLabelProviderConfigurationEClass.getESuperTypes().add(this.getILabelProviderConfiguration());

		// Initialize classes, features, and operations; add parameters
		initEClass(iLabelProviderConfigurationEClass, ILabelProviderConfiguration.class, "ILabelProviderConfiguration", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(featureLabelProviderConfigurationEClass, FeatureLabelProviderConfiguration.class, "FeatureLabelProviderConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getFeatureLabelProviderConfiguration_DisplayIsDerived(), ecorePackage.getEBoolean(), "displayIsDerived", "true", 1, 1, FeatureLabelProviderConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getFeatureLabelProviderConfiguration_DisplayType(), ecorePackage.getEBoolean(), "displayType", "true", 1, 1, FeatureLabelProviderConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getFeatureLabelProviderConfiguration_DisplayMultiplicity(), ecorePackage.getEBoolean(), "displayMultiplicity", "true", 1, 1, FeatureLabelProviderConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getFeatureLabelProviderConfiguration_DisplayName(), ecorePackage.getEBoolean(), "displayName", "true", 1, 1, FeatureLabelProviderConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(objectLabelProviderConfigurationEClass, ObjectLabelProviderConfiguration.class, "ObjectLabelProviderConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getObjectLabelProviderConfiguration_DisplayIcon(), ecorePackage.getEBoolean(), "displayIcon", "true", 1, 1, ObjectLabelProviderConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getObjectLabelProviderConfiguration_DisplayLabel(), ecorePackage.getEBoolean(), "displayLabel", "true", 1, 1, ObjectLabelProviderConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
	}

} //NattablelabelproviderPackageImpl
