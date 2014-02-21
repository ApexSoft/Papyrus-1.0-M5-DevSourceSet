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
package org.eclipse.papyrus.infra.nattable.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.papyrus.infra.nattable.model.nattable.NattablePackage;
import org.eclipse.papyrus.infra.nattable.model.nattable.Table;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AbstractHeaderAxisConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.AxisManagerRepresentation;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.IAxisConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.LocalTableHeaderAxisConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationFactory;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.NattableaxisconfigurationPackage;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxisconfiguration.TableHeaderAxisConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ILabelProviderConfiguration;
import org.eclipse.papyrus.infra.nattable.utils.HeaderAxisConfigurationManagementUtils;
import org.eclipse.papyrus.infra.nattable.utils.TableEditingDomainUtils;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;

/**
 * This class provides some useful commands used to edit the table properties
 * 
 * @author vl222926
 * 
 */
public class TableCommands {

	/**
	 * 
	 * Constructor.
	 * 
	 */
	private TableCommands() {
		//to prevent instanciation
	}

	/**
	 * 
	 * @param table
	 *        the table
	 * @param feature
	 *        the edited feature
	 * @param newValue
	 *        the new value for this feature
	 * @return
	 *         the command to change a value in the row configuration header of a table
	 */
	//TODO : the handler must use me
	public static ICommand getSetRowHeaderConfigurationValueCommand(final Table table, final EStructuralFeature feature, final Object newValue) {
		final TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		final CompositeCommand compositeCommand = new CompositeCommand("SetRowHeaderConfigurationCommand"); //$NON-NLS-1$
		EObject elementToEdit = HeaderAxisConfigurationManagementUtils.getRowAbstractHeaderAxisUsedInTable(table);
		if(elementToEdit instanceof TableHeaderAxisConfiguration) {
			elementToEdit = HeaderAxisConfigurationManagementUtils.transformToLocalHeaderConfiguration((TableHeaderAxisConfiguration)elementToEdit);
			SetRequest request = null;
			if(!table.isInvertAxis()) {
				request = new SetRequest(domain, table, NattablePackage.eINSTANCE.getTable_LocalRowHeaderAxisConfiguration(), elementToEdit);
			} else {
				request = new SetRequest(domain, table, NattablePackage.eINSTANCE.getTable_LocalColumnHeaderAxisConfiguration(), elementToEdit);
			}
			final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(table);
			final ICommand cmd = provider.getEditCommand(request);
			compositeCommand.add(cmd);
		}
		final SetRequest request = new SetRequest(domain, elementToEdit, feature, newValue);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(elementToEdit);
		final ICommand cmd = provider.getEditCommand(request);
		compositeCommand.add(cmd);
		return compositeCommand;
	}

	/**
	 * 
	 * @param table
	 *        the table
	 * @param feature
	 *        the edited feature
	 * @param newValue
	 *        the new value for this feature
	 * @return
	 *         the command to change a value in the column configuration header of a table
	 */
	//TODO : the handler must use me
	public static ICommand getSetColumnHeaderConfigurationValueCommand(final Table table, final EStructuralFeature feature, final Object newValue) {
		final TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		final CompositeCommand compositeCommand = new CompositeCommand("SetColumnHeaderConfigurationCommand"); //$NON-NLS-1$
		EObject elementToEdit = HeaderAxisConfigurationManagementUtils.getColumnAbstractHeaderAxisUsedInTable(table);
		if(elementToEdit instanceof TableHeaderAxisConfiguration) {
			elementToEdit = HeaderAxisConfigurationManagementUtils.transformToLocalHeaderConfiguration((TableHeaderAxisConfiguration)elementToEdit);
			SetRequest request = null;
			if(table.isInvertAxis()) {
				request = new SetRequest(domain, table, NattablePackage.eINSTANCE.getTable_LocalRowHeaderAxisConfiguration(), elementToEdit);
			} else {
				request = new SetRequest(domain, table, NattablePackage.eINSTANCE.getTable_LocalColumnHeaderAxisConfiguration(), elementToEdit);
			}
			final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(table);
			final ICommand cmd = provider.getEditCommand(request);
			compositeCommand.add(cmd);
		}
		final SetRequest request = new SetRequest(domain, elementToEdit, feature, newValue);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(elementToEdit);
		final ICommand cmd = provider.getEditCommand(request);
		compositeCommand.add(cmd);
		return compositeCommand;
	}

