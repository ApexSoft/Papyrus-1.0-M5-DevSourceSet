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
package org.eclipse.papyrus.infra.gmfdiag.css.properties.modelelement;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.infra.gmfdiag.css.notation.CSSDiagram;
import org.eclipse.papyrus.infra.gmfdiag.css.notation.CSSStyles;
import org.eclipse.papyrus.infra.gmfdiag.css.properties.creation.StyleSheetFactory;
import org.eclipse.papyrus.infra.gmfdiag.css.properties.databinding.DiagramStyleSheetObservableList;
import org.eclipse.papyrus.infra.gmfdiag.css.properties.provider.CSSStyleSheetContentProvider;
import org.eclipse.papyrus.infra.gmfdiag.css.properties.provider.CSSStyleSheetLabelProvider;
import org.eclipse.papyrus.infra.gmfdiag.css.provider.CSSClassContentProvider;
import org.eclipse.papyrus.infra.gmfdiag.properties.modelelement.CustomStyleModelElement;
import org.eclipse.papyrus.infra.widgets.creation.ReferenceValueFactory;
import org.eclipse.papyrus.infra.widgets.creation.StringEditionFactory;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.papyrus.views.properties.contexts.DataContextElement;


public class CSSModelElement extends CustomStyleModelElement {

	public CSSModelElement(View source, DataContextElement context) {
		super(source, context);
	}

	public CSSModelElement(View source, EditingDomain domain, DataContextElement element) {
		super(source, domain, element);
	}

	@Override
	public ReferenceValueFactory getValueFactory(String propertyPath) {
		if(CSSStyles.CSS_DIAGRAM_STYLESHEETS_KEY.equals(propertyPath)) {
			return new StyleSheetFactory((View)this.source);
		}
		if(CSSStyles.CSS_GMF_CLASS_KEY.equals(propertyPath)) {
			StringEditionFactory factory = new StringEditionFactory();
			factory.setContentProvider(getContentProvider(propertyPath));
			return factory;
		}

		return super.getValueFactory(propertyPath);
	}

	@Override
	public IObservable doGetObservable(String propertyPath) {
		if(CSSStyles.CSS_DIAGRAM_STYLESHEETS_KEY.equals(propertyPath)) {
			return new DiagramStyleSheetObservableList((View)source, domain, propertyPath);
		}
		return super.doGetObservable(propertyPath);
	}

	@Override
	public ILabelProvider getLabelProvider(String propertyPath) {
		if(CSSStyles.CSS_DIAGRAM_STYLESHEETS_KEY.equals(propertyPath)) {
			return new CSSStyleSheetLabelProvider();
		}
		return super.getLabelProvider(propertyPath);
	}

	@Override
	public IStaticContentProvider getContentProvider(String propertyPath) {
		if(propertyPath.equals(CSSStyles.CSS_DIAGRAM_STYLESHEETS_KEY)) {
			return new CSSStyleSheetContentProvider(source);
		}

		if(propertyPath.equals(CSSStyles.CSS_GMF_CLASS_KEY)) {
			Diagram diagram = ((View)source).getDiagram();
			if(diagram instanceof CSSDiagram) {

				EObject semanticElement = ((View)source).getElement();

				if(semanticElement != null) {
					//TODO: For Diagrams, we should use the right DiagramKind (See GMFElementAdapter)
					//Until then, we list all available classes (*)
					String elementName = source instanceof Diagram ? "*" : semanticElement.eClass().getName();
					return new CSSClassContentProvider(elementName, ((CSSDiagram)diagram).getEngine());
				}
			}

			return null;
		}

		return null;
	}

}
