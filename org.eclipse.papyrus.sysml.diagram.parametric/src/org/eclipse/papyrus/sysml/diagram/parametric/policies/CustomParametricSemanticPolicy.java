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
package org.eclipse.papyrus.sysml.diagram.parametric.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;
import org.eclipse.gmf.runtime.emf.type.core.SpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.constraints.ConstraintBlock;
import org.eclipse.papyrus.sysml.diagram.common.edit.part.BlockPropertyCompositeEditPart;
import org.eclipse.papyrus.sysml.diagram.parametric.commands.CustomBindingConnectorCreateCommand;
import org.eclipse.papyrus.sysml.diagram.parametric.commands.CustomParametricContextLinkReorientCommand;
import org.eclipse.papyrus.sysml.diagram.parametric.commands.ReorientReferenceRelationshipRequestWithGraphical;
import org.eclipse.papyrus.sysml.diagram.parametric.edit.part.CustomBlockCompositeEditPartTN;
import org.eclipse.papyrus.sysml.diagram.parametric.edit.part.CustomConstraintBlockPropertyCompositeEditPart;
import org.eclipse.papyrus.sysml.diagram.parametric.edit.policy.DiagramSemanticEditPolicy;
import org.eclipse.papyrus.sysml.service.types.matcher.PartPropertyMatcher;
import org.eclipse.papyrus.sysml.service.types.matcher.ReferencePropertyMatcher;
import org.eclipse.papyrus.sysml.service.types.matcher.ValuePropertyMatcher;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ContextLinkEditPart;
import org.eclipse.papyrus.uml.diagram.common.edit.part.ConstraintParameterAffixedNodeEditPart;
import org.eclipse.papyrus.uml.diagram.common.edit.part.PortAffixedNodeEditPart;
import org.eclipse.papyrus.uml.tools.utils.UMLUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;

public class CustomParametricSemanticPolicy extends DiagramSemanticEditPolicy {

	@Override
	protected Command getSemanticCommand(IEditCommandRequest request) {
		if (request instanceof CreateElementRequest) {
			if (((CreateElementRequest) request).getElementType().getEClass() == UMLPackage.eINSTANCE.getProperty()) {
				// Could create a ConstraintParameter on the diagram frame only if host element is a ConstraintBlock
				Element semanticOwner = UMLUtil.resolveUMLElement(this.getHost());
				if (semanticOwner instanceof Class && org.eclipse.uml2.uml.util.UMLUtil.getStereotypeApplication(semanticOwner, ConstraintBlock.class) == null) {
					return UnexecutableCommand.INSTANCE;
				}
				// Could not create Part/Reference/Value
				if (request instanceof CreateElementRequest && ((CreateElementRequest)request).getElementType() instanceof SpecializationType) {
					IElementMatcher matcher = ((SpecializationType)((CreateElementRequest)request).getElementType()).getMatcher();
					if (matcher instanceof PartPropertyMatcher) {
						return UnexecutableCommand.INSTANCE;
					}
					if (matcher instanceof ReferencePropertyMatcher) {
						return UnexecutableCommand.INSTANCE;
					}
					if (matcher instanceof ValuePropertyMatcher) {
						return UnexecutableCommand.INSTANCE;
					}
				}
			}
		}
		return super.getSemanticCommand(request);
	}

