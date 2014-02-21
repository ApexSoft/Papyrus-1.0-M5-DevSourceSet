/*****************************************************************************
 * Copyright (c) 2012 ATOS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Olivier Mélois (ATOS) - Initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.papyrus.sysml.diagram.requirement.edit.part;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.requirements.Requirement;

/**
 * Label used to display the id of a requirement
 */
public class CustomRequirementIdLabelEditPart extends CustomAbstractRequirementInformationLabelEditPart {

	public CustomRequirementIdLabelEditPart(View view) {
		super(view);
	}

	@Override
	public void refreshLabelText() {
		if(this.figure != null && this.getRequirement() != null) {
			WrappingLabel wrappingLabel = (WrappingLabel)figure;
			Requirement req = this.getRequirement();
			String id = req.getId();
			if(id == null) {
				id = ""; //$NON-NLS-1$
			}
			wrappingLabel.setText("id = \"" + id + "\""); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
