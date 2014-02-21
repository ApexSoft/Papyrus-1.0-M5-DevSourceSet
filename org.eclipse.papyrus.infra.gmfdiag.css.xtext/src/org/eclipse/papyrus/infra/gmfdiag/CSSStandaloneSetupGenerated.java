/*****************************************************************************
 * Copyright (c) 2012-2013 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ISetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
@SuppressWarnings("all")
public class CSSStandaloneSetupGenerated implements ISetup {

	public Injector createInjectorAndDoEMFRegistration() {
		// register default ePackages
		if(!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("ecore")) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl());
		}
		if(!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("xmi")) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl());
		}
		if(!EPackage.Registry.INSTANCE.containsKey(org.eclipse.xtext.XtextPackage.eNS_URI)) {
			EPackage.Registry.INSTANCE.put(org.eclipse.xtext.XtextPackage.eNS_URI, org.eclipse.xtext.XtextPackage.eINSTANCE);
		}

		Injector injector = createInjector();
		register(injector);
		return injector;
	}

	public Injector createInjector() {
		return Guice.createInjector(new org.eclipse.papyrus.infra.gmfdiag.CSSRuntimeModule());
	}

	public void register(Injector injector) {
		if(!EPackage.Registry.INSTANCE.containsKey("http://www.eclipse.org/papyrus/infra/gmfdiag/CSS")) {
			EPackage.Registry.INSTANCE.put("http://www.eclipse.org/papyrus/infra/gmfdiag/CSS", org.eclipse.papyrus.infra.gmfdiag.css.CssPackage.eINSTANCE);
		}

		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("css", resourceFactory);
		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("css", serviceProvider);




	}
}
