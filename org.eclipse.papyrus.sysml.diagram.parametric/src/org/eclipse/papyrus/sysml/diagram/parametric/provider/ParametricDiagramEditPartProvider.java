/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		R�gis CHEVREL: chevrel.regis <at> gmail.com
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.parametric.provider;

import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.parametric.Activator;
import org.eclipse.papyrus.sysml.diagram.parametric.edit.part.ParametricDiagramEditPart;

public class ParametricDiagramEditPartProvider extends AbstractEditPartProvider {

	@Override
	public boolean provides(IOperation operation) {
		if(operation instanceof CreateGraphicEditPartOperation) {
			View view = ((IEditPartOperation)operation).getView();

			// Ensure current diagram is Parametric Diagram
			if(ElementTypes.DIAGRAM_ID.equals(view.getType())) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected Class<?> getDiagramEditPartClass(View view) {
		if(ElementTypes.DIAGRAM_ID.equals(view.getType())) {
			return ParametricDiagramEditPart.class;
		}

		Activator.log.error(new Exception("Could not create EditPart."));
		return null;
	}

}
