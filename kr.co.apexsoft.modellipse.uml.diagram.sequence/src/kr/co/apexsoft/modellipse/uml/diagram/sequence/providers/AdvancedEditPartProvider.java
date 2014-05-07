package kr.co.apexsoft.modellipse.uml.diagram.sequence.providers;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts.AdvancedCombinedFragmentCombinedFragmentCompartmentEditPart;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts.AdvancedInteractionInteractionCompartmentEditPart;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts.AdvancedInteractionOperandEditPart;
import kr.co.apexsoft.modellipse.uml.diagram.sequence.edit.parts.AdvancedLifelineEditPart;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionInteractionCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.providers.CustomEditPartProvider;

public class AdvancedEditPartProvider extends CustomEditPartProvider {

	@Override
	protected IGraphicalEditPart createCustomEditPart(View view) {
		switch (UMLVisualIDRegistry.getVisualID(view)) {
		case InteractionInteractionCompartmentEditPart.VISUAL_ID:
			return new AdvancedInteractionInteractionCompartmentEditPart(view);
		case InteractionOperandEditPart.VISUAL_ID:
			return new AdvancedInteractionOperandEditPart(view);
		case CombinedFragmentCombinedFragmentCompartmentEditPart.VISUAL_ID:
			return new AdvancedCombinedFragmentCombinedFragmentCompartmentEditPart(view);
		case LifelineEditPart.VISUAL_ID:
			return new AdvancedLifelineEditPart(view);
		}
		return super.createCustomEditPart(view);
	}
}
