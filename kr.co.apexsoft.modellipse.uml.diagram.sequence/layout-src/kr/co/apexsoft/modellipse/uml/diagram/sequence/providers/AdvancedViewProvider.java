package kr.co.apexsoft.modellipse.uml.diagram.sequence.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.providers.CustomViewProvider;

public class AdvancedViewProvider extends CustomViewProvider {

	@Override
	public Node createInteraction_2001(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = super.createInteraction_2001(domainElement, containerView, index,
				persisted, preferencesHint);
		node.setLayoutConstraint(null);
		return node;
	}

	@Override
	public Node createCombinedFragment_3004(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		return super.createCombinedFragment_3004(domainElement, containerView, index,
				persisted, preferencesHint);
	}

	@Override
	public Node createInteractionOperand_3005(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = super.createInteractionOperand_3005(domainElement, containerView, index,
				persisted, preferencesHint);
		node.setLayoutConstraint(null);
		return node;
	}

	@Override
	protected Node createCompartment(View owner, String hint,
			boolean canCollapse, boolean hasTitle, boolean canSort,
			boolean canFilter) {
		Node node = super.createCompartment(owner, hint, canCollapse, hasTitle, canSort,
				canFilter);
		node.setLayoutConstraint(null);
		return node;
	}

}
