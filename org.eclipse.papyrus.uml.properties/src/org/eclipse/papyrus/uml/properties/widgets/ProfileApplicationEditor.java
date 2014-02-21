/*****************************************************************************
 * Copyright (c) 2011, 2013 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *  Christian W. Damus (CEA) - Refactoring package/profile import/apply UI for CDO
 *  
 *****************************************************************************/
package org.eclipse.papyrus.uml.properties.widgets;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.widgets.editors.MultipleReferenceEditor;
import org.eclipse.papyrus.uml.profile.definition.Version;
import org.eclipse.papyrus.uml.profile.ui.dialogs.ElementImportTreeSelectionDialog.ImportSpec;
import org.eclipse.papyrus.uml.profile.ui.dialogs.ProfileTreeSelectionDialog;
import org.eclipse.papyrus.uml.profile.utils.Util;
import org.eclipse.papyrus.uml.profile.validation.ProfileValidationHelper;
import org.eclipse.papyrus.uml.properties.Activator;
import org.eclipse.papyrus.uml.properties.messages.Messages;
import org.eclipse.papyrus.uml.properties.profile.ui.dialogs.Message;
import org.eclipse.papyrus.uml.properties.profile.ui.dialogs.RegisteredProfileSelectionDialog;
import org.eclipse.papyrus.uml.tools.importsources.PackageImportSourceDialog;
import org.eclipse.papyrus.uml.tools.utils.ProfileUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;

/**
 * An editor for Profile application
 * 
 * @author Camille Letavernier
 */
public class ProfileApplicationEditor extends MultipleReferenceEditor {

	/**
	 * The button to add profiles from the list of registered ones
	 */
	protected Button addRegisteredProfile;

	/**
	 * The button to reapply a profile
	 */
	protected Button reapplyProfile;

	/**
	 * The umlPackage being edited
	 */
	protected Package umlPackage;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param parent
	 * @param style
	 */
	public ProfileApplicationEditor(Composite parent, int style) {
		super(parent, style);

		tree.setHeaderVisible(true);
		GridData treeData = new GridData(SWT.FILL, SWT.FILL, true, true);
		treeData.horizontalSpan = 2;
		treeData.minimumHeight = 140;
		tree.setLayoutData(treeData);

		TableLayout layout = new TableLayout(true);
		tree.setLayout(layout);

		TreeColumn nameColumn = new TreeColumn(tree, SWT.NONE);
		nameColumn.setText("Name");
		layout.addColumnData(new ColumnWeightData(40, 400, true));

		TreeColumn locationColumn = new TreeColumn(tree, SWT.NONE);
		locationColumn.setText("Location");
		layout.addColumnData(new ColumnWeightData(30, 300, true));

		TreeColumn versionColumn = new TreeColumn(tree, SWT.NONE);
		versionColumn.setText("Version");
		layout.addColumnData(new ColumnWeightData(10, 100, true));
	}

	@Override
	public void setToolTipText(String text) {
		//Override to avoid displaying a tooltip on the tree. It prevents the Cells tooltips from working
		super.setLabelToolTipText(text);
	}

	@Override
	public void setLabelProvider(ILabelProvider labelProvider) {
		super.setLabelProvider(new ProfileColumnsLabelProvider(labelProvider));
	}

	protected class ProfileColumnsLabelProvider extends ColumnLabelProvider {

		private ILabelProvider defaultLabelProvider;

		public ProfileColumnsLabelProvider(ILabelProvider defaultLabelProvider) {
			this.defaultLabelProvider = defaultLabelProvider;
		}

		@Override
		public void update(ViewerCell cell) {
			if(cell.getColumnIndex() == 0) {
				updateName(cell);
				return;
			}

			Profile profile;
			EObject element = EMFHelper.getEObject(cell.getElement());

			if(element instanceof Profile) {
				profile = (Profile)element;
			} else {
				cell.setText("");
				return;
			}

			switch(cell.getColumnIndex()) {
			case 1:
				updateLocation(cell, profile);
				break;
			case 2:
				updateVersion(cell, profile);
				break;
			}

		}

		public void updateName(ViewerCell cell) {
			cell.setImage(defaultLabelProvider.getImage(cell.getElement()));
			cell.setText(defaultLabelProvider.getText(cell.getElement()));
		}

		public void updateLocation(ViewerCell cell, Profile profile) {
			String location = "Unknown";
			if(profile.eIsProxy()) {
				location = EcoreUtil.getURI(profile).trimFragment().toString();
			} else if(profile.eResource() != null) {
				URI uri = profile.eResource().getURI();
				if(uri != null) {
					location = uri.toString();
				}
			}

			cell.setText(location);
		}

		public void updateVersion(ViewerCell cell, Profile profile) {
			String versionText = "";
			Version version = Util.getProfileDefinitionVersion(profile);
			if(version != Version.emptyVersion) {
				versionText = version.toString();
			}

			cell.setText(versionText);
		}
	}

