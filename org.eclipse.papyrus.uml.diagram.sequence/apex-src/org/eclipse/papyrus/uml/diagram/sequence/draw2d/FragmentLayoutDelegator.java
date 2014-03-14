package org.eclipse.papyrus.uml.diagram.sequence.draw2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart.CustomInteractionOperandFigure;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineFigure;
import org.eclipse.papyrus.uml.diagram.sequence.util.ConstraintEntry;
import org.eclipse.papyrus.uml.diagram.sequence.util.ConstraintRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.util.InteractionOperandCoveredHelper;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;

public class FragmentLayoutDelegator {

	private final static Insets PADDING = new Insets(5);
	private final static int SPACING = 5;
	private final static int EXEC_HEAD_HEIGHT_BEFORE_EXEC = 30;
	private final static int EXEC_HEAD_HEIGHT_BEFORE_CF = 10;
	private final static int EXEC_TAIL_HEIGHT = 10;

	private Stack<ConstraintEntry> fConstraintStack = new Stack<ConstraintEntry>();
	private Map<IFigure, Rectangle> fConstraintMap = new HashMap<IFigure, Rectangle>();

	private InteractionFragment fContainer;
	private EditPartViewer fViewer;
	
	private Dimension fPreferredSize;
	
	public FragmentLayoutDelegator(InteractionFragment container, EditPartViewer viewer) {
		this.fContainer = container;
		this.fViewer = viewer;
	}
	
	public void invalidate() {
		fPreferredSize = null;
	}

	public void layout(IFigure parent) {
		Iterator<IFigure> iter = fConstraintMap.keySet().iterator();
		while (iter.hasNext()) {
			IFigure figure = iter.next();
			Rectangle bounds = fConstraintMap.get(figure);
			if (parent != figure.getParent()) {
				Point offset = new Point();
				InteractionFragment container = fContainer;
				while (container instanceof InteractionOperand) {
					offset = offset.getTranslated(getOrigin(getFigure(container)));
					if (container.eContainer() instanceof CombinedFragment) {
						container = (CombinedFragment) container.eContainer();
						if (container.eContainer() instanceof InteractionFragment) {
							container = (InteractionFragment) container.eContainer();
						} else {
							break;
						}
					}
				}
				Point parentLocation = getOrigin(figure.getParent());
				bounds = bounds.getTranslated(offset);
				bounds = bounds.getTranslated(parentLocation.getNegated());
				ConstraintRegistry.INSTANCE.setConstraint(figure, bounds);
			}
			figure.setBounds(bounds);
		}
	}
	
	public Dimension getPreferredSize(IFigure parent) {
		if (fPreferredSize == null) {
			fPreferredSize = calculatePreferredSize(parent);
		}
		return fPreferredSize;
	}
	
	private Dimension calculatePreferredSize(IFigure f) {
		calculateBounds(f);

		Rectangle rect = new Rectangle();
		while (!fConstraintStack.isEmpty()) {
			ConstraintEntry entry = fConstraintStack.pop();
			rect.union(entry.getValue());
		}
		Insets insets = f.getInsets();
		Dimension d = new Dimension(rect.width + insets.getWidth(),
				rect.height + insets.getHeight());
		if (d.width > 0)
			d.width += PADDING.right;
		if (d.height > 0)
			d.height += PADDING.bottom;
		return d;
	}

