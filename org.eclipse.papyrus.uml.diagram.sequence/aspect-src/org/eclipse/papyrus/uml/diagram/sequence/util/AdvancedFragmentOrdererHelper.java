package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.OccurrenceSpecification;

public class AdvancedFragmentOrdererHelper {
	
	public static final String INDEX_FRAGMENT = "Index Fragment"; //$NON-NLS-1$ 
	
	private static final String COMMAND_LABEL = "Ordering Fragments"; //$NON-NLS-1$

	public static ICommand createOrderingFragmentsCommand(final EditPart parentEditPart, final CreateViewAndElementRequest request) {
		return new AbstractTransactionalCommand(getEditingDomain(parentEditPart), COMMAND_LABEL, null) {
			
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
					IAdaptable info) throws ExecutionException {
				InteractionFragment container = (InteractionFragment) request
						.getExtendedData().get(SequenceRequestConstant.INTERACTIONFRAGMENT_CONTAINER);
				if (container == null) {
					CreateElementRequest createElementRequest = (CreateElementRequest) request.getViewAndElementDescriptor()
							.getCreateElementRequestAdapter().getAdapter(CreateElementRequest.class);
					if (createElementRequest.getContainer() instanceof InteractionFragment) {
						container = (InteractionFragment) createElementRequest.getContainer();
					}
				}
				
				List<InteractionFragment> insertionFragments = new ArrayList<InteractionFragment>();
				for (ViewDescriptor descriptor : request.getViewDescriptors()) {
					for (InteractionFragment ift : getInsertionFragments(container, descriptor)) {
						if (!insertionFragments.contains(ift)) {
							insertionFragments.add(ift);
						}
					}
				}
				
				orderingFragments(parentEditPart, container, request.getLocation(), insertionFragments);
				
				return CommandResult.newOKCommandResult();
			}
		};
	}
	
	public static ICommand createOrderingFragmentsCommand(final EditPart anyEditPart, final CreateConnectionViewAndElementRequest request) {
		return new AbstractTransactionalCommand(getEditingDomain(anyEditPart), COMMAND_LABEL, null) {
			
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
					IAdaptable info) throws ExecutionException {
				InteractionFragment srcContainer = (InteractionFragment) request
						.getExtendedData().get(SequenceRequestConstant.SOURCE_MODEL_CONTAINER);
				InteractionFragment tgtContainer = (InteractionFragment) request
						.getExtendedData().get(SequenceRequestConstant.SOURCE_MODEL_CONTAINER);
				
				ConnectionViewAndElementDescriptor descriptor = request.getConnectionViewAndElementDescriptor();
				Edge edge = (Edge) descriptor.getAdapter(Edge.class);
				if (edge != null) {
					Message message = (Message) edge.getElement();
					List<InteractionFragment> insertionFragments = new ArrayList<InteractionFragment>();
					insertionFragments.addAll(getInsertionFragments(srcContainer, message));
					// Source Interaction (or InteractionOperand)
					if (srcContainer != tgtContainer) {
						Point srcLocation = (Point) request.getExtendedData().get(SequenceRequestConstant.SOURCE_LOCATION_DATA);
						if (srcLocation == null) {
							srcLocation = request.getLocation();
						}
						orderingFragments(anyEditPart, srcContainer, srcLocation, insertionFragments);
						insertionFragments.clear();
					}
					// Target Interaction (or InteractionOperand)
					insertionFragments.addAll(getInsertionFragments(tgtContainer, message));
					EObject target = edge.getTarget().getElement();
					if (target instanceof ExecutionSpecification) {
						for (InteractionFragment ift : getInsertionFragments(tgtContainer, target)) {
							if (!insertionFragments.contains(ift)) {
								insertionFragments.add(ift);
							}
						}
					}
					orderingFragments(anyEditPart, tgtContainer, request.getLocation(), insertionFragments);
				}
				
				return CommandResult.newOKCommandResult();
			}
		};
	}
	
	private static TransactionalEditingDomain getEditingDomain(EditPart editPart) {
		if(editPart == null) {
			return null;
		}
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart)editPart.getAdapter(IGraphicalEditPart.class);
		if(graphicalEditPart != null) {
			return graphicalEditPart.getEditingDomain();
		}
		return null;
	}
	
	private static List<InteractionFragment> getInsertionFragments(InteractionFragment container, Object adapter) {
		List<InteractionFragment> result = new ArrayList<InteractionFragment>();
		if (container == null) {
			return result;
		}
		if (adapter instanceof Collection<?>) {
			Iterator<?> collection = ((Collection<?>) adapter).iterator();
			while (collection.hasNext()) {
				result.addAll(getInsertionFragments(container, collection.next()));
			}
			return result;
		}
		EObject element = null;
		if (adapter instanceof IAdaptable) {
			View view = (View) ((IAdaptable) adapter).getAdapter(View.class);
			element = view.getElement();
		} else if (adapter instanceof EObject) {
			element = (EObject) adapter;
		}
		if (element instanceof ExecutionSpecification) {
			ExecutionSpecification es = (ExecutionSpecification) element;
			OccurrenceSpecification start = es.getStart();
			OccurrenceSpecification finish = es.getFinish();
			if (container.equals(start.eContainer())) {
				result.add(start);
			}
			if (container.equals(finish.eContainer())) {
				result.add(es);
			}
			if (container.equals(es.eContainer())) {
				result.add(es.getFinish());
			}
		} else if (element instanceof Message) {
			Message msg = (Message) element;
			MessageEnd send = msg.getSendEvent();
			MessageEnd receive = msg.getReceiveEvent();
			if (send instanceof OccurrenceSpecification && container.equals(send.eContainer())) {
				result.add((OccurrenceSpecification) msg.getSendEvent());
			}
			if (receive instanceof OccurrenceSpecification && container.equals(receive.eContainer())) {
				result.add((OccurrenceSpecification) msg.getReceiveEvent());
			}
		} else if (element instanceof InteractionFragment &&
				container.equals(element.eContainer())) {
			result.add((InteractionFragment) element);
		}
		return result;
	}
	
	public static void orderingFragments(EditPart rootEditPart, InteractionFragment container, Point location,
			Collection<InteractionFragment> fragments) {
		if (rootEditPart instanceof IGraphicalEditPart) {
			AdvancedFragmentOrderer fragmentOrderer = new AdvancedFragmentOrderer((IGraphicalEditPart) rootEditPart);
			fragmentOrderer.setFragmentContainer(container);
			fragmentOrderer.setInsertionPosition(location.y);
			fragmentOrderer.addInsertionFragments(fragments);
			fragmentOrderer.ordering();
		}
	}
}
