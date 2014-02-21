package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.extensionpoints.editors.configuration.IAdvancedEditorConfiguration;
import org.eclipse.papyrus.extensionpoints.editors.configuration.IDirectEditorConfiguration;
import org.eclipse.papyrus.extensionpoints.editors.configuration.IPopupEditorConfiguration;
import org.eclipse.papyrus.extensionpoints.editors.ui.ExtendedDirectEditionDialog;
import org.eclipse.papyrus.extensionpoints.editors.ui.ILabelEditorDialog;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.papyrus.extensionpoints.editors.utils.DirectEditorsUtil;
import org.eclipse.papyrus.extensionpoints.editors.utils.IDirectEditorsIds;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.directedit.MultilineLabelDirectEditManager;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.IDirectEdition;
import org.eclipse.papyrus.uml.diagram.common.figure.node.ILabelFigure;
import org.eclipse.papyrus.uml.diagram.common.util.MessageDirection;
import org.eclipse.papyrus.uml.diagram.sequence.Activator;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.UMLTextSelectionEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.locator.GateLocator;
import org.eclipse.papyrus.uml.diagram.sequence.parsers.MessageFormatParser;
import org.eclipse.papyrus.uml.diagram.sequence.util.GateHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Gate;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.UMLPackage;

public class GateNameEditPart extends LabelEditPart implements ITextAwareEditPart, IBorderItemEditPart {

	public static final String GATE_NAME_TYPE = "Gate_Name";

	private DirectEditManager manager;

	private IParser parser;

	private List<?> parserElements;

	private String defaultText;

	protected int directEditionMode = IDirectEdition.UNDEFINED_DIRECT_EDITOR;

	protected IDirectEditorConfiguration configuration;
	static {
		registerSnapBackPosition(GATE_NAME_TYPE, new Point(-32, 0));
	}

