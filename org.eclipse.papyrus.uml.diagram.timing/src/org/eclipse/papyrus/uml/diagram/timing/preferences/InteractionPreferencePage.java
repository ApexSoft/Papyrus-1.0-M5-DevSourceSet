/*
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.uml.diagram.timing.preferences;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.AbstractPapyrusNodePreferencePage;
import org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper;
import org.eclipse.papyrus.uml.diagram.timing.edit.parts.TimingDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.timing.part.UMLDiagramEditorPlugin;

/**
 * @generated
 */
@SuppressWarnings("all")
// disable warnings on generated code
public class InteractionPreferencePage extends AbstractPapyrusNodePreferencePage {

	/**
	 * @generated
	 */
	public static final String compartments[] = { "InteractionCompartment", "TimeRulerCompartment" };

	/**
	 * @generated
	 */
	public InteractionPreferencePage() {
		super();
		setPreferenceKey(TimingDiagramEditPart.MODEL_ID + "_Interaction");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UMLDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	public static void initDefaults(final IPreferenceStore store) {

		final String key = TimingDiagramEditPart.MODEL_ID + "_Interaction";
		store.setDefault(PreferencesConstantsHelper.getElementConstant(key, PreferencesConstantsHelper.WIDTH), 600);
		store.setDefault(PreferencesConstantsHelper.getElementConstant(key, PreferencesConstantsHelper.HEIGHT), 400);

		Map<String, Boolean> map = getStaticCompartmentVisibilityPreferences();
		for(final String name : map.keySet()) {
			final String preferenceName = PreferencesConstantsHelper.getLabelElementConstant(key, name, PreferencesConstantsHelper.COMPARTMENT_VISIBILITY);
			store.setDefault(preferenceName, map.get(name));
		}

		map = getStaticCompartmentTitleVisibilityPreferences();
		for(final String name : map.keySet()) {
			final String preferenceName = PreferencesConstantsHelper.getLabelElementConstant(key, name, PreferencesConstantsHelper.COMPARTMENT_NAME_VISIBILITY);
			store.setDefault(preferenceName, map.get(name));
		}

		// org.eclipse.jface.preference.PreferenceConverter.setDefault(store,
		// org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.getElementConstant(elementName,
		// org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.COLOR_FILL), new
		// org.eclipse.swt.graphics.RGB(255, 255, 255));
		// org.eclipse.jface.preference.PreferenceConverter.setDefault(store,
		// org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.getElementConstant(elementName,
		// org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.COLOR_LINE), new
		// org.eclipse.swt.graphics.RGB(0, 0, 0));

		// Set the default for the gradient
		// store.setDefault(org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.getElementConstant(elementName,
		// org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.GRADIENT_POLICY),false);
		// org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter gradientPreferenceConverter =
		// new org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter(
		// new org.eclipse.swt.graphics.RGB(255, 255, 255),
		// new org.eclipse.swt.graphics.RGB(0, 0, 0), 0, 0);
		// store.setDefault(org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.getElementConstant(elementName,
		// org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper.COLOR_GRADIENT),
		// gradientPreferenceConverter.getPreferenceValue());

	}

	/**
	 * @generated
	 */
	@Override
	protected void initializeCompartmentsList() {
		for(final String name : compartments) {
			this.compartmentsList.add(name);
		}
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticCompartmentVisibilityPreferences() {
		final TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		map.put("InteractionCompartment", Boolean.TRUE);
		map.put("TimeRulerCompartment", Boolean.TRUE);
		return map;
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticCompartmentTitleVisibilityPreferences() {
		final TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		return map;
	}

	/**
	 * @generated
	 */
	@Override
	protected TreeMap<String, Boolean> getCompartmentTitleVisibilityPreferences() {
		return getStaticCompartmentTitleVisibilityPreferences();
	}

}