	@Override
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (req.getElementType().getEClass() == UMLPackage.eINSTANCE.getConnector()) {
			if (getHost() instanceof CustomBlockCompositeEditPartTN) {
				return UnexecutableCommand.INSTANCE;
			}
			if (getHost() instanceof CustomConstraintBlockPropertyCompositeEditPart) {
				return UnexecutableCommand.INSTANCE;			
			}
			if (getHost() instanceof ConstraintParameterAffixedNodeEditPart) {
				return getGEFWrapper(new CustomBindingConnectorCreateCommand(req));			
			}
			if (getHost() instanceof PortAffixedNodeEditPart) {
				return getGEFWrapper(new CustomBindingConnectorCreateCommand(req));				
			}
			if (getHost() instanceof BlockPropertyCompositeEditPart) {
				return getGEFWrapper(new CustomBindingConnectorCreateCommand(req));				
			}
		}
		return super.getCreateRelationshipCommand(req);
	}

	/**
	 * Method getReorientRefRelationshipTargetCommand. Removes the reference the
	 * ConnectionEditPart current has an add the new TargetEditPart
	 * 
	 * @param request
	 * @return Command
	 */
	@Override
	
	protected Command getReorientRelationshipSourceCommand(
			ReconnectRequest request) {
		
		EditPart target = request.getTarget();
		if (target instanceof CustomBlockCompositeEditPartTN) {
			return UnexecutableCommand.INSTANCE;
		}
		if (target instanceof CustomConstraintBlockPropertyCompositeEditPart) {
			return UnexecutableCommand.INSTANCE;			
		}

		org.eclipse.gef.ConnectionEditPart connectionEP = (request).getConnectionEditPart();
		
		if (connectionEP instanceof ConnectionEditPart) {
			if (!((ConnectionEditPart) connectionEP).isSemanticConnection()) {
				return null;
			}
		}

		EditPart sourceEditPart = connectionEP.getSource();
		EditPart targetEditPart = connectionEP.getTarget();
		EObject referenceOwner = ViewUtil
			.resolveSemanticElement((View) targetEditPart.getModel());
		EObject oldTarget = ViewUtil
			.resolveSemanticElement((View) sourceEditPart.getModel());
		EObject newTarget = ViewUtil
			.resolveSemanticElement((View) request.getTarget().getModel());

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
            .getEditingDomain();

        ReorientReferenceRelationshipRequest semRequest = new ReorientReferenceRelationshipRequestWithGraphical(
            editingDomain, referenceOwner, newTarget, oldTarget,
            ReorientReferenceRelationshipRequest.REORIENT_SOURCE,
            request.getTarget(), targetEditPart);
        
        semRequest.addParameters(request.getExtendedData());
		return getSemanticCommand(semRequest);
	}



	/**
	 * Method getReorientRefRelationshipTargetCommand. Removes the reference the
	 * ConnectionEditPart current has an add the new TargetEditPart
	 * 
	 * @param request
	 * @return Command
	 */
	@Override
	protected Command getReorientRelationshipTargetCommand(
			ReconnectRequest request) {
		
		EditPart target = request.getTarget();
		if (target instanceof CustomBlockCompositeEditPartTN) {
			return UnexecutableCommand.INSTANCE;
		}
		if (target instanceof CustomConstraintBlockPropertyCompositeEditPart) {
			return UnexecutableCommand.INSTANCE;			
		}

		org.eclipse.gef.ConnectionEditPart connectionEP = (request).getConnectionEditPart();
		
		if (connectionEP instanceof ConnectionEditPart) {
			if (!((ConnectionEditPart) connectionEP).isSemanticConnection()) {
				return null;
			}
		}

		EditPart sourceEditPart = connectionEP.getSource();
		EditPart targetEditPart = connectionEP.getTarget();
		EObject referenceOwner = ViewUtil
			.resolveSemanticElement((View) sourceEditPart.getModel());
		EObject oldTarget = ViewUtil
			.resolveSemanticElement((View) targetEditPart.getModel());
		EObject newTarget = ViewUtil
			.resolveSemanticElement((View) request.getTarget().getModel());

        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
            .getEditingDomain();

        ReorientReferenceRelationshipRequest semRequest = new ReorientReferenceRelationshipRequestWithGraphical(
                editingDomain, referenceOwner, newTarget, oldTarget,
                ReorientReferenceRelationshipRequest.REORIENT_TARGET,
                sourceEditPart, request.getTarget());
        
        semRequest.addParameters(request.getExtendedData());

		return getSemanticCommand(semRequest);
	}

	@Override
	protected Command getReorientRefRelationshipSourceCommand(
			ReconnectRequest request) {

		if(request.getConnectionEditPart() instanceof ContextLinkEditPart) {
			org.eclipse.gef.ConnectionEditPart connectionEP = request.getConnectionEditPart();
			
			if (connectionEP instanceof ConnectionEditPart) {
				if (!((ConnectionEditPart) connectionEP).isSemanticConnection()) {
					return null;
				}
			}

			EditPart sourceEditPart = connectionEP.getSource();
			EditPart targetEditPart = connectionEP.getTarget();
			EObject referenceOwner = ViewUtil
				.resolveSemanticElement((View) targetEditPart.getModel());
			EObject oldTarget = ViewUtil
				.resolveSemanticElement((View) sourceEditPart.getModel());
			EObject newTarget = ViewUtil
				.resolveSemanticElement((View) request.getTarget().getModel());

	        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
	            .getEditingDomain();

	        ReorientReferenceRelationshipRequestWithGraphical semRequest = new ReorientReferenceRelationshipRequestWithGraphical(
	                editingDomain, referenceOwner, newTarget, oldTarget,
	                ReorientReferenceRelationshipRequest.REORIENT_SOURCE,
	                sourceEditPart, targetEditPart);
			return  getGEFWrapper(new CustomParametricContextLinkReorientCommand(semRequest));
		}
		return super.getReorientRefRelationshipTargetCommand(request);
	}

	@Override
	protected Command getReorientRefRelationshipTargetCommand(
			ReconnectRequest request) {
		if(request.getConnectionEditPart() instanceof ContextLinkEditPart) {
			org.eclipse.gef.ConnectionEditPart connectionEP = request.getConnectionEditPart();
			
			if (connectionEP instanceof ConnectionEditPart) {
				if (!((ConnectionEditPart) connectionEP).isSemanticConnection()) {
					return null;
				}
			}

			EditPart sourceEditPart = connectionEP.getSource();
			EditPart targetEditPart = connectionEP.getTarget();
			EObject referenceOwner = ViewUtil
				.resolveSemanticElement((View) sourceEditPart.getModel());
			EObject oldTarget = ViewUtil
				.resolveSemanticElement((View) targetEditPart.getModel());
			EObject newTarget = ViewUtil
				.resolveSemanticElement((View) request.getTarget().getModel());

	        TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
	            .getEditingDomain();

	        ReorientReferenceRelationshipRequestWithGraphical semRequest = new ReorientReferenceRelationshipRequestWithGraphical(
	                editingDomain, referenceOwner, newTarget, oldTarget,
	                ReorientReferenceRelationshipRequest.REORIENT_TARGET,
	                sourceEditPart, targetEditPart);
			return  getGEFWrapper(new CustomParametricContextLinkReorientCommand(semRequest));
		}
		return super.getReorientRefRelationshipTargetCommand(request);
	}

	
}
