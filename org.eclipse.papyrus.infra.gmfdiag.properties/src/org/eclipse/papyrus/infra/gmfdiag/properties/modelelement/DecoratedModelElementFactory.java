/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.properties.modelelement;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.helper.NotationHelper;
import org.eclipse.papyrus.infra.gmfdiag.common.providers.ShapeDecorator;
import org.eclipse.papyrus.infra.gmfdiag.properties.Activator;
import org.eclipse.papyrus.views.properties.contexts.DataContextElement;
import org.eclipse.papyrus.views.properties.modelelement.ModelElement;
import org.eclipse.papyrus.views.properties.modelelement.ModelElementFactory;

/**
 * A factory for handling the GMF decorated elements
 */
public class DecoratedModelElementFactory implements ModelElementFactory {

	public ModelElement createFromSource(Object sourceElement, DataContextElement context) {
		View view = NotationHelper.findView(sourceElement);

		if(view != null) {
			EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(view);
			if(ShapeDecorator.isDecorable(view)) {
				return new DecoratedModelElement(view, domain);
			}
			
		}

		Activator.log.warn("The selected element cannot be resolved to a Decorated element");
		return null;
	}

}
