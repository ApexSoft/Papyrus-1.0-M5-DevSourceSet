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

import java.io.IOException;
import java.net.URL;

import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.eclipse.e4.ui.css.core.engine.CSSElementContext;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.gmfdiag.css.resource.CSSNotationResource;
import org.eclipse.papyrus.infra.gmfdiag.css.stylesheets.ModelStyleSheets;
import org.eclipse.papyrus.infra.gmfdiag.css.stylesheets.StyleSheet;
import org.eclipse.papyrus.infra.gmfdiag.css.stylesheets.StyleSheetReference;
import org.w3c.dom.Element;

/**
 * An extended CSS Engine for an EMF Resource (A Model). This engine
 * is a child of the WorkspaceCSSEngine.
 * 
 * It should not be used directly.
 * 
 * @author Camille Letavernier
 * 
 * @see DiagramCSSEngine
 */
@SuppressWarnings("restriction")
public class ModelCSSEngine extends ExtendedCSSEngineImpl {

	private final Resource model;

	/**
	 * Creates a ModelCSSEngine for the requested resource.
	 * 
	 * @param model
	 */
	public ModelCSSEngine(Resource model) {
		super(getProjectCSSEngine(model));
		this.model = model;
	}

	private static ExtendedCSSEngine getProjectCSSEngine(Resource resource) {
		if(resource instanceof CSSNotationResource) {
			return ((CSSNotationResource)resource).getProjectEngine();
		}
		return new ProjectCSSEngine(resource);
	}

	@Override
	protected void reloadStyleSheets() {
		this.styleSheets.clear();
		for(EObject eObject : model.getContents()) {
			if(eObject instanceof ModelStyleSheets) {
				ModelStyleSheets styleSheets = (ModelStyleSheets)eObject;
				for(StyleSheet styleSheet : styleSheets.getStylesheets()) {
					this.styleSheets.add(styleSheet);
				}
			}
		}
	}

	@Override
	protected void parseStyleSheet(StyleSheetReference styleSheet) throws IOException {
		String path = styleSheet.getPath();
		if(path.startsWith("/")) {
			path = "platform:/plugin" + path;
		} else {
			URI uri = URI.createURI(styleSheet.getPath());
			uri = uri.resolve(model.getURI());
			path = uri.toString();
		}
		URL url = new URL(path);
		parseStyleSheet(url.openStream());
	}

	//Unsupported operations. The ModelCSSEngine should not be used directly.

	@Override
	public Element getElement(Object node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IElementProvider getElementProvider() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setElementProvider(IElementProvider elementProvider) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CSSElementContext getCSSElementContext(Object node) {
		throw new UnsupportedOperationException();
	}
}
