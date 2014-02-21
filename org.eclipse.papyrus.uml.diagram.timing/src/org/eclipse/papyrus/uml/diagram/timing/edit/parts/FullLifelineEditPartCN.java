/*
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.uml.diagram.timing.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeNodeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.PapyrusCreationEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;
import org.eclipse.papyrus.uml.diagram.timing.custom.edit.policies.CustomFullLifelineItemSemanticEditPolicyCN;
import org.eclipse.papyrus.uml.diagram.timing.custom.edit.policies.TimeRulerVisibilityRefreshEditPolicy;
import org.eclipse.papyrus.uml.diagram.timing.custom.edit.policies.TimingDiagramDragDropEditPolicy;
import org.eclipse.papyrus.uml.diagram.timing.custom.figures.FullLifelineFigure;
import org.eclipse.papyrus.uml.diagram.timing.edit.policies.FullLifelineItemSemanticEditPolicyCN;
import org.eclipse.papyrus.uml.diagram.timing.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.timing.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.timing.providers.UMLElementTypes;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
@SuppressWarnings("all")
// disable warnings on generated code
public class FullLifelineEditPartCN extends

LifelineEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 19;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public FullLifelineEditPartCN(final View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new PapyrusCreationEditPolicy());
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new FullLifelineItemSemanticEditPolicyCN());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new TimingDiagramDragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CustomFullLifelineItemSemanticEditPolicyCN());
		installEditPolicy(AppliedStereotypeLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new AppliedStereotypeNodeLabelDisplayEditPolicy());
		installEditPolicy(TimeRulerVisibilityRefreshEditPolicy.ROLE, new TimeRulerVisibilityRefreshEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable
		// editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * Papyrus codeGen
	 * 
	 * @generated
	 **/
	@Override
	protected void handleNotificationEvent(final Notification event) {
		super.handleNotificationEvent(event);

	}

	/**
	 * @generated
	 */
	@Override
	protected LayoutEditPolicy createLayoutEditPolicy() {
		final org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			@Override
			protected EditPolicy createChildEditPolicy(final EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if(result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			@Override
			protected Command getMoveChildrenCommand(final Request request) {
				return null;
			}

			@Override
			protected Command getCreateCommand(final CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	@Override
	protected IFigure createNodeShape() {
		return this.primaryShape = new FullLifelineFigure();
	}

	/**
	 * @generated
	 */
	@Override
	public FullLifelineFigure getPrimaryShape() {
		return (FullLifelineFigure)this.primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(final EditPart childEditPart) {
		if(childEditPart instanceof FullLifelineNameEditPart) {
			((FullLifelineNameEditPart)childEditPart).setLabel(getPrimaryShape().getLifelineLabelFigure());
			return true;
		}

		if(childEditPart instanceof FullLifelineStateDefinitionCompartmentEditPartCN) {
			final IFigure pane = getPrimaryShape().getStateDefinitionContainerFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.add(((FullLifelineStateDefinitionCompartmentEditPartCN)childEditPart).getFigure());
			return true;
		}

		if(childEditPart instanceof FullLifelineTimelineCompartmentEditPartCN) {
			final IFigure pane = getPrimaryShape().getTimelineContainerFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.add(((FullLifelineTimelineCompartmentEditPartCN)childEditPart).getFigure());
			return true;
		}

		if(childEditPart instanceof FullLifelineTimeRulerCompartmentEditPartCN) {
			final IFigure pane = getPrimaryShape().getTimeRulerContainerFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.add(((FullLifelineTimeRulerCompartmentEditPartCN)childEditPart).getFigure());
			return true;
		}

		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(final EditPart childEditPart) {
		if(childEditPart instanceof FullLifelineNameEditPart) {
			return true;
		}
		if(childEditPart instanceof FullLifelineStateDefinitionCompartmentEditPartCN) {
			final IFigure pane = getPrimaryShape().getStateDefinitionContainerFigure();
			pane.remove(((FullLifelineStateDefinitionCompartmentEditPartCN)childEditPart).getFigure());
			return true;
		}
		if(childEditPart instanceof FullLifelineTimelineCompartmentEditPartCN) {
			final IFigure pane = getPrimaryShape().getTimelineContainerFigure();
			pane.remove(((FullLifelineTimelineCompartmentEditPartCN)childEditPart).getFigure());
			return true;
		}
		if(childEditPart instanceof FullLifelineTimeRulerCompartmentEditPartCN) {
			final IFigure pane = getPrimaryShape().getTimeRulerContainerFigure();
			pane.remove(((FullLifelineTimeRulerCompartmentEditPartCN)childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void addChildVisual(final EditPart childEditPart, final int index) {
		if(addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	@Override
	protected void removeChildVisual(final EditPart childEditPart) {
		if(removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	@Override
	protected IFigure getContentPaneFor(final IGraphicalEditPart editPart) {
		if(editPart instanceof FullLifelineStateDefinitionCompartmentEditPartCN) {
			return getPrimaryShape().getStateDefinitionContainerFigure();
		}
		if(editPart instanceof FullLifelineTimelineCompartmentEditPartCN) {
			return getPrimaryShape().getTimelineContainerFigure();
		}
		if(editPart instanceof FullLifelineTimeRulerCompartmentEditPartCN) {
			return getPrimaryShape().getTimeRulerContainerFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	@Override
	protected NodeFigure createNodePlate() {
		final String prefElementId = "FullLifeline";
		final IPreferenceStore store = UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
		final String preferenceConstantWitdh = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId, PreferencesConstantsHelper.WIDTH);
		final String preferenceConstantHeight = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId, PreferencesConstantsHelper.HEIGHT);
		final DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(store.getInt(preferenceConstantWitdh), store.getInt(preferenceConstantHeight));

		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag
	 * and modify it.
	 * 
	 * @generated
	 */
	@Override
	protected NodeFigure createNodeFigure() {
		final NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		final IFigure shape = createNodeShape();
		figure.add(shape);
		this.contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects layout one may have set for generated
	 * figure.
	 * 
	 * @param nodeShape
	 *        instance of generated figure class
	 * @generated
	 */
	@Override
	protected IFigure setupContentPane(final IFigure nodeShape) {
		if(nodeShape.getLayoutManager() == null) {
			final ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	@Override
	public IFigure getContentPane() {
		if(this.contentPane != null) {
			return this.contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	@Override
	protected void setForegroundColor(final Color color) {
		if(this.primaryShape != null) {
			this.primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setLineWidth(final int width) {
		if(this.primaryShape instanceof Shape) {
			((Shape)this.primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setLineType(final int style) {
		if(this.primaryShape instanceof Shape) {
			((Shape)this.primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	@Override
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(UMLVisualIDRegistry.getType(FullLifelineNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getMARelTypesOnSource() {
		final ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Message_53);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getMARelTypesOnSourceAndTarget(final IGraphicalEditPart targetEditPart) {
		final LinkedList<IElementType> types = new LinkedList<IElementType>();
		if(targetEditPart instanceof MessageOccurrenceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Message_53);
		}
		if(targetEditPart instanceof DestructionOccurrenceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Message_53);
		}
		if(targetEditPart instanceof GateEditPart) {
			types.add(UMLElementTypes.Message_53);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getMATypesForTarget(final IElementType relationshipType) {
		final LinkedList<IElementType> types = new LinkedList<IElementType>();
		if(relationshipType == UMLElementTypes.Message_53) {
			types.add(UMLElementTypes.MessageOccurrenceSpecification_13);
			types.add(UMLElementTypes.DestructionOccurrenceSpecification_27);
			types.add(UMLElementTypes.Gate_69);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getMARelTypesOnTarget() {
		final ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Message_50);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getMATypesForSource(final IElementType relationshipType) {
		final LinkedList<IElementType> types = new LinkedList<IElementType>();
		if(relationshipType == UMLElementTypes.Message_50) {
			types.add(UMLElementTypes.MessageOccurrenceSpecification_13);
			types.add(UMLElementTypes.DestructionOccurrenceSpecification_27);
			types.add(UMLElementTypes.Gate_69);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public EditPart getTargetEditPart(final Request request) {
		if(request instanceof CreateViewAndElementRequest) {
			final CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest)request).getViewAndElementDescriptor().getCreateElementRequestAdapter();
			final IElementType type = (IElementType)adapter.getAdapter(IElementType.class);
			if(type == UMLElementTypes.Node_9) {
				return getChildBySemanticHint(UMLVisualIDRegistry.getType(FullLifelineStateDefinitionCompartmentEditPartCN.VISUAL_ID));
			}
			if(type == UMLElementTypes.Node_24) {
				return getChildBySemanticHint(UMLVisualIDRegistry.getType(FullLifelineTimeRulerCompartmentEditPartCN.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

	/**
	 * @generated
	 */
	@Override
	public Object getPreferredValue(final EStructuralFeature feature) {
		final IPreferenceStore preferenceStore = (IPreferenceStore)getDiagramPreferencesHint().getPreferenceStore();
		Object result = null;

		if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor() || feature == NotationPackage.eINSTANCE.getFontStyle_FontColor() || feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
			String prefColor = null;
			if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor()) {
				prefColor = PreferencesConstantsHelper.getElementConstant("FullLifeline", PreferencesConstantsHelper.COLOR_LINE);
			} else if(feature == NotationPackage.eINSTANCE.getFontStyle_FontColor()) {
				prefColor = PreferencesConstantsHelper.getElementConstant("FullLifeline", PreferencesConstantsHelper.COLOR_FONT);
			} else if(feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
				prefColor = PreferencesConstantsHelper.getElementConstant("FullLifeline", PreferencesConstantsHelper.COLOR_FILL);
			}
			result = FigureUtilities.RGBToInteger(PreferenceConverter.getColor(preferenceStore, prefColor));
		} else if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency() || feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()) {
			final String prefGradient = PreferencesConstantsHelper.getElementConstant("FullLifeline", PreferencesConstantsHelper.COLOR_GRADIENT);
			final GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(preferenceStore.getString(prefGradient));
			if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency()) {
				result = new Integer(gradientPreferenceConverter.getTransparency());
			} else if(feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()) {
				result = gradientPreferenceConverter.getGradientData();
			}
		}

		if(result == null) {
			result = getStructuralFeatureValue(feature);
		}
		return result;
	}
}
