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
package org.eclipse.papyrus.infra.nattable.provider;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.layer.LabelStack;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.papyrus.infra.nattable.manager.table.INattableModelManager;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ILabelProviderConfiguration;
import org.eclipse.papyrus.infra.nattable.utils.ILabelProviderContextElementWrapper;
import org.eclipse.papyrus.infra.nattable.utils.LabelConfigurationManagementUtils;
import org.eclipse.papyrus.infra.nattable.utils.LabelProviderCellContextElementWrapper;
import org.eclipse.papyrus.infra.nattable.utils.NattableConfigAttributes;
import org.eclipse.papyrus.infra.services.labelprovider.service.IFilteredLabelProvider;
import org.eclipse.papyrus.infra.services.labelprovider.service.LabelProviderService;
import org.eclipse.swt.graphics.Image;


public abstract class AbstractNattableCellLabelProvider implements IFilteredLabelProvider {

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.labelprovider.service.IFilteredLabelProvider#accept(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 * 
	 */
	public boolean accept(Object element) {
		return false;
	}

	/**
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 * 
	 */
	public Image getImage(Object element) {
		return null;
	}

	/**
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 * 
	 */
	public String getText(Object element) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 * 
	 * @param listener
	 *        always throws {@link UnsupportedOperationException}
	 */
	public void addListener(ILabelProviderListener listener) {
		//		throw new UnsupportedOperationException();
	}

	public void dispose() {
	}

	/**
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 * 
	 * @param element
	 * @param property
	 * @return
	 *         false
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/**
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 * 
	 * @param listener
	 *        always throws {@link UnsupportedOperationException}
	 */
	public void removeListener(ILabelProviderListener listener) {
		//		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cell
	 * @param registry
	 * @return
	 *         the row object for this cell
	 */
	protected Object getRowObject(final ILayerCell cell, final IConfigRegistry registry) {
		int rowIndex = cell.getRowIndex();
		INattableModelManager provider = getAxisContentProvider(registry);
		return provider.getRowElement(rowIndex);
	}

	/**
	 * 
	 * @param cell
	 * @param registry
	 * @return
	 *         the column object for this cell
	 */
	protected Object getColumnObject(final ILayerCell cell, final IConfigRegistry registry) {
		int columnIndex = cell.getColumnIndex();
		INattableModelManager provider = getAxisContentProvider(registry);
		return provider.getColumnElement(columnIndex);
	}

	/**
	 * 
	 * @param registry
	 * @return
	 *         the table axis element provider
	 */
	protected INattableModelManager getAxisContentProvider(final IConfigRegistry registry) {
		return registry.getConfigAttribute(NattableConfigAttributes.NATTABLE_MODEL_MANAGER_CONFIG_ATTRIBUTE, DisplayMode.NORMAL, NattableConfigAttributes.NATTABLE_MODEL_MANAGER_ID);
	}

	/**
	 * 
	 * @param registry
	 * @return
	 *         the label provider service
	 */
	protected LabelProviderService getLabelProviderService(final IConfigRegistry registry) {
		return registry.getConfigAttribute(NattableConfigAttributes.LABEL_PROVIDER_SERVICE_CONFIG_ATTRIBUTE, DisplayMode.NORMAL, NattableConfigAttributes.LABEL_PROVIDER_SERVICE_ID);
	}

	/**
	 * 
	 * @param element
	 *        a label provider context element
	 * @return
	 *         the configuration to use for this element
	 */
	protected ILabelProviderConfiguration getLabelConfiguration(final LabelProviderCellContextElementWrapper element) {
		ILabelProviderConfiguration conf = null;
		final IConfigRegistry configRegistry = ((ILabelProviderContextElementWrapper)element).getConfigRegistry();
		INattableModelManager manager = configRegistry.getConfigAttribute(NattableConfigAttributes.NATTABLE_MODEL_MANAGER_CONFIG_ATTRIBUTE, DisplayMode.NORMAL, NattableConfigAttributes.NATTABLE_MODEL_MANAGER_ID);
		LabelStack labels = element.getConfigLabels();
		if(labels.hasLabel(GridRegion.COLUMN_HEADER)) {
			conf = LabelConfigurationManagementUtils.getUsedColumnObjectLabelConfiguration(manager.getTable());
		} else if(labels.hasLabel(GridRegion.ROW_HEADER)) {
			conf = LabelConfigurationManagementUtils.getUsedRowObjectLabelConfiguration(manager.getTable());
		}
		return conf;
	}
}