	/**
	 * Constructor.
	 * 
	 * @param view
	 */
	public GateNameEditPart(View view) {
		super(view);
	}

	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new UMLTextSelectionEditPolicy());
	}

	public IBorderItemLocator getBorderItemLocator() {
		IFigure parentFigure = getFigure().getParent();
		if(parentFigure != null && parentFigure.getLayoutManager() != null) {
			Object constraint = parentFigure.getLayoutManager().getConstraint(getFigure());
			return (IBorderItemLocator)constraint;
		}
		return null;
	}

	public void refreshBounds() {
		int x = ((Integer)getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
		int y = ((Integer)getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
		int width = ((Integer)getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Width())).intValue();
		int height = ((Integer)getStructuralFeatureValue(NotationPackage.eINSTANCE.getSize_Height())).intValue();
		if(x == 0 && y == 0) {
			EditPart parent = getParent();
			if(parent instanceof GateEditPart) {
				GateEditPart gateEditPart = ((GateEditPart)parent);
				EObject elt = gateEditPart.resolveSemanticElement();
				Dimension preferredSize = getFigure().getPreferredSize(width, height);
				IBorderItemLocator locator = gateEditPart.getBorderItemLocator();
				if(locator instanceof GateLocator) {
					int alignment = ((GateLocator)locator).getAlignment(((GateLocator)locator).getConstraint());
					if(PositionConstants.LEFT == alignment) {
						if(gateEditPart.getTargetConnections().isEmpty()) {
							x = -preferredSize.width - 1;
						} else {
							x = GateEditPart.DEFAULT_SIZE.width + 1;
						}
						if(elt instanceof Gate && GateHelper.isInnerCFGate((Gate)elt)) {
							y = GateEditPart.DEFAULT_SIZE.height - 2;//move fown
						} else {
							y = -GateEditPart.DEFAULT_SIZE.height + 2;//move up
						}
					} else if(PositionConstants.RIGHT == alignment) {
						if(gateEditPart.getSourceConnections().isEmpty()) {
							x = GateEditPart.DEFAULT_SIZE.width + 1;
						} else {
							x = -preferredSize.width - 1;
						}
						if(elt instanceof Gate && GateHelper.isInnerCFGate((Gate)elt)) {
							y = GateEditPart.DEFAULT_SIZE.height - 2;//move fown
						} else {
							y = -GateEditPart.DEFAULT_SIZE.height + 2;//move up
						}
					} else if(PositionConstants.TOP == alignment) {
						y = -GateEditPart.DEFAULT_SIZE.height - 1;
					} else if(PositionConstants.BOTTOM == alignment) {
						y = GateEditPart.DEFAULT_SIZE.height + 1;
					}
				}
				//				SetBoundsCommand cmd = new SetBoundsCommand(getEditingDomain(), "", new EObjectAdapter(getNotationView()), new Point(x, y));
				//				CommandHelper.executeCommandWithoutHistory(getEditingDomain(), new GMFtoEMFCommandWrapper(cmd));
			}
		}
		IBorderItemLocator borderItemLocator = getBorderItemLocator();
		if(borderItemLocator != null) {
			borderItemLocator.setConstraint(new Rectangle(x, y, width, height));
		}
	}

	public int getKeyPoint() {
		return ConnectionLocator.MIDDLE;
	}

	protected String getLabelTextHelper(IFigure figure) {
		if(figure instanceof WrappingLabel) {
			return ((WrappingLabel)figure).getText();
		} else if(figure instanceof ILabelFigure) {
			return ((ILabelFigure)figure).getText();
		} else {
			return ((Label)figure).getText();
		}
	}

	protected void setLabelTextHelper(IFigure figure, String text) {
		if(figure instanceof WrappingLabel) {
			((WrappingLabel)figure).setText(text);
		} else if(figure instanceof ILabelFigure) {
			((ILabelFigure)figure).setText(text);
		} else {
			((Label)figure).setText(text);
		}
	}

	protected Image getLabelIconHelper(IFigure figure) {
		if(figure instanceof WrappingLabel) {
			return ((WrappingLabel)figure).getIcon();
		} else if(figure instanceof ILabelFigure) {
			return ((ILabelFigure)figure).getIcon();
		} else {
			return ((Label)figure).getIcon();
		}
	}

	protected void setLabelIconHelper(IFigure figure, Image icon) {
		if(figure instanceof WrappingLabel) {
			((WrappingLabel)figure).setIcon(icon);
		} else if(figure instanceof ILabelFigure) {
			((ILabelFigure)figure).setIcon(icon);
		} else {
			((Label)figure).setIcon(icon);
		}
	}

	public void setLabel(WrappingLabel figure) {
		unregisterVisuals();
		setFigure(figure);
		defaultText = getLabelTextHelper(figure);
		registerVisuals();
		refreshVisuals();
	}

	protected List getModelChildren() {
		return Collections.EMPTY_LIST;
	}

	public IGraphicalEditPart getChildBySemanticHint(String semanticHint) {
		return null;
	}

	protected EObject getParserElement() {
		return resolveSemanticElement();
	}

	protected Image getLabelIcon() {
		return null;
	}

	protected String getLabelText() {
		String text = null;
		EObject parserElement = getParserElement();
		if(parserElement != null && getParser() != null) {
			text = getParser().getPrintString(new EObjectAdapter(parserElement), getParserOptions().intValue());
		}
		if(text == null || text.length() == 0) {
			text = defaultText;
		}
		return text;
	}

	public void setLabelText(String text) {
		setLabelTextHelper(getFigure(), text);
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if(pdEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy)pdEditPolicy).refreshFeedback();
		}
		Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if(sfEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy)sfEditPolicy).refreshFeedback();
		}
	}

	public String getEditText() {
		if(getParserElement() == null || getParser() == null) {
			return ""; //$NON-NLS-1$
		}
		return getParser().getEditString(new EObjectAdapter(getParserElement()), getParserOptions().intValue());
	}

	protected boolean isEditable() {
		return getParser() != null;
	}

	public ICellEditorValidator getEditTextValidator() {
		return new ICellEditorValidator() {

			public String isValid(final Object value) {
				if(value instanceof String) {
					final EObject element = getParserElement();
					final IParser parser = getParser();
					try {
						IParserEditStatus valid = (IParserEditStatus)getEditingDomain().runExclusive(new RunnableWithResult.Impl() {

							public void run() {
								setResult(parser.isValidEditString(new EObjectAdapter(element), (String)value));
							}
						});
						return valid.getCode() == ParserEditStatus.EDITABLE ? null : valid.getMessage();
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}
				// shouldn't get here
				return null;
			}
		};
	}

	public IContentAssistProcessor getCompletionProcessor() {
		if(getParserElement() == null || getParser() == null) {
			return null;
		}
		return getParser().getCompletionProcessor(new EObjectAdapter(getParserElement()));
	}

	public ParserOptions getParserOptions() {
		return ParserOptions.NONE;
	}

	public IParser getParser() {
		if(parser == null) {
			parser = new GateNameParser();
		}
		return parser;
	}

	protected DirectEditManager getManager() {
		if(manager == null) {
			setManager(new MultilineLabelDirectEditManager(this, MultilineLabelDirectEditManager.getTextCellEditorClass(this), UMLEditPartFactory.getTextCellEditorLocator(this)));
		}
		return manager;
	}

	protected void setManager(DirectEditManager manager) {
		this.manager = manager;
	}

	protected void performDirectEdit() {
		getManager().show();
	}

	protected void performDirectEdit(Point eventLocation) {
		if(getManager() instanceof TextDirectEditManager) {
			((TextDirectEditManager)getManager()).show(eventLocation.getSWTPoint());
		}
	}

	private void performDirectEdit(char initialCharacter) {
		if(getManager() instanceof TextDirectEditManager) {
			((TextDirectEditManager)getManager()).show(initialCharacter);
		} else {
			performDirectEdit();
		}
	}

	protected void performDirectEditRequest(Request request) {
		final Request theRequest = request;
		if(IDirectEdition.UNDEFINED_DIRECT_EDITOR == directEditionMode) {
			directEditionMode = getDirectEditionType();
		}
		switch(directEditionMode) {
		case IDirectEdition.NO_DIRECT_EDITION:
			// no direct edition mode => does nothing
			return;
		case IDirectEdition.EXTENDED_DIRECT_EDITOR:
			updateExtendedEditorConfiguration();
			if(configuration == null || configuration.getLanguage() == null) {
				performDefaultDirectEditorEdit(theRequest);
			} else {
				configuration.preEditAction(resolveSemanticElement());
				Dialog dialog = null;
				if(configuration instanceof IPopupEditorConfiguration) {
					IPopupEditorHelper helper = ((IPopupEditorConfiguration)configuration).createPopupEditorHelper(this);
					helper.showEditor();
					return;
				} else if(configuration instanceof IAdvancedEditorConfiguration) {
					dialog = ((IAdvancedEditorConfiguration)configuration).createDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), resolveSemanticElement(), configuration.getTextToEdit(resolveSemanticElement()));
				} else if(configuration instanceof IDirectEditorConfiguration) {
					dialog = new ExtendedDirectEditionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), resolveSemanticElement(), ((IDirectEditorConfiguration)configuration).getTextToEdit(resolveSemanticElement()), (IDirectEditorConfiguration)configuration);
				} else {
					return;
				}
				final Dialog finalDialog = dialog;
				if(Window.OK == dialog.open()) {
					TransactionalEditingDomain domain = getEditingDomain();
					RecordingCommand command = new RecordingCommand(domain, "Edit Label") {

						@Override
						protected void doExecute() {
							configuration.postEditAction(resolveSemanticElement(), ((ILabelEditorDialog)finalDialog).getValue());
						}
					};
					domain.getCommandStack().execute(command);
				}
			}
			break;
		case IDirectEdition.DEFAULT_DIRECT_EDITOR:
			// initialize the direct edit manager
			try {
				getEditingDomain().runExclusive(new Runnable() {

					public void run() {
						if(isActive() && isEditable()) {
							if(theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
								Character initialChar = (Character)theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
								performDirectEdit(initialChar.charValue());
							} else if((theRequest instanceof DirectEditRequest) && (getEditText().equals(getLabelText()))) {
								DirectEditRequest editRequest = (DirectEditRequest)theRequest;
								performDirectEdit(editRequest.getLocation());
							} else {
								performDirectEdit();
							}
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshUnderline();
		refreshStrikeThrough();
	}

	protected void refreshLabel() {
		EditPolicy maskLabelPolicy = getEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY);
		if(maskLabelPolicy == null) {
			setLabelTextHelper(getFigure(), getLabelText());
			setLabelIconHelper(getFigure(), getLabelIcon());
		}
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if(pdEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy)pdEditPolicy).refreshFeedback();
		}
		Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if(sfEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy)sfEditPolicy).refreshFeedback();
		}
		refreshBounds();
	}

	protected void refreshUnderline() {
		FontStyle style = (FontStyle)getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if(style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel)getFigure()).setTextUnderline(style.isUnderline());
		}
		if(resolveSemanticElement() instanceof Feature) {
			if(((Feature)resolveSemanticElement()).isStatic()) {
				((WrappingLabel)getFigure()).setTextUnderline(true);
			} else {
				((WrappingLabel)getFigure()).setTextUnderline(false);
			}
		}
	}

	protected void refreshStrikeThrough() {
		FontStyle style = (FontStyle)getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if(style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel)getFigure()).setTextStrikeThrough(style.isStrikeThrough());
		}
	}

	protected void refreshFont() {
		FontStyle style = (FontStyle)getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if(style != null) {
			FontData fontData = new FontData(style.getFontName(), style.getFontHeight(), (style.isBold() ? SWT.BOLD : SWT.NORMAL) | (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
			setFont(fontData);
		}
	}

	protected void setFontColor(Color color) {
		getFigure().setForegroundColor(color);
	}

	protected void addSemanticListeners() {
		if(getParser() instanceof ISemanticParser) {
			EObject element = resolveSemanticElement();
			parserElements = ((ISemanticParser)getParser()).getSemanticElementsBeingParsed(element);
			for(int i = 0; i < parserElements.size(); i++) {
				addListenerFilter("SemanticModel" + i, this, (EObject)parserElements.get(i)); //$NON-NLS-1$
			}
		} else {
			super.addSemanticListeners();
		}
	}

	protected void removeSemanticListeners() {
		if(parserElements != null) {
			for(int i = 0; i < parserElements.size(); i++) {
				removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
			}
		} else {
			super.removeSemanticListeners();
		}
	}

	protected AccessibleEditPart getAccessibleEditPart() {
		if(accessibleEP == null) {
			accessibleEP = new AccessibleGraphicalEditPart() {

				public void getName(AccessibleEvent e) {
					e.result = getLabelTextHelper(getFigure());
				}
			};
		}
		return accessibleEP;
	}

	private View getFontStyleOwnerView() {
		return (View)getModel();
	}

	public int getDirectEditionType() {
		if(checkExtendedEditor()) {
			initExtendedEditorConfiguration();
			return IDirectEdition.EXTENDED_DIRECT_EDITOR;
		}
		if(checkDefaultEdition()) {
			return IDirectEdition.DEFAULT_DIRECT_EDITOR;
		}
		// not a named element. no specific editor => do nothing
		return IDirectEdition.NO_DIRECT_EDITION;
	}

	protected boolean checkExtendedEditor() {
		if(resolveSemanticElement() != null) {
			return DirectEditorsUtil.hasSpecificEditorConfiguration(resolveSemanticElement().eClass().getInstanceClassName());
		}
		return false;
	}

	protected boolean checkDefaultEdition() {
		return (getParser() != null);
	}

	protected void initExtendedEditorConfiguration() {
		if(configuration == null) {
			final String languagePreferred = Activator.getDefault().getPreferenceStore().getString(IDirectEditorsIds.EDITOR_FOR_ELEMENT + resolveSemanticElement().eClass().getInstanceClassName());
			if(languagePreferred != null && !languagePreferred.equals("")) {
				configuration = DirectEditorsUtil.findEditorConfiguration(languagePreferred, resolveSemanticElement().eClass().getInstanceClassName());
			} else {
				configuration = DirectEditorsUtil.findEditorConfiguration(IDirectEditorsIds.UML_LANGUAGE, resolveSemanticElement().eClass().getInstanceClassName());
			}
		}
	}

	protected void updateExtendedEditorConfiguration() {
		String languagePreferred = Activator.getDefault().getPreferenceStore().getString(IDirectEditorsIds.EDITOR_FOR_ELEMENT + resolveSemanticElement().eClass().getInstanceClassName());
		if(languagePreferred != null && !languagePreferred.equals("") && languagePreferred != configuration.getLanguage()) {
			configuration = DirectEditorsUtil.findEditorConfiguration(languagePreferred, resolveSemanticElement().eClass().getInstanceClassName());
		} else if(IDirectEditorsIds.SIMPLE_DIRECT_EDITOR.equals(languagePreferred)) {
			configuration = null;
		}
	}

	protected void performDefaultDirectEditorEdit(final Request theRequest) {
		// initialize the direct edit manager
		try {
			getEditingDomain().runExclusive(new Runnable() {

				public void run() {
					if(isActive() && isEditable()) {
						if(theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
							Character initialChar = (Character)theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
							performDirectEdit(initialChar.charValue());
						} else if((theRequest instanceof DirectEditRequest) && (getEditText().equals(getLabelText()))) {
							DirectEditRequest editRequest = (DirectEditRequest)theRequest;
							performDirectEdit(editRequest.getLocation());
						} else {
							performDirectEdit();
						}
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void handleNotificationEvent(Notification event) {
		Object feature = event.getFeature();
		if(NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			Integer c = (Integer)event.getNewValue();
			setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		} else if(NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
			refreshUnderline();
		} else if(NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
			refreshStrikeThrough();
		} else if(NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
			refreshFont();
		} else {
			if(getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
				refreshLabel();
			}
			if(getParser() instanceof ISemanticParser) {
				ISemanticParser modelParser = (ISemanticParser)getParser();
				if(modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if(resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
		}
		super.handleNotificationEvent(event);
	}

	protected IFigure createFigure() {
		WrappingLabel label = new WrappingLabel();
		defaultText = getLabelTextHelper(label);
		return label;
	}

	public class GateNameParser extends MessageFormatParser implements ISemanticParser {

		/**
		 * Constructor.
		 * 
		 * @param features
		 */
		public GateNameParser() {
			super(new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() });
		}

		/**
		 * @see org.eclipse.papyrus.uml.diagram.sequence.parsers.MessageFormatParser#getPrintString(org.eclipse.core.runtime.IAdaptable, int)
		 * 
		 * @param adapter
		 * @param flags
		 * @return
		 */
		@Override
		public String getPrintString(IAdaptable adapter, int flags) {
			//			Object element = adapter.getAdapter(EObject.class);
			//			if(element instanceof Gate) {
			//				Gate gate = (Gate)element;
			//				if(gate.eContainer() instanceof CombinedFragment) {
			//					Gate outerGate = GateHelper.getOuterCFGate(gate);
			//					if(outerGate != null) {
			//						gate = outerGate;
			//					}
			//				} else if(gate.eContainer() instanceof Interaction) {
			//					Gate actualGate = GateHelper.getActualGate(gate);
			//					if(actualGate != null) {
			//						gate = actualGate;
			//					}
			//				}
			//				String printString = getPrintString(gate);
			//				if(printString != null) {
			//					return printString;
			//				}
			//			}
			return super.getPrintString(adapter, flags);
		}

		/**
		 * @param gate
		 */
		protected String getPrintString(Gate gate) {
			Message message = gate.getMessage();
			if(message != null) {
				MessageDirection direction = null;
				EObject parent = gate.eContainer();
				if(parent instanceof CombinedFragment) {
					CombinedFragment cf = ((CombinedFragment)parent);
					if(gate == message.getSendEvent()) {
						MessageEnd receiveEvent = message.getReceiveEvent();
						Lifeline lifeline = getCoveredBy(receiveEvent);
						if(!cf.getCovereds().contains(lifeline)) {
							direction = MessageDirection.OUT;
						}
					} else {
						MessageEnd sendEvent = message.getSendEvent();
						Lifeline coveredBy = getCoveredBy(sendEvent);
						if(!cf.getCovereds().contains(coveredBy)) {
							direction = MessageDirection.IN;
						}
					}
				} else if(parent instanceof Interaction) {
					if(gate == message.getSendEvent()) {
						direction = MessageDirection.IN;
					} else if(gate == message.getReceiveEvent()) {
						direction = MessageDirection.OUT;
					}
				}
				if(direction == null) {
					if(gate == message.getSendEvent()) {
						direction = MessageDirection.OUT;
					} else if(gate == message.getReceiveEvent()) {
						direction = MessageDirection.IN;
					}
				}
				if(direction != null) {
					StringBuffer buf = new StringBuffer();
					buf.append(direction.getName() + "_");
					buf.append(message.getName());
					return new String(buf);
				}
			}
			return null;
		}

		private Lifeline getCoveredBy(MessageEnd messageEnd) {
			if(messageEnd == null) {
				return null;
			}
			if(messageEnd instanceof OccurrenceSpecification) {
				return ((OccurrenceSpecification)messageEnd).getCovered();
			}
			return null;
		}

		protected EStructuralFeature getEStructuralFeature(Object notification) {
			EStructuralFeature featureImpl = null;
			if(notification instanceof Notification) {
				Object feature = ((Notification)notification).getFeature();
				if(feature instanceof EStructuralFeature) {
					featureImpl = (EStructuralFeature)feature;
				}
			}
			return featureImpl;
		}

		private boolean isValidFeature(EStructuralFeature feature) {
			return UMLPackage.eINSTANCE.getNamedElement_Name().equals(feature);
		}

		/**
		 * @see org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser#getSemanticElementsBeingParsed(org.eclipse.emf.ecore.EObject)
		 * 
		 * @param element
		 * @return
		 */
		public List getSemanticElementsBeingParsed(EObject element) {
			if(element instanceof Gate) {
				return Collections.singletonList(element);
			}
			return Collections.emptyList();
		}

		/**
		 * @see org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser#areSemanticElementsAffected(org.eclipse.emf.ecore.EObject,
		 *      java.lang.Object)
		 * 
		 * @param listener
		 * @param notification
		 * @return
		 */
		public boolean areSemanticElementsAffected(EObject listener, Object notification) {
			EStructuralFeature feature = getEStructuralFeature(notification);
			return isValidFeature(feature);
		}
	}
}