	/**
	 * 
	 * @param table
	 *        the table
	 * @param usedLabelConfiguration
	 *        the used label configuration
	 * @param editedFeature
	 *        the edited feature
	 * @param newValue
	 *        the new value for this feature
	 * @return
	 *         the command to modify the feature value in the label configuration of the table. This command adds the localLabelConfiguration when it
	 *         is required
	 */
	//TODO the handler must use me
	public static final ICommand getSetColumnLabelConfigurationValueCommand(final Table table, final ILabelProviderConfiguration usedLabelConfiguration, final EStructuralFeature editedFeature, final Object newValue) {
		final CompositeCommand cmd = new CompositeCommand("ChangeColumnLabelConfigurationValueCommand"); //$NON-NLS-1$
		TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		ILabelProviderConfiguration editedLabelConf;
		if(usedLabelConfiguration.eContainer() instanceof TableHeaderAxisConfiguration) {
			editedLabelConf = EcoreUtil.copy(usedLabelConfiguration);
			cmd.add(getRegisterLocalColumnLabelConfigurationCommand(table, usedLabelConfiguration, editedLabelConf));
		} else {
			editedLabelConf = usedLabelConfiguration;
		}
		final IEditCommandRequest request = new SetRequest(domain, editedLabelConf, editedFeature, newValue);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(editedLabelConf);
		cmd.add(provider.getEditCommand(request));
		return cmd;
	}

	/**
	 * 
	 * @param table
	 *        the table
	 * @param usedLabelConfiguration
	 *        the used label configuration
	 * @param editedFeature
	 *        the edited feature
	 * @param newValue
	 *        the new value for this feature
	 * @return
	 *         the command to modify the feature value in the label configuration of the table. This command adds the localLabelConfiguration when it
	 *         is required
	 */
	//TODO the handler must use me
	public static final ICommand getSetRowLabelConfigurationValueCommand(final Table table, final ILabelProviderConfiguration usedLabelConfiguration, final EStructuralFeature editedFeature, final Object newValue) {
		final CompositeCommand cmd = new CompositeCommand("ChangeRowLabelConfigurationValueCommand"); //$NON-NLS-1$
		TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		ILabelProviderConfiguration editedLabelConf;
		if(usedLabelConfiguration.eContainer() instanceof TableHeaderAxisConfiguration) {
			editedLabelConf = EcoreUtil.copy(usedLabelConfiguration);
			cmd.add(getRegisterLocalRowLabelConfigurationCommand(table, usedLabelConfiguration, editedLabelConf));
		} else {
			editedLabelConf = usedLabelConfiguration;
		}
		final IEditCommandRequest request = new SetRequest(domain, editedLabelConf, editedFeature, newValue);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(editedLabelConf);
		cmd.add(provider.getEditCommand(request));
		return cmd;
	}


	/**
	 * 
	 * @param table
	 *        the table
	 * @param tableLabelConfiguration
	 *        the table LabelConfiguration
	 * @param localTableLabelConfiguration
	 *        the local table label configuration
	 * @return
	 *         the command to register the local column label configuration to the table
	 */
	private static final ICommand getRegisterLocalColumnLabelConfigurationCommand(final Table table, final ILabelProviderConfiguration tableLabelConfiguration, final ILabelProviderConfiguration localTableLabelConfiguration) {
		final AbstractHeaderAxisConfiguration abstractHeaderAxisUsedInTable = HeaderAxisConfigurationManagementUtils.getColumnAbstractHeaderAxisUsedInTable(table);

		EStructuralFeature axisConfigurationFeature = NattablePackage.eINSTANCE.getTable_LocalColumnHeaderAxisConfiguration();
		if(!table.isInvertAxis()) {
			axisConfigurationFeature = NattablePackage.eINSTANCE.getTable_LocalRowHeaderAxisConfiguration();
		}

		final TableHeaderAxisConfiguration headerAxisConfig = (TableHeaderAxisConfiguration)HeaderAxisConfigurationManagementUtils.getColumnAbstractHeaderAxisInTableConfiguration(table);
		return getRegisterLocalLabelConfigurationCommand("ChangeColumnHeaderLabelConfigurationCommand", table, abstractHeaderAxisUsedInTable, headerAxisConfig, axisConfigurationFeature, localTableLabelConfiguration, tableLabelConfiguration); //$NON-NLS-1$
	}


