/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.internalblock.preferences;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

public class BlockPropertyCompositePreferencePage extends AbstractPreferencePage {

	/** Constructor */
	public BlockPropertyCompositePreferencePage() {
		super();
	}

	/** Default preferences initializer */
	public static void initDefaults(IPreferenceStore store) {
		CustomBlockPropertyCompositePreferencePage.initDefaults(store);
		/*CustomNestedBlockPropertyCompositePreferencePage.initDefaults(store);*/
	}

	@Override
	protected void addFields(Composite parent) {

	}

	@Override
	protected void initHelp() {

	}
}
