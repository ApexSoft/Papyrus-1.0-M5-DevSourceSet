/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
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
package org.eclipse.papyrus.uml.diagram.profile.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.papyrus.uml.diagram.profile.edit.policies.UMLBaseItemSemanticEditPolicy;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * @generated
 */
public class ProfileApplicationReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ProfileApplicationReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if(false == getElementToEdit() instanceof ProfileApplication) {
			return false;
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if(!(oldEnd instanceof Package && newEnd instanceof Package)) {
			return false;
		}
		Profile target = getLink().getAppliedProfile();
		if(!(getLink().eContainer() instanceof Package)) {
			return false;
		}
		Package container = (Package)getLink().eContainer();
		return UMLBaseItemSemanticEditPolicy.getLinkConstraints().canExistProfileApplication_1045(container, getLink(), getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if(!(oldEnd instanceof Profile && newEnd instanceof Profile)) {
			return false;
		}
		Package source = getLink().getApplyingPackage();
		if(!(getLink().eContainer() instanceof Package)) {
			return false;
		}
		Package container = (Package)getLink().eContainer();
		return UMLBaseItemSemanticEditPolicy.getLinkConstraints().canExistProfileApplication_1045(container, getLink(), source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if(!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setApplyingPackage(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setAppliedProfile(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected ProfileApplication getLink() {
		return (ProfileApplication)getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected Package getOldSource() {
		return (Package)oldEnd;
	}

	/**
	 * @generated
	 */
	protected Package getNewSource() {
		return (Package)newEnd;
	}

	/**
	 * @generated
	 */
	protected Profile getOldTarget() {
		return (Profile)oldEnd;
	}

	/**
	 * @generated
	 */
	protected Profile getNewTarget() {
		return (Profile)newEnd;
	}
}