	/**
	 * 
	 * @param table
	 *        the table
	 * @param tableLabelConfiguration
	 *        the table LabelConfiguration
	 * @param localTableLabelConfiguration
	 *        the local table label configuration
	 * @return
	 *         the command to register the local row label configuration to the table
	 */
	private static final ICommand getRegisterLocalRowLabelConfigurationCommand(final Table table, final ILabelProviderConfiguration tableLabelConfiguration, final ILabelProviderConfiguration localTableLabelConfiguration) {
		AbstractHeaderAxisConfiguration abstractHeaderAxisUsedInTable = HeaderAxisConfigurationManagementUtils.getRowAbstractHeaderAxisUsedInTable(table);

		EStructuralFeature axisConfigurationFeature = NattablePackage.eINSTANCE.getTable_LocalColumnHeaderAxisConfiguration();
		if(table.isInvertAxis()) {
			axisConfigurationFeature = NattablePackage.eINSTANCE.getTable_LocalRowHeaderAxisConfiguration();
		}

		final TableHeaderAxisConfiguration headerAxisConfig = (TableHeaderAxisConfiguration)HeaderAxisConfigurationManagementUtils.getRowAbstractHeaderAxisInTableConfiguration(table);
		return getRegisterLocalLabelConfigurationCommand("ChangeRowHeaderLabelConfigurationCommand", table, abstractHeaderAxisUsedInTable, headerAxisConfig, axisConfigurationFeature, localTableLabelConfiguration, tableLabelConfiguration); //$NON-NLS-1$
	}

