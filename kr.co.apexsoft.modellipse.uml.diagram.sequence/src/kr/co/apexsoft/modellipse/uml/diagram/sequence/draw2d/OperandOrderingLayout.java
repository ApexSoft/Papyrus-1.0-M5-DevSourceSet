package kr.co.apexsoft.modellipse.uml.diagram.sequence.draw2d;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class OperandOrderingLayout extends FlowLayout {

	public final static int STRETCH_BOTH = 0;
	public final static int STRETCH_FIRST = 1;
	public final static int STRETCH_LAST = 2;
	
	private int majorStretch = STRETCH_FIRST;
	
	public OperandOrderingLayout(boolean isHorizontal) {
		super(isHorizontal);
		setMinorSpacing(0);
		setMajorSpacing(0);
		setStretchMinorAxis(true);
	}
	
	/**
	 * Returns the stretch type used for an entire row/column.
	 * <P>
	 * Possible values are :
	 * <ul>
	 * <li>{@link #STRETCH_BOTH}
	 * <li>{@link #STRETCH_FIRST}
	 * <li>{@link #STRETCH_LAST}
	 * </ul>
	 * 
	 * @return the major stretch
	 */
	public int getMajorStretch() {
		return majorStretch;
	}
	
	/**
	 * Sets the stretch type for an entire row/column within the parent figure.
	 * <P>
	 * Possible values are :
	 * <ul>
	 * <li>{@link #STRETCH_BOTH}
	 * <li>{@link #STRETCH_FIRST}
	 * <li>{@link #STRETCH_LAST}
	 * </ul>
	 * 
	 * @param align
	 *            the major stretch
	 */
	public void setMajorStretch(int stretch) {
		this.majorStretch = stretch;
	}
	
	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
		return super.calculatePreferredSize(container, wHint, hHint);
	}

	@Override
	public void layout(IFigure parent) {
		super.layout(parent);
	}

	@Override
	protected void layoutRow(IFigure parent) {
		int majorAdjustment = 0;
		int minorAdjustment = 0;
		int correctMinorAlignment = getMinorAlignment();
		int correctMajorStretch = getMajorStretch();
		
		int firstStretch = 0;
		int nextStretch = 0;
		
		majorAdjustment = data.area.width - data.rowWidth + getMinorSpacing();

		switch (correctMajorStretch) {
		case STRETCH_FIRST:
			firstStretch = majorAdjustment;
			break;
		case STRETCH_BOTH:
			firstStretch = nextStretch = majorAdjustment / data.rowCount;
			break;
		case STRETCH_LAST:
			break;
		}
		
		int totalStretch = 0;
		for (int j = 0; j < data.rowCount; j++) {
			if (isStretchMinorAxis()) {
				data.bounds[j].height = data.rowHeight;
			} else {
				minorAdjustment = data.rowHeight - data.bounds[j].height;
				switch (correctMinorAlignment) {
				case ALIGN_TOPLEFT:
					minorAdjustment = 0;
					break;
				case ALIGN_CENTER:
					minorAdjustment /= 2;
					break;
				case ALIGN_BOTTOMRIGHT:
					break;
				}
				data.bounds[j].y += minorAdjustment;
			}
			
			if (j == 0) {
				data.bounds[j].width += firstStretch;
				totalStretch += firstStretch;
			} else if (j < data.rowCount - 1) {
				data.bounds[j].x += totalStretch;
				data.bounds[j].width += nextStretch;
				totalStretch += nextStretch;
			} else {
				data.bounds[j].x += totalStretch;
				data.bounds[j].width += majorAdjustment - totalStretch;
			}

			setBoundsOfChild(parent, data.row[j], transposer.t(data.bounds[j]));
		}
		data.rowY += getMajorSpacing() + data.rowHeight;
		initRow();
	}
	
	protected void initVariables(IFigure parent) {
		data.row = new IFigure[parent.getChildren().size()];
		data.bounds = new Rectangle[data.row.length];
		data.maxWidth = Integer.MAX_VALUE;
	}

	@Override
	protected void initRow() {
		data.rowX = 0;
		data.rowHeight = data.area.height;
		data.rowWidth = 0;
		data.rowCount = 0;
	}

	protected Map<IFigure, Object> constraints = new HashMap<IFigure, Object>();
	
	@Override
	public Object getConstraint(IFigure child) {
		return constraints.get(child);
	}

	@Override
	public void setConstraint(IFigure child, Object constraint) {
		constraints.put(child, constraint);
	}

	@Override
	public void remove(IFigure child) {
		super.remove(child);
		constraints.remove(child);
	}

	@Override
	protected Dimension getChildSize(IFigure child, int wHint, int hHint) {
		Rectangle bounds = (Rectangle) getConstraint(child);
		if (bounds.width == -1 || bounds.height == -1) {
			Dimension pref = child.getPreferredSize(wHint, hHint);
			bounds = bounds.getCopy();
			if (bounds.width == -1)
				bounds.width = pref.width;
			if (bounds.height == -1)
				bounds.height = pref.height;
		}
		return bounds.getSize();
	}

}