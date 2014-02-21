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
package org.eclipse.papyrus.infra.nattable.celleditor.config;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * A specific interface to declare editor on specific EStructuralFeature
 * 
 */
public interface IAxisEStructuralFeatureCellEditorConfiguration extends IAxisCellEditorConfiguration {

	/**
	 * 
	 * @return
	 *         the edited feature
	 */
	public Collection<EStructuralFeature> getEditedFeature();
}