	@Override
	protected void createListControls() {
		super.createListControls();
		up.dispose();
		down.dispose();
		edit.dispose();
		up = down = edit = null;

		add.setToolTipText(Messages.ProfileApplicationEditor_ApplyProfile);
		addRegisteredProfile = createButton(Activator.getDefault().getImage("/icons/AddReg.gif"), Messages.ProfileApplicationEditor_ApplyRegisteredProfile); //$NON-NLS-1$

		reapplyProfile = createButton(org.eclipse.papyrus.infra.widgets.Activator.getDefault().getImage("/icons/refresh.gif"), "Reapply profile");
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				updateControls();
			}
		});
	}

	/**
	 * Applies a profile from workspace
	 * 
	 * @see org.eclipse.papyrus.infra.widgets.editors.MultipleValueEditor#addAction()
	 * 
	 */
	@Override
	protected void addAction() {
		//Code from org.eclipse.papyrus.uml.profile.ui.compositesformodel.AppliedProfileCompositeOnModel#applyProfileButtonPressed()


		// Create and open the dialog box
		// ResourceSelectionDialog dialog =
		// new ResourceSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), "Apply Profiles");

		Map<String, String> extensionFilters = new LinkedHashMap<String, String>();
		extensionFilters.put("*.profile.uml", "UML Profiles (*.profile.uml)");
		extensionFilters.put("*.uml", "UML (*.uml)");
		extensionFilters.put("*", "All (*)");

		Collection<Package> packages = PackageImportSourceDialog.open(getShell(), Messages.ProfileApplicationEditor_ApplyProfilesDialogTitle, Collections.singletonList(umlPackage), extensionFilters);

		// If nothing is selected : abort
		if((packages == null) || packages.isEmpty()) {
			return;
		}

		if(packages.size() > 0) {
			ProfileTreeSelectionDialog profileDialog = new ProfileTreeSelectionDialog(getShell(), packages);

			if(profileDialog.open() != Window.OK) {
				return;
			}

			if(profileDialog.getResult().isEmpty()) {
				return;
			}

			Collection<ImportSpec<Profile>> profilesImportToApply = profileDialog.getResult();

			Collection<Profile> profilesToApply = new LinkedList<Profile>();
			for(ImportSpec<Profile> importProfile : profilesImportToApply) {
				profilesToApply.add(importProfile.getElement());
			}

			if(!ProfileValidationHelper.checkApplicableProfiles(getShell(), profilesToApply)) {
				return;
			}

			Message message = new Message(Messages.ProfileApplicationEditor_WaitMessageTitle, Messages.ProfileApplicationEditor_WaitMessage);
			message.open();
			for(Profile profile : profilesToApply) {
				modelProperty.add(profile);
			}
			message.close();
			commit();
		}
	}

	/**
	 * Applies a profile from the registry
	 */
	protected void addRegisteredAction() {
		RegisteredProfileSelectionDialog profileSelectionDialog = new RegisteredProfileSelectionDialog(getShell(), umlPackage);
		List<Profile> profilesToApply = profileSelectionDialog.run();
		for(Profile profile : profilesToApply) {
			modelProperty.add(profile);
		}

		commit();
	}

	protected void reapplyProfileAction() {
		ISelection selectedElements = treeViewer.getSelection();

		//Filter profiles
		List<Profile> profilesToRefresh = new LinkedList<Profile>();
		if(!selectedElements.isEmpty() && selectedElements instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection)selectedElements;
			Iterator<?> iterator = selection.iterator();
			while(iterator.hasNext()) {
				Object element = iterator.next();
				if(element instanceof Profile) {
					profilesToRefresh.add((Profile)element);
				}
			}
		}

		//Check validity
		if(ProfileValidationHelper.checkApplicableProfiles(getShell(), profilesToRefresh)) {
			//If everything is fine, refresh the profiles
			for(Profile profile : profilesToRefresh) {
				modelProperty.add(profile);
			}

			commit();
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		super.widgetSelected(e);
		if(e.widget == addRegisteredProfile) {
			addRegisteredAction();
		}
		if(e.widget == reapplyProfile) {
			reapplyProfileAction();
		}
	}

	/**
	 * Sets the package on which the profiles will be applied
	 * 
	 * @param umlPackage
	 */
	public void setPackage(Package umlPackage) {
		this.umlPackage = umlPackage;
		updateControls();
	}

	@Override
	protected void updateControls() {
		boolean enabled = modelProperty != null && umlPackage != null;
		add.setEnabled(enabled);
		addRegisteredProfile.setEnabled(enabled);
		remove.setEnabled(enabled);

		// check whether the selection can be reapplied
		IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection();
		enabled = false;
		Iterator<?> iterator = selection.iterator();
		while(iterator.hasNext()) {
			Object element = iterator.next();
			if(element instanceof Profile) {
				if(ProfileUtil.isDirty(umlPackage, (Profile)element)) {
					enabled = true; //At least one dirty profile is selected
					break;
				}
			}
		}

		reapplyProfile.setEnabled(enabled);
	}
}