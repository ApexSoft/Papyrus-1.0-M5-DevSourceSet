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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.sysml.diagram.internalblock.Activator;

public class InternalBlockDiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	protected IPreferenceStore getPreferenceStore() {
		return Activator.getInstance().getPreferenceStore();
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();

		org.eclipse.papyrus.sysml.diagram.internalblock.preferences.ConstraintPreferencePage.initDefaults(store);
		org.eclipse.papyrus.sysml.diagram.internalblock.preferences.CommentPreferencePage.initDefaults(store);
		org.eclipse.papyrus.sysml.diagram.internalblock.preferences.CommentAnnotatedElementPreferencePage.initDefaults(store);
		org.eclipse.papyrus.sysml.diagram.internalblock.preferences.ConstraintConstrainedElementPreferencePage.initDefaults(store);
		BlockCompositePreferencePage.initDefaults(store);
		DependencyPreferencePage.initDefaults(store);
	}
}
