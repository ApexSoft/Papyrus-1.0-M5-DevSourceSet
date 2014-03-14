package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.HashMap;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

public class ConstraintRegistry extends HashMap<IFigure, Rectangle> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 255967478126778396L;
	
	public final static ConstraintRegistry INSTANCE = new ConstraintRegistry();
	
	private ConstraintRegistry() {
	}
	
	public Rectangle getConstraint(IFigure f) {
		return get(f);
	}
	
	public void setConstraint(IFigure f, Rectangle r) {
		put(f, r);
	}
	
	public boolean isExistConstraint(IFigure f) {
		return containsKey(f);
	}
	
}
