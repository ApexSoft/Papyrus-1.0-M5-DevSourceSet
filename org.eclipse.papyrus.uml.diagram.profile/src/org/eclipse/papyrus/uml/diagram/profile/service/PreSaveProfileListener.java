/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
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
package org.eclipse.papyrus.uml.diagram.profile.service;

import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.action.ValidateAction.EclipseResourcesUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.commands.CheckedOperationHistory;
import org.eclipse.papyrus.infra.core.lifecycleevents.DoSaveEvent;
import org.eclipse.papyrus.infra.core.lifecycleevents.ISaveEventListener;
import org.eclipse.papyrus.infra.core.resource.IModel;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.services.validation.ValidationTool;
import org.eclipse.papyrus.uml.diagram.profile.custom.commands.DefineProfileCommand;
import org.eclipse.papyrus.uml.profile.Activator;
import org.eclipse.papyrus.uml.profile.definition.PapyrusDefinitionAnnotation;
import org.eclipse.papyrus.uml.profile.ui.dialogs.ProfileDefinitionDialog;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;

/**
 * This class provides listeners
 * 
 * 
 * This class describes the actions to do just before the save action
 */
public class PreSaveProfileListener implements ISaveEventListener {

	/**
	 * If an error comes here, the save action can't be make entirely -> use try-catch!
	 * 
	 * This function is called before the user make a "save" action
	 */
	public void doSave(DoSaveEvent event) {
		try {
			//System.out.println("preSave Event received"); //$NON-NLS-1$
			/**
			 * if the root is a profile, we must define it. If it's another thing, we
			 * do nothing
			 */

			Profile rootProfile = null;
			/**
			 * Obtain the root profile
			 */
			ServicesRegistry registry = event.getServiceRegistry();

			ModelSet modelSet = ServiceUtils.getInstance().getModelSet(registry);

			IModel umlModel = modelSet.getModel(UmlModel.MODEL_ID);

			EObject profileEObject = null;
			if(umlModel != null) {
				profileEObject = ((UmlModel)umlModel).lookupRoot();
			}

			if(profileEObject instanceof Profile) {
				rootProfile = (Profile)profileEObject;
			}

			if(rootProfile == null) {
				return; //We're not saving a profile model
			}

			/**
			 * Does the user want define the profile?
			 */
			String DEFINE_MSG = "In order to apply this profile, it had to be defined.\nWould you like to define it?";
			String PAPYRUS_QUESTION = "Papyrus question"; //$NON-NLS-1$

			Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

			boolean result = MessageDialog.openQuestion(activeShell, PAPYRUS_QUESTION, DEFINE_MSG);
			if(!result) {
				return;
			}

			ProfileDefinitionDialog dialog = new ProfileDefinitionDialog(activeShell, rootProfile);
			dialog.open();
			if(dialog.getReturnCode() == Window.OK) {
				PapyrusDefinitionAnnotation papyrusAnnotation = dialog.getPapyrusDefinitionAnnotation();
				TransactionalEditingDomain domain = ServiceUtils.getInstance().getTransactionalEditingDomain(registry);
				//evaluate contraint of profiles
				AdapterFactory adapterFactory = domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)domain).getAdapterFactory() : null;
				Diagnostician diagnostician = createDiagnostician(adapterFactory, new NullProgressMonitor());
				BasicDiagnostic diagnostic = diagnostician.createDefaultDiagnostic(rootProfile);
				diagnostic.getSeverity();
				Map<Object, Object> context = diagnostician.createDefaultContext();
				diagnostician.validate(rootProfile, diagnostic, context);
				if(canDefine(diagnostic)) {

					DefineProfileCommand cmd = new DefineProfileCommand(domain, papyrusAnnotation, rootProfile, dialog.saveConstraintInDefinition());
					try {
						IStatus status = CheckedOperationHistory.getInstance().execute(cmd, new NullProgressMonitor(), null);
						switch(status.getSeverity()) {
						case IStatus.OK:
							MessageDialog.openInformation(activeShell, "The profile has been defined", "The profile has been successfully defined");
							break;
						case IStatus.WARNING:
							Activator.log.warn(status.getMessage());
							MessageDialog.openWarning(activeShell, "The profile has been defined", status.getMessage());
							break;
						case IStatus.ERROR:
							notifyErrors(activeShell, cmd.getDiagnostic());
							break;
						}
					} catch (ExecutionException e) {
						Activator.log.error(e);
						MessageDialog.openError(activeShell, "Uncaught exception", "An exception occurred during the profile definition: \n" + e.getMessage());
					}
				} else {
					handleDiagnostic(diagnostic, rootProfile);
					MessageDialog.openError(activeShell, "Profile not Valid", "The profile cannot be defined because it is invalid.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void notifyErrors(Shell activeShell, Diagnostic diagnostic) {
		Activator.log.error(diagnostic.getMessage(), diagnostic.getException());
		DiagnosticDialog.openProblem(activeShell, "Profile definition failed", "The following errors occured during the profile definition", diagnostic);
	}

	protected boolean canDefine(Diagnostic diagnostic) {
		int severity = diagnostic.getSeverity();
		if(severity == Diagnostic.CANCEL) {
			return false;
		}
		if(severity == Diagnostic.ERROR) {
			//
			for(Diagnostic childDiagnostic : diagnostic.getChildren()) {
				if(childDiagnostic.getSeverity() != Diagnostic.ERROR) {
					continue;
				}

				if(childDiagnostic.getData().isEmpty()) {
					continue;
				}

				//Only fail on validation errors occuring on the UML Profile itself; do not fail on its Ecore definitions
				if(childDiagnostic.getData().get(0) instanceof Element) {
					return false;
				}
			}
		}

		//Ok, Warning, Info
		return true;
	}

	protected Diagnostician createDiagnostician(final AdapterFactory adapterFactory, final IProgressMonitor progressMonitor) {
		return new Diagnostician() {

			@Override
			public String getObjectLabel(EObject eObject) {
				if(adapterFactory != null && !eObject.eIsProxy()) {
					IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(eObject, IItemLabelProvider.class);
					if(itemLabelProvider != null) {
						return itemLabelProvider.getText(eObject);
					}
				}
				return super.getObjectLabel(eObject);
			}

			@Override
			public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
				progressMonitor.worked(1);
				return super.validate(eClass, eObject, diagnostics, context);
			}
		};
	}

	protected void handleDiagnostic(Diagnostic diagnostic, Profile profil) {
		// Do not show a dialog, as in the original version since the user sees the result directly
		// in the model explorer
		Resource resource = profil.eResource();
		if(resource != null) {
			if(profil != null) {
				ValidationTool vt = new ValidationTool(profil);
				vt.deleteSubMarkers();
			}
			// IPath path = new Path(resource.getURI().toPlatformString (false));
			// IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
			// IFile file = wsRoot.getFile(path);
			// eclipseResourcesUtil.deleteMarkers (file);
			EclipseResourcesUtil eclipseResourcesUtil = new EclipseResourcesUtil();
			for(Diagnostic childDiagnostic : diagnostic.getChildren()) {
				eclipseResourcesUtil.createMarkers(resource, childDiagnostic);
				// createMarkersOnDi (file, childDiagnostic);
			}
		}
	}

	/**
	 * If an error comes here, the save action can't be make entirely -> use try-catch!
	 * 
	 * This function is called before the user make a "save as" action
	 */
	public void doSaveAs(DoSaveEvent event) {
		try {
			//System.out.println("preSaveAS Event received"); //$NON-NLS-1$
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
