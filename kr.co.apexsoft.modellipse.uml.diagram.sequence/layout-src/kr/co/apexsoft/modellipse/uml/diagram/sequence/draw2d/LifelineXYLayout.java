package kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d;

import java.util.Iterator;

import kr.co.apexsoft.modellipse.uml.diagram.sequence.util.ConstraintRegistry;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class LifelineXYLayout extends XYLayout {

	@Override
	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
		Dimension origin = super.calculatePreferredSize(f, wHint, hHint);
		Rectangle rect = new Rectangle();
		Iterator<?> children = f.getChildren().iterator();
		while (children.hasNext()) {
			IFigure child = (IFigure) children.next();
			Rectangle r = ConstraintRegistry.INSTANCE.getConstraint(child);
			
			if (r == null) {
				r = (Rectangle) getConstraint(child);
				if (r == null) {
					continue;
				}
				
				if (r.width == -1 || r.height == -1) {
					Dimension preferredSize = child.getPreferredSize(wHint, hHint);
					if (r.width == -1)
						r.width = preferredSize.width;
					if (r.height == -1)
						r.height = preferredSize.height;
				}
			}
			rect.union(r);
		}
		Insets insets = f.getInsets();
		Dimension d = new Dimension(rect.width + insets.getWidth(), rect.height + insets.getHeight())
				.union(getBorderPreferredSize(f)).union(origin);
		return d;
	}

	@Override
	public void layout(IFigure parent) {
		Iterator<?> children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		
		Rectangle dotLineBounds = parent.getBounds();
		
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			Rectangle bounds = ConstraintRegistry.INSTANCE.getConstraint(f);
			
			if (bounds == null) {
				bounds = (Rectangle) getConstraint(f);
				if (bounds == null) {
					continue;
				}
				if (bounds.width == -1 || bounds.height == -1) {
					Dimension preferredSize = f.getPreferredSize(bounds.width,
							bounds.height);
					bounds = bounds.getCopy();
					if (bounds.width == -1)
						bounds.width = preferredSize.width;
					if (bounds.height == -1)
						bounds.height = preferredSize.height;
				}
			}
			bounds.x = dotLineBounds.width/2 - bounds.width/2;
			bounds = bounds.getTranslated(offset);

			f.setBounds(bounds);
		}
		
	}

	@Override
	public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
		// TODO Auto-generated method stub
		return super.getMinimumSize(container, wHint, hHint);
	}

	@Override
	public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
		// TODO Auto-generated method stub
		return super.getPreferredSize(container, wHint, hHint);
	}

	@Override
	public void setConstraint(IFigure figure, Object newConstraint) {
		// TODO Auto-generated method stub
		super.setConstraint(figure, newConstraint);
	}

}
