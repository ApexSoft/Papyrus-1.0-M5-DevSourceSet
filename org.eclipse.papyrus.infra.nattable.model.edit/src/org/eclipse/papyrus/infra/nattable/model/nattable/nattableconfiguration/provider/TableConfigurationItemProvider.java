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
package org.eclipse.papyrus.infra.nattable.model.nattable.nattableconfiguration.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationFactory;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisprovider.NattableaxisproviderFactory;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattableconfiguration.NattableconfigurationPackage;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableconfiguration.TableConfiguration;

import org.eclipse.papyrus.infra.nattable.model.nattable.nattabletester.NattabletesterFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.papyrus.infra.nattable.model.nattable.nattableconfiguration.TableConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TableConfigurationItemProvider extends TableNamedElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TableConfigurationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if(itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addTypePropertyDescriptor(object);
			addIconPathPropertyDescriptor(object);
			addCellEditorDeclarationPropertyDescriptor(object);
			addDefaultRowAxisProviderPropertyDescriptor(object);
			addDefaultColumnAxisProviderPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_TableConfiguration_type_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", "_UI_TableConfiguration_type_feature", "_UI_TableConfiguration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__TYPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Icon Path feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIconPathPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_TableConfiguration_iconPath_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", "_UI_TableConfiguration_iconPath_feature", "_UI_TableConfiguration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ICON_PATH, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Cell Editor Declaration feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCellEditorDeclarationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_TableConfiguration_cellEditorDeclaration_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", "_UI_TableConfiguration_cellEditorDeclaration_feature", "_UI_TableConfiguration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__CELL_EDITOR_DECLARATION, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Default Row Axis Provider feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDefaultRowAxisProviderPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_TableConfiguration_defaultRowAxisProvider_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", "_UI_TableConfiguration_defaultRowAxisProvider_feature", "_UI_TableConfiguration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__DEFAULT_ROW_AXIS_PROVIDER, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Default Column Axis Provider feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDefaultColumnAxisProviderPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_TableConfiguration_defaultColumnAxisProvider_feature"), //$NON-NLS-1$
			getString("_UI_PropertyDescriptor_description", "_UI_TableConfiguration_defaultColumnAxisProvider_feature", "_UI_TableConfiguration_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__DEFAULT_COLUMN_AXIS_PROVIDER, true, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if(childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__CREATION_TESTER);
			childrenFeatures.add(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_HEADER_AXIS_CONFIGURATION);
			childrenFeatures.add(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_HEADER_AXIS_CONFIGURATION);
			childrenFeatures.add(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_AXIS_PROVIDERS);
			childrenFeatures.add(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_AXIS_PROVIDERS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns TableConfiguration.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TableConfiguration")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TableConfiguration)object).getName();
		return label == null || label.length() == 0 ? getString("_UI_TableConfiguration_type") : //$NON-NLS-1$
		getString("_UI_TableConfiguration_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch(notification.getFeatureID(TableConfiguration.class)) {
		case NattableconfigurationPackage.TABLE_CONFIGURATION__TYPE:
		case NattableconfigurationPackage.TABLE_CONFIGURATION__ICON_PATH:
		case NattableconfigurationPackage.TABLE_CONFIGURATION__CELL_EDITOR_DECLARATION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case NattableconfigurationPackage.TABLE_CONFIGURATION__CREATION_TESTER:
		case NattableconfigurationPackage.TABLE_CONFIGURATION__ROW_HEADER_AXIS_CONFIGURATION:
		case NattableconfigurationPackage.TABLE_CONFIGURATION__COLUMN_HEADER_AXIS_CONFIGURATION:
		case NattableconfigurationPackage.TABLE_CONFIGURATION__COLUMN_AXIS_PROVIDERS:
		case NattableconfigurationPackage.TABLE_CONFIGURATION__ROW_AXIS_PROVIDERS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__CREATION_TESTER, NattabletesterFactory.eINSTANCE.createJavaTableTester()));

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_HEADER_AXIS_CONFIGURATION, NattableaxisconfigurationFactory.eINSTANCE.createTableHeaderAxisConfiguration()));

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_HEADER_AXIS_CONFIGURATION, NattableaxisconfigurationFactory.eINSTANCE.createTableHeaderAxisConfiguration()));

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_AXIS_PROVIDERS, NattableaxisproviderFactory.eINSTANCE.createSlaveObjectAxisProvider()));

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_AXIS_PROVIDERS, NattableaxisproviderFactory.eINSTANCE.createMasterObjectAxisProvider()));

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_AXIS_PROVIDERS, NattableaxisproviderFactory.eINSTANCE.createSlaveObjectAxisProvider()));

		newChildDescriptors.add(createChildParameter(NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_AXIS_PROVIDERS, NattableaxisproviderFactory.eINSTANCE.createMasterObjectAxisProvider()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_HEADER_AXIS_CONFIGURATION || childFeature == NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_HEADER_AXIS_CONFIGURATION || childFeature == NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__COLUMN_AXIS_PROVIDERS || childFeature == NattableconfigurationPackage.Literals.TABLE_CONFIGURATION__ROW_AXIS_PROVIDERS;

		if(qualify) {
			return getString("_UI_CreateChild_text2", //$NON-NLS-1$
				new Object[]{ getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
