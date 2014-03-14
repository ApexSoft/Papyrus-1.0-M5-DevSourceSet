package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.uml2.uml.InteractionFragment;

public class ConstraintEntry implements Entry<InteractionFragment, Rectangle> {
	private InteractionFragment key;
	private Rectangle constraint;
	public ConstraintEntry(InteractionFragment key, Rectangle constraint) {
		this.key = key;
		this.constraint = constraint;
	}

	public InteractionFragment getKey() {
		return key;
	}

	public Rectangle getValue() {
		return constraint;
	}

	public Rectangle setValue(Rectangle value) {
		return (constraint = value);
	}

}