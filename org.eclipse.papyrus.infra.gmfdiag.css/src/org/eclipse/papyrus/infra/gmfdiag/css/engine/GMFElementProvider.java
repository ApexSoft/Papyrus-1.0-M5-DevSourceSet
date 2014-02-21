/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.css.engine;

import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.css.dom.GMFElementAdapter;
import org.w3c.dom.Element;

/**
 * Provides an Element for a GMF Notation object.
 * 
 * @author Camille Letavernier
 */
@SuppressWarnings("restriction")
public class GMFElementProvider implements IElementProvider {

	/**
	 * {@inheritDoc}
	 * 
	 * @param element
	 *        A GMF View
	 * @param engine
	 *        An ExtendedCSSEngine
	 */
	public Element getElement(Object element, CSSEngine engine) {

		if(!(element instanceof View)) {
			throw new IllegalArgumentException("Unknown element : " + element);
		}

		if(!(engine instanceof ExtendedCSSEngine)) {
			throw new IllegalArgumentException("Invalid CSS Engine : " + engine);
		}

		return new GMFElementAdapter((View)element, (ExtendedCSSEngine)engine);
	}

}
