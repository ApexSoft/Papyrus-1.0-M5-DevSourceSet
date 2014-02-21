/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		R�gis CHEVREL: chevrel.regis <at> gmail.com
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.parametric.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.sysml.diagram.parametric.provider.ElementTypes;

public class CommentPreferencePage extends ParametricDiagramNodePreferencePage {

	/** Constant key to access preferences */
	protected static String prefKey = ElementTypes.DIAGRAM_ID + "_Comment"; //$NON-NLS-1$

	
	/** Default constructor */	
	public CommentPreferencePage() {
		super();
		setPreferenceKey(ElementTypes.DIAGRAM_ID + "_Comment"); //$NON-NLS-1$
	}
	
	/**
	 * Initialize defaults using a specified {@link IPreferenceStore}
	 * 
	 * @param store
	 *        the preference store.
	 */
	public static void initDefaults(IPreferenceStore store) {
		// Start of user code custom default initializations
 		// End of user code
		
	}

	
}
