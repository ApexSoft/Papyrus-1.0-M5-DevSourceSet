/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.nattable.controlmode.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.papyrus.infra.nattable.controlmode.helpers.TableControlModeHelper;
import org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest;
import org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant;
import org.eclipse.papyrus.infra.services.controlmode.participants.IUncontrolCommandParticipant;


public class PapyrusNattableControlModeParticipant implements IControlCommandParticipant, IUncontrolCommandParticipant {

	/** the ID for this control mode participant */
	public static final String PARTICIPANT_ID = "org.eclipse.papyrus.infra.nattable.controlmode.NatTableControlModeParticipant";

	@Override
	public String getID() {
		return PapyrusNattableControlModeParticipant.PARTICIPANT_ID;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlModeParticipant#getPriority()
	 * 
	 * @return
	 */
	@Override
	public int getPriority() {
		return 50;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant#provideControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public boolean provideControlCommand(ControlModeRequest request) {
		final EObject objectToControl = request.getTargetObject();
		if(objectToControl == null) {
			return false;
		}
		final Iterable<EObject> iter = TableControlModeHelper.createDescendantTablesIterable(objectToControl);
		return iter.iterator().hasNext();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IUncontrolCommandParticipant#provideUnControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public boolean provideUnControlCommand(ControlModeRequest request) {
		return provideControlCommand(request);
	}


	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IUncontrolCommandParticipant#getPreUncontrolCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ICommand getPreUncontrolCommand(ControlModeRequest request) {
		return new PapyrusNattableControlCommand(request);
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant#getPreControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ICommand getPreControlCommand(ControlModeRequest request) {
		//don't create the notation resource since it is already handled 
		return null;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IUncontrolCommandParticipant#getPostUncontrolCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ICommand getPostUncontrolCommand(ControlModeRequest request) {
		//don't remove the notation resource
		return null;
	}


	/**
	 * 
	 * @see org.eclipse.papyrus.infra.services.controlmode.participants.IControlCommandParticipant#getPostControlCommand(org.eclipse.papyrus.infra.services.controlmode.ControlModeRequest)
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public ICommand getPostControlCommand(ControlModeRequest request) {
		return new PapyrusNattableControlCommand(request);
	}



}
