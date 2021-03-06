/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.alf.validation.typing;


public class VoidFacade extends TypeFacade {

	private TypeFacade typeFacade ;
	
	public VoidFacade(TypeFacade typeFacade) {
		this.typeFacade = typeFacade ;
		this.typeObject = typeFacade.typeObject ;
	}
	
	public TypeFacade getTypeFacade() {
		return typeFacade ;
	}

	@Override
	public String getLabel() {
		return typeFacade != null ? typeFacade.getLabel() : "" ;
	}
}
