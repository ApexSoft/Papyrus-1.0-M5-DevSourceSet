/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.nattable.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.ui.NatEventData;
import org.eclipse.papyrus.infra.nattable.manager.axis.IAxisManager;
import org.eclipse.papyrus.infra.nattable.manager.table.INattableModelManager;
import org.eclipse.papyrus.infra.nattable.utils.TableEditingDomainUtils;
import org.eclipse.papyrus.infra.nattable.utils.TableSelectionWrapper;
import org.eclipse.papyrus.infra.tools.util.WorkbenchPartHelper;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The abstract handler to use for the table actions
 * 
 * @author Vincent Lorenzo
 * 
 */
public abstract class AbstractTableHandler extends AbstractHandler {

	/** the id used to find the NatEvent in the EclipseContext */
	public static final String NAT_EVENT_DATA_PARAMETER_ID = "natEventParameterId"; //$NON-NLS-1$

	/**
	 * the event which have declenched the call to setEnable(Object evaluationContext. This event contains the location of the mouse pointer when
	 * the popup menu for this handler have been created
	 */
	//TODO : should maybe be remove with the future usage of e4 and the Eclispe Context
	protected NatEventData eventData;

	/**
	 * the table selection wrapper
	 */
	protected TableSelectionWrapper wrapper = null;

	/**
	 * 
	 * @return
	 *         the current active part
	 */
	protected IWorkbenchPart getActivePart() {
		return WorkbenchPartHelper.getCurrentActiveWorkbenchPart();
	}

	/**
	 * 
	 * @return
	 *         the current table manager or <code>null</code> if not found
	 */
	protected INattableModelManager getCurrentNattableModelManager() {
		final IWorkbenchPart currentPart = getActivePart();
		if(currentPart != null) {
			final INattableModelManager manager = (INattableModelManager)currentPart.getAdapter(INattableModelManager.class);
			return manager;
		}
		return null;
	}


	/**
	 * Returns the EditingDomain associated to the table
	 * 
	 * @return
	 */
	protected TransactionalEditingDomain getTableEditingDomain() {//duplicated code from NattableModelManager
		return TableEditingDomainUtils.getTableEditingDomain(getCurrentNattableModelManager().getTable());
	}

	/**
	 * Returns the EditingDomain associated to the context
	 * 
	 * @return
	 */
	protected TransactionalEditingDomain getContextEditingDomain() {//duplicated code from NattableModelManager
		return TableEditingDomainUtils.getTableContextEditingDomain(getCurrentNattableModelManager().getTable());
	}

	/**
	 * 
	 * @param evaluationContext
	 *        the evaluation context
	 * @return
	 *         the NatEventData from this evaluation context
	 */
	protected NatEventData getNatEventData(final Object evaluationContext) {
		if(evaluationContext instanceof NatEventData) {
			return (NatEventData)evaluationContext;
		}
		NatEventData eventData = null;
		if(evaluationContext instanceof IEvaluationContext) {
			Object value = ((IEvaluationContext)evaluationContext).getVariable(NAT_EVENT_DATA_PARAMETER_ID);
			if(value instanceof NatEventData) {
				eventData = (NatEventData)value;
			}
		}
		//TODO : currently we can't have dependency on org.eclipse.e4.... 
		//that's why we can't add the variable NAT_EVENT_DATA_PARAMETER_ID and we need to create a NatEventData instead of to get it in evaluationContext
		if(eventData == null) {
			Point cursorLocation = Display.getDefault().getCursorLocation();
			Control control = Display.getDefault().getCursorControl();//TODO doesn't work when we are selecting a command in a menu!
			if(control instanceof NatTable) {//TODO : not nice, but required
				cursorLocation = control.toControl(cursorLocation);
				Event e = new Event();
				e.x = cursorLocation.x;
				e.y = cursorLocation.y;
				e.display = Display.getDefault();
				e.widget = control;
				MouseEvent event = new MouseEvent(e);
				eventData = NatEventData.createInstanceFromEvent(event);
			}
		}
		return eventData;
	}

	/**
	 * 
	 * @param evaluationContext
	 * @return
	 *         the index of the rows which are fully selected
	 */
	protected List<Integer> getFullSelectedRowsIndex(Object evaluationContext) {
		final INattableModelManager manager = getCurrentNattableModelManager();
		if(manager != null) {
			final NatEventData data = getNatEventData(evaluationContext);
			if(data != null) {
				final SelectionLayer layer = manager.getBodyLayerStack().getSelectionLayer();
				int[] fullSelectedColumnsPosition = layer.getFullySelectedRowPositions();
				List<Integer> positions = new ArrayList<Integer>();
				for(int i : fullSelectedColumnsPosition) {
					positions.add(layer.getRowIndexByPosition(i));
				}
				return positions;
			}
		}
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param evaluationContext
	 * @return
	 *         the index of the columns which are fully selected
	 */
	protected List<Integer> getFullSelectedColumnsIndex(Object evaluationContext) {
		final INattableModelManager manager = getCurrentNattableModelManager();
		if(manager != null) {
			final NatEventData data = getNatEventData(evaluationContext);
			if(data != null) {
				final SelectionLayer layer = manager.getBodyLayerStack().getSelectionLayer();
				int[] fullSelectedColumnsPosition = layer.getFullySelectedColumnPositions();
				List<Integer> positions = new ArrayList<Integer>();
				for(int i : fullSelectedColumnsPosition) {
					positions.add(layer.getColumnIndexByPosition(i));
				}
				return positions;
			}
		}
		return Collections.emptyList();
	}


	/**
	 * 
	 * @return
	 *         the row axis manager
	 */
	protected IAxisManager getRowAxisManager() {
		final INattableModelManager manager = getCurrentNattableModelManager();
		if(manager != null) {
			return manager.getRowAxisManager();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 *         the column axis manager
	 */
	protected IAxisManager getColumnAxisManager() {
		final INattableModelManager manager = getCurrentNattableModelManager();
		if(manager != null) {
			return manager.getColumnAxisManager();
		}
		return null;
	}

	/**
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#setEnabled(java.lang.Object)
	 * 
	 * @param evaluationContext
	 */
	@Override
	public void setEnabled(Object evaluationContext) {
		this.eventData = getNatEventData(evaluationContext);
		setBaseEnabled(getCurrentNattableModelManager() != null);
		wrapper = null;

		if(evaluationContext instanceof IEvaluationContext) {
			Object selection = HandlerUtil.getVariable(evaluationContext, "selection"); //$NON-NLS-1$
			//			if(selection instanceof IStructuredSelection) {
			//				Iterator<?> iter = ((IStructuredSelection)selection).iterator();
			//				while(iter.hasNext() && wrapper == null) {
			//					Object current = iter.next();
			//					if(current instanceof TableSelectionWrapper) {
			//						wrapper = (TableSelectionWrapper)current;
			//					}
			//				}
			//			}
			if(selection instanceof IAdaptable) {
				wrapper = (TableSelectionWrapper)((IAdaptable)selection).getAdapter(TableSelectionWrapper.class);
			}
		}
	}
}