	/**
	 * 
	 * @param commandName
	 *        the name of the command
	 * @param table
	 *        the table to edit
	 * @param headerAxisConfigurationUsedInTable
	 *        the headerAxisConfiguration currently used in the table
	 * @param tableHeaderAxisConfiguration
	 *        the table header axis configuration defined in the table configuration
	 * @param axisConfigurationFeature
	 *        the feature to use to register a local HeaderAxisConfiguration if it is required
	 * @param localTableLabelConfiguration
	 *        the local table label configuration to register
	 * @param tableLabelConfiguration
	 *        the table label configuration
	 * @return
	 *         the command to register the local label configuration
	 */
	private static final ICommand getRegisterLocalLabelConfigurationCommand(final String commandName, final Table table, final AbstractHeaderAxisConfiguration headerAxisConfigurationUsedInTable, final TableHeaderAxisConfiguration tableHeaderAxisConfiguration, final EStructuralFeature axisConfigurationFeature, final ILabelProviderConfiguration localTableLabelConfiguration, final ILabelProviderConfiguration tableLabelConfiguration) {
		final TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		final CompositeCommand cmd = new CompositeCommand(commandName);

		//1. we must get or create the required LocalTableHeaderAxisConfiguration
		LocalTableHeaderAxisConfiguration localConfig = null;
		if(headerAxisConfigurationUsedInTable instanceof LocalTableHeaderAxisConfiguration) {
			localConfig = (LocalTableHeaderAxisConfiguration)headerAxisConfigurationUsedInTable;
		} else if(headerAxisConfigurationUsedInTable instanceof TableHeaderAxisConfiguration) {
			//we can't edit it, because it's comes from the initial configuration
			localConfig = HeaderAxisConfigurationManagementUtils.transformToLocalHeaderConfiguration((TableHeaderAxisConfiguration)headerAxisConfigurationUsedInTable);
			final IEditCommandRequest request = new SetRequest(domain, table, axisConfigurationFeature, headerAxisConfigurationUsedInTable);
			final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(table);
			cmd.add(provider.getEditCommand(request));
		}

		//2. this one must store the new label configuration
		final IEditCommandRequest request = new SetRequest(domain, headerAxisConfigurationUsedInTable, NattableaxisconfigurationPackage.eINSTANCE.getAbstractHeaderAxisConfiguration_OwnedLabelConfigurations(), localTableLabelConfiguration);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(headerAxisConfigurationUsedInTable);
		cmd.add(provider.getEditCommand(request));

		//3. we must get or create the AxisManagerConfiguration(s)
		final List<AxisManagerConfiguration> axisManagerConfiguration = new ArrayList<AxisManagerConfiguration>();
		for(final AxisManagerRepresentation current : tableHeaderAxisConfiguration.getAxisManagers()) {
			//we look for defined axis manager which uses the labelconfiguration defined in the table config
			if(current.getHeaderLabelConfiguration() == tableLabelConfiguration) {
				AxisManagerConfiguration currentConf = null;
				//we look for an axis manager configuration mapped in this axis manager representation
				for(final AxisManagerConfiguration axisConf : localConfig.getAxisManagerConfigurations()) {
					if(axisConf.getAxisManager() == current) {
						currentConf = axisConf;
					}
				}
				if(currentConf == null) {
					currentConf = NattableaxisconfigurationFactory.eINSTANCE.createAxisManagerConfiguration();
					currentConf.setAxisManager(current);
					final IEditCommandRequest request2 = new SetRequest(domain, localConfig, NattableaxisconfigurationPackage.eINSTANCE.getLocalTableHeaderAxisConfiguration_AxisManagerConfigurations(), currentConf);
					final IElementEditService provider2 = ElementEditServiceUtils.getCommandProvider(localConfig);
					cmd.add(provider2.getEditCommand(request2));
				}
				axisManagerConfiguration.add(currentConf);
			}
		}

		//4. these one must reference the LabelConfiguration
		for(final AxisManagerConfiguration current : axisManagerConfiguration) {
			final IEditCommandRequest request2 = new SetRequest(domain, current, NattableaxisconfigurationPackage.eINSTANCE.getAxisManagerConfiguration_LocalHeaderLabelConfiguration(), localTableLabelConfiguration);
			final IElementEditService provider2 = ElementEditServiceUtils.getCommandProvider(current);
			cmd.add(provider2.getEditCommand(request2));
		}
		return cmd;
	}


	/**
	 * 
	 * @param table
	 * @param localHeaderAxisConfiguration
	 * @param column
	 * @return
	 *         the command to register the local table configuration into the table
	 */
	private static final ICommand getRegisterLocalHeaderAxisConfigurationCommand(final Table table, final LocalTableHeaderAxisConfiguration localHeaderAxisConfiguration, final boolean workOnColumn) {
		final TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		final EStructuralFeature feature;
		if(registerOnColumn(table, workOnColumn)) {
			feature = NattablePackage.eINSTANCE.getTable_LocalColumnHeaderAxisConfiguration();
		} else {
			feature = NattablePackage.eINSTANCE.getTable_LocalRowHeaderAxisConfiguration();
		}
		final SetRequest request = new SetRequest(domain, table, feature, localHeaderAxisConfiguration);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(table);
		return provider.getEditCommand(request);
	}


	/**
	 * 
	 * @param table
	 *        the table
	 * @param configurationToAdd
	 *        the configuration to add
	 * @param onColumn
	 *        boolean indicating if the user is editing dislpayed column or displayed row
	 * @return
	 *         the command to add the axis configuration to the table
	 */
	private static final ICommand getAddIAxisConfigurationToLocalTableHeaderAxisConfiguration(final Table table, final IAxisConfiguration configurationToAdd, final boolean onColumn) {
		final CompositeCommand compositeCommand = new CompositeCommand("Add IAxis Configuration to table header"); //$NON-NLS-1$
		final TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);