	private void calculateBounds(IFigure parent) {
		fConstraintMap.clear();
		fConstraintStack.clear();

		int offsetY = getTopPadding(parent);

		List<InteractionFragment> orderingFragments = getOrderingFragments(fContainer);
		for (int i = 0; i < orderingFragments.size(); i++) {
			InteractionFragment fragment = orderingFragments.get(i);

			// CF
			if (fragment instanceof CombinedFragment) {
				IFigure figure = getFigure(fragment);
				if (figure == null) {
					continue;
				}
				Rectangle bounds = getPreferredBounds(figure);
				if (bounds == null) {
					continue;
				}
				if (!fConstraintStack.isEmpty()) {
					ConstraintEntry entry = fConstraintStack.peek();
					if (entry.getKey() instanceof ExecutionSpecification) {
						offsetY = entry.getValue().x + EXEC_HEAD_HEIGHT_BEFORE_CF;
					} else {
						offsetY = entry.getValue().bottom() + SPACING;
					}
				}

				bounds.y = Math.max(bounds.y, offsetY);
				fConstraintStack.push(new ConstraintEntry(fragment, bounds));
				fConstraintMap.put(figure, bounds);
			// ES
			} else if (fragment instanceof ExecutionSpecification) {
				IFigure figure = getFigure(fragment);
				if (figure == null) {
					continue;
				}
				Rectangle bounds = getPreferredBounds(figure);
				if (bounds == null) {
					continue;
				}
				if (!fConstraintStack.isEmpty()) {
					ConstraintEntry entry = fConstraintStack.peek();
					if (entry.getKey() instanceof ExecutionSpecification) {
						offsetY = entry.getValue().x + EXEC_HEAD_HEIGHT_BEFORE_EXEC;
					} else {
						offsetY = entry.getValue().bottom() + SPACING;
					}
				}

				Point offset = new Point();
				InteractionFragment container = fContainer;
				while (container instanceof InteractionOperand) {
					offset = offset.getTranslated(getOrigin(getFigure(container)));
					if (container.eContainer() instanceof CombinedFragment) {
						container = (CombinedFragment) container.eContainer();
						if (container.eContainer() instanceof InteractionFragment) {
							container = (InteractionFragment) container.eContainer();
						} else {
							break;
						}
					}
				}
				Point parentLocation = getOrigin(figure.getParent());
				bounds = bounds.getTranslated(parentLocation);
				bounds = bounds.getTranslated(offset.getNegated());

				bounds.y = Math.max(bounds.y, offsetY);
				fConstraintStack.push(new ConstraintEntry(fragment, bounds));
			// EOS
			} else if (fragment instanceof ExecutionOccurrenceSpecification) {
				ExecutionSpecification es = ((ExecutionOccurrenceSpecification) fragment).getExecution();
				if (fragment != es.getFinish()) {
					continue;
				}
				Rectangle bounds = null;
				Rectangle innerBounds = null;
				boolean isInnerExist = false;
				while (!fConstraintStack.isEmpty()) {
					ConstraintEntry entry = fConstraintStack.pop();
					if (es == entry.getKey()) {
						bounds = entry.getValue();
						break;
					} else {
						if (innerBounds == null) {
							innerBounds = entry.getValue();
						} else {
							innerBounds.union(entry.getValue());
						}
						isInnerExist = true;
					}
				}
				if (isInnerExist) {
					bounds.height = Math.max(bounds.bottom(), innerBounds.bottom() + EXEC_TAIL_HEIGHT) - bounds.y;
				}
				fConstraintStack.push(new ConstraintEntry(fragment, bounds));
				fConstraintMap.put(getFigure(es), bounds);
			}
		}
	}
	
	private IFigure getFigure(InteractionFragment fragment) {
		return InteractionOperandCoveredHelper.findFigure(fragment, fViewer);
	}

	private List<InteractionFragment> getOrderingFragments(InteractionFragment parent) {
		List<InteractionFragment> result = new ArrayList<InteractionFragment>();

		if (parent instanceof Interaction) {
			result.addAll(((Interaction) parent).getFragments());
		} else if (parent instanceof InteractionOperand) {
			result.addAll(((InteractionOperand) parent).getFragments());
		}

		return result;
	}

	private int getTopPadding(IFigure figure) {
		Rectangle rect = new Rectangle();

		if (figure instanceof CustomInteractionOperandFigure) {
			CustomInteractionOperandFigure f = (CustomInteractionOperandFigure) figure;
			WrappingLabel interactionConstraintLabel = f.getInteractionConstraintLabel();
			for (Object child : f.getChildren()) {
				if (child instanceof WrappingLabel && child != interactionConstraintLabel) {
					rect.union((Rectangle) getConstraint((WrappingLabel) child));
				}
			}
			return rect.bottom() + PADDING.top;
		} else if (figure instanceof BorderItemsAwareFreeFormLayer) {
			BorderItemsAwareFreeFormLayer f = (BorderItemsAwareFreeFormLayer) figure;
			Iterator<?> children = f.getChildren().iterator();
			while (children.hasNext()) {
				Object child = children.next();
				if (child instanceof BorderedNodeFigure) {
					IFigure mainFigure = ((BorderedNodeFigure) child).getMainFigure();
					if (mainFigure instanceof DefaultSizeNodeFigure &&
							mainFigure.getChildren().size() > 0) {
						Object o = ((DefaultSizeNodeFigure) mainFigure).getChildren().get(0);
						if (o instanceof LifelineFigure) {
							RectangleFigure nameContainer = ((LifelineFigure) o).getFigureLifelineNameContainerFigure();
							rect.union(nameContainer.getBounds());
						}
					}
				}
			}
			return rect.bottom();
		}
		return rect.bottom();
	}

	private Rectangle getPreferredBounds(IFigure figure) {
		Object constraint = getConstraint(figure);
		if (constraint instanceof Rectangle) {
			Rectangle bounds = (Rectangle) constraint;
			bounds = bounds.getCopy();
			
			int wHint = bounds.width;
			int hHint = bounds.height;
			if (wHint == -1 || hHint == -1) {
				Dimension preferredSize = figure.getPreferredSize(bounds.width, bounds.height);
				if (wHint == -1)
					wHint = preferredSize.width;
				if (hHint == -1)
					hHint = preferredSize.height;
			}
			
			Dimension min = figure.getMinimumSize(wHint, hHint);
			Dimension max = figure.getMaximumSize();
			
			bounds.width = Math.max(min.width, Math.min(max.width, wHint));
			bounds.height = Math.max(min.height, Math.min(max.height, hHint));
			return bounds;
		}
		return null;
	}
	
	private Point getOrigin(IFigure parent) {
		return parent.getClientArea().getLocation();
	}
	
	private Object getConstraint(IFigure f) {
		if (f != null && f.getParent() != null && f.getParent().getLayoutManager() != null) {
			return f.getParent().getLayoutManager().getConstraint(f);
		}
		return null;
	}

}
