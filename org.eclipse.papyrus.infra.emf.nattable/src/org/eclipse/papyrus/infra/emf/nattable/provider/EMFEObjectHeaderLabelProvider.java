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
package org.eclipse.papyrus.infra.emf.nattable.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.EObjectAxis;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattableaxis.IAxis;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ILabelProviderConfiguration;
import org.eclipse.papyrus.infra.nattable.model.nattable.nattablelabelprovider.ObjectLabelProviderConfiguration;
import org.eclipse.papyrus.infra.nattable.provider.AbstractNattableCellLabelProvider;
import org.eclipse.papyrus.infra.nattable.utils.ILabelProviderContextElementWrapper;
import org.eclipse.papyrus.infra.nattable.utils.LabelProviderCellContextElementWrapper;
import org.eclipse.papyrus.infra.services.labelprovider.service.LabelProviderService;
import org.eclipse.swt.graphics.Image;

/**
 * The label provider used for header when they represents an {@link EObject} and NOT an {@link EStructuralFeature}
 * 
 * @author Vincent Lorenzo
 * 
 */
public class EMFEObjectHeaderLabelProvider extends AbstractNattableCellLabelProvider {

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.provider.AbstractNattableCellLabelProvider#accept(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 */
	@Override
	public boolean accept(Object element) {
		if(element instanceof ILabelProviderContextElementWrapper) {
			Object object = ((ILabelProviderContextElementWrapper)element).getObject();
			if(object instanceof IAxis) {
				object = ((IAxis)object).getElement();
			}
			return object instanceof EObject && !(object instanceof EStructuralFeature);
		}
		return false;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.provider.AbstractNattableCellLabelProvider#getText(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 */
	@Override
	public String getText(Object element) {
		ILabelProviderContextElementWrapper context = (ILabelProviderContextElementWrapper)element;
		EObject object = (EObject)((ILabelProviderContextElementWrapper)element).getObject();
		if(object instanceof EObjectAxis) {
			object = ((EObjectAxis)object).getElement();
		}
		final IConfigRegistry configRegistry = context.getConfigRegistry();
		final LabelProviderService serv = getLabelProviderService(configRegistry);
		ILabelProviderConfiguration conf = null;
		if(element instanceof LabelProviderCellContextElementWrapper) {
			conf = getLabelConfiguration((LabelProviderCellContextElementWrapper)element);
		}
		if(conf instanceof ObjectLabelProviderConfiguration && !((ObjectLabelProviderConfiguration)conf).isDisplayLabel()) {
			return "";
		}
		return serv.getLabelProvider(object).getText(object);
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.nattable.provider.AbstractNattableCellLabelProvider#getImage(java.lang.Object)
	 * 
	 * @param element
	 * @return
	 */
	@Override
	public Image getImage(Object element) {
		EObject object = (EObject)((ILabelProviderContextElementWrapper)element).getObject();
		if(object instanceof EObjectAxis) {
			object = ((EObjectAxis)object).getElement();
		}
		final IConfigRegistry configRegistry = ((ILabelProviderContextElementWrapper)element).getConfigRegistry();
		final LabelProviderService serv = getLabelProviderService(configRegistry);
		ILabelProviderConfiguration conf = null;
		if(element instanceof LabelProviderCellContextElementWrapper) {
			conf = getLabelConfiguration((LabelProviderCellContextElementWrapper)element);
		}
		if(conf instanceof ObjectLabelProviderConfiguration && !((ObjectLabelProviderConfiguration)conf).isDisplayIcon()) {
			return null;
		}
		return serv.getLabelProvider(object).getImage(object);
	}
}