		//1.we look for an existing local headerAxisConfigruation on the table
		AbstractHeaderAxisConfiguration headerAxisConfiguration = null;
		if(registerOnColumn(table, onColumn)) {
			headerAxisConfiguration = HeaderAxisConfigurationManagementUtils.getColumnAbstractHeaderAxisInTable(table);
		} else {
			headerAxisConfiguration = HeaderAxisConfigurationManagementUtils.getRowAbstractHeaderAxisInTable(table);
		}

		//2. if the local header axis doen't exist we create and register it
		if(headerAxisConfiguration == null) {
			final TableHeaderAxisConfiguration tableHeaderAxisConfiguration;
			final EStructuralFeature feature;
			if(registerOnColumn(table, onColumn)) {
				tableHeaderAxisConfiguration = (TableHeaderAxisConfiguration)HeaderAxisConfigurationManagementUtils.getColumnAbstractHeaderAxisInTableConfiguration(table);
				feature = NattablePackage.eINSTANCE.getTable_LocalColumnHeaderAxisConfiguration();
			} else {
				tableHeaderAxisConfiguration = (TableHeaderAxisConfiguration)HeaderAxisConfigurationManagementUtils.getRowAbstractHeaderAxisInTableConfiguration(table);
				feature = NattablePackage.eINSTANCE.getTable_LocalRowHeaderAxisConfiguration();
			}
			headerAxisConfiguration = HeaderAxisConfigurationManagementUtils.transformToLocalHeaderConfiguration(tableHeaderAxisConfiguration);
			final SetRequest request = new SetRequest(domain, table, feature, headerAxisConfiguration);
			final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(table);
			compositeCommand.add(provider.getEditCommand(request));
		}

		//3. we register the axis configuration to the local table header axis
		final SetRequest request = new SetRequest(domain, headerAxisConfiguration, NattableaxisconfigurationPackage.eINSTANCE.getAbstractHeaderAxisConfiguration_OwnedAxisConfigurations(), configurationToAdd);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(headerAxisConfiguration);
		compositeCommand.add(provider.getEditCommand(request));
		return compositeCommand;
	}

	/**
	 * 
	 * @param table
	 *        the table
	 * @param workOnColumn
	 *        a boolean indication if the user is work on displayed columns or on displayed row
	 * @return
	 *         <code>true</code> if the operations must be done on column and false if the operations must be done on rows
	 */
	private static final boolean registerOnColumn(final Table table, final boolean workOnColumn) {
		return workOnColumn || (!workOnColumn && table.isInvertAxis());
	}

	/**
	 * 
	 * @param table
	 *        the table
	 * @param editedConfiguration
	 *        the edited configuration
	 * @param managedFeature
	 *        the managed feature
	 * @param value
	 *        the new value for this feature
	 * @param onColumn
	 *        <code>true</code> if we are working on column
	 * @return
	 *         the command doing the set value
	 */
	public static ICommand getSetIAxisConfigurationValueCommand(final Table table, final IAxisConfiguration editedConfiguration, final EStructuralFeature managedFeature, final Object value, boolean onColumn) {
		final CompositeCommand compositeCommand = new CompositeCommand("Set IAxis Value Command");
		final TransactionalEditingDomain domain = TableEditingDomainUtils.getTableEditingDomain(table);
		final EObject parent = editedConfiguration.eContainer();
		IAxisConfiguration realEditedObject = editedConfiguration;
		//the current configuration doesn't exist in the instance of the table, we must add it
		if(parent == null) {
			compositeCommand.add(getAddIAxisConfigurationToLocalTableHeaderAxisConfiguration(table, editedConfiguration, onColumn));
		} else if(parent instanceof TableHeaderAxisConfiguration) {
			realEditedObject = EcoreUtil.copy(editedConfiguration);
			compositeCommand.add(getAddIAxisConfigurationToLocalTableHeaderAxisConfiguration(table, realEditedObject, onColumn));
		}
		final SetRequest request = new SetRequest(domain, realEditedObject, managedFeature, value);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(realEditedObject);
		compositeCommand.add(provider.getEditCommand(request));
		return compositeCommand;
	}



}
