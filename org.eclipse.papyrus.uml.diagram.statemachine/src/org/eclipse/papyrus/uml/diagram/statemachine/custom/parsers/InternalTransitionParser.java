/*****************************************************************************
 * Copyright (c) 2011 Atos.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos - Initial API and implementation
 *   Arthur daussy (Atos) arthur.daussy@atos.net - Bug : 365404: [State Machine Diagram] Internal transition should be displayed as label on the state figure
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.statemachine.custom.parsers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Transition;

/**
 * Special parser for internal transition
 * 
 * @author arthur daussy
 * 
 */
public class InternalTransitionParser extends TransitionPropertiesParser {

	@Override
	protected String getValueString(IAdaptable element, int flags) {
		Object obj = element.getAdapter(EObject.class);
		if(obj instanceof Transition) {
			Transition tran = (Transition)obj;
			boolean noTrigger = tran.getTriggers() == null || tran.getTriggers().size() == 0;
			boolean noGuard = tran.getGuard() == null;
			boolean noEffect = tran.getEffect() == null;
			if(noEffect && noGuard && noTrigger) {
				return tran.getName();
			}
		}
		return super.getValueString(element, flags);
	}


}
