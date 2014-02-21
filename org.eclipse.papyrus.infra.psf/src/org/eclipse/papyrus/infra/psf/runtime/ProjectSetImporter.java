/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Camille Letavernier (CEA LIST) camille.letavernier@cea.fr
 *******************************************************************************/
package org.eclipse.papyrus.infra.psf.runtime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.psf.Activator;
import org.eclipse.papyrus.infra.psf.ui.FilterProjectsDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.core.IProjectSetSerializer;
import org.eclipse.team.core.ProjectSetCapability;
import org.eclipse.team.core.ProjectSetSerializationContext;
import org.eclipse.team.core.RepositoryProviderType;
import org.eclipse.team.core.Team;
import org.eclipse.team.core.TeamException;
import org.eclipse.team.internal.core.TeamPlugin;
import org.eclipse.team.internal.ui.TeamCapabilityHelper;
import org.eclipse.team.internal.ui.TeamUIMessages;
import org.eclipse.team.internal.ui.TeamUIPlugin;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * Initially copied from org.eclipse.team.internal.ui.ProjectSetImporter
 */
@SuppressWarnings({ "restriction", "deprecation" })
public class ProjectSetImporter {

	/**
	 * Imports a psf file based on a file content. This may be used when psf
	 * file is imported from any other location that local filesystem.
	 * 
	 * @param psfContents
	 *        the content of the psf file.
	 * @param filename
	 *        the name of the source file. This is included in case the
	 *        provider needs to deduce relative paths
	 * @param shell
	 * @param monitor
	 * @return list of new projects
	 * @throws InvocationTargetException
	 */
	public static ImportResult importProjectSetFromString(String psfContents, String filename, Shell shell, IProgressMonitor monitor) throws InvocationTargetException {
		XMLMemento xmlMemento = stringToXMLMemento(psfContents);
		return importProjectSet(xmlMemento, filename, shell, monitor);
	}

	/**
	 * Imports a psf file.
	 * 
	 * @param filename
	 * @param shell
	 * @param monitor
	 * @return list of new projects
	 * @throws InvocationTargetException
	 */
	public static ImportResult importProjectSet(String filename, Shell shell, IProgressMonitor monitor) throws InvocationTargetException {
		XMLMemento xmlMemento = filenameToXMLMemento(filename);
		return importProjectSet(xmlMemento, filename, shell, monitor);
	}

	/**
	 * Differences with the base implementation:
	 * 
	 * - User can select the projects to import
	 * - Projects are imported one by one (Instead of being imported provider by provider). The workspace is not locked during the import
	 * - Exceptions are caught and stored. They do not break the import
	 * - The method returns an ImportResult, containing the list of successfully imported projects and a diagnostic for errors
	 */
	private static ImportResult importProjectSet(XMLMemento xmlMemento, String filename, Shell shell, IProgressMonitor mainMonitor) throws InvocationTargetException {

		ImportResult result = new ImportResult();
		final List<IStatus> diagnostic = result.getDiagnostic();

		Map<String, List<String>> providersToProjects = filterProjects(xmlMemento, shell);

		if(providersToProjects == null) {
			mainMonitor.setCanceled(true);
			return result;
		}


		try {
			String version = xmlMemento.getString("version"); //$NON-NLS-1$

			final List<IProject> newProjects = new ArrayList<IProject>();
			if(version.equals("1.0")) { //$NON-NLS-1$
				IProjectSetSerializer serializer = Team.getProjectSetSerializer("versionOneSerializer"); //$NON-NLS-1$
				if(serializer != null) {
					IProject[] projects = serializer.addToWorkspace(new String[0], filename, shell, new NullProgressMonitor());
					if(projects != null) {
						newProjects.addAll(Arrays.asList(projects));
					}
				}
			} else {
				final ProjectSetSerializationContext context = new ProjectSetSerializationContext(filename);

				int nbProjects = 0;

				for(List<String> projects : providersToProjects.values()) {
					nbProjects += projects.size();
				}

				int nbWorkingSets = xmlMemento.getChildren("workingSets").length;

				int totalWork = nbProjects + nbWorkingSets;

				mainMonitor.beginTask(String.format("Importing %s projects", nbProjects), totalWork);

				for(Map.Entry<String, List<String>> providerToProjects : providersToProjects.entrySet()) {
					if(mainMonitor.isCanceled()) {
						return result;
					}

					List<String> projects = providerToProjects.getValue();
					String id = providerToProjects.getKey();

					TeamCapabilityHelper.getInstance().processRepositoryId(id, PlatformUI.getWorkbench().getActivitySupport());
					RepositoryProviderType providerType = RepositoryProviderType.getProviderType(id);
					if(providerType == null) {
						// The provider type is absent. Perhaps there is another provider that can import this type
						providerType = TeamPlugin.getAliasType(id);
					}

					if(providerType == null) {
						diagnostic.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, String.format("Unknown team provider: %s", id)));
						mainMonitor.worked(projects.size());
						continue;
					}

					final ProjectSetCapability serializer = providerType.getProjectSetCapability();
					ProjectSetCapability.ensureBackwardsCompatible(providerType, serializer);

					if(serializer != null) {
						for(final String ref : projects) {

							if(mainMonitor.isCanceled()) {
								return result;
							}

							try {
								IProgressMonitor monitor = new SubProgressMonitor(mainMonitor, 1);

								final String projectName = getProjectName(serializer, ref);

								mainMonitor.subTask(String.format("Importing %s...", projectName));

								final List<IProject> importedProjects = new LinkedList<IProject>();

								WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

									@Override
									protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
										try {
											IProject[] allProjects = serializer.addToWorkspace(new String[]{ ref }, context, monitor);

											if(allProjects != null) {
												importedProjects.addAll(Arrays.asList(allProjects));
											}
										} catch (Exception ex) {
											diagnostic.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, String.format("An error occurred when importing %s", projectName), ex));
										}
									}
								};

								operation.run(monitor);

								//Refresh the new projects
								for(IProject project : importedProjects) {
									if(project == null) {
										continue;
									}
									try {
										project.refreshLocal(IResource.DEPTH_INFINITE, null);
									} catch (CoreException ex) {
										Activator.log.error(ex); //Not directly related to the import.
									} catch (OperationCanceledException ex) {
										//Ignore: The refresh workspace is cancel
									}
								}

								newProjects.addAll(importedProjects);

							} catch (InterruptedException ex) {
								mainMonitor.setCanceled(true);
							} catch (Exception ex) {
								diagnostic.add(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "An error occurred when importing a project", ex));
							}
						}
					}
				}

				//try working sets
				IMemento[] sets = xmlMemento.getChildren("workingSets"); //$NON-NLS-1$
				IWorkingSetManager wsManager = TeamUIPlugin.getPlugin().getWorkbench().getWorkingSetManager();
				boolean replaceAll = false;
				boolean mergeAll = false;
				boolean skipAll = false;

				mainMonitor.setTaskName(String.format("Creating %s working sets...", nbWorkingSets));

				for(IMemento set : sets) {
					if(mainMonitor.isCanceled()) {
						return result;
					}
					mainMonitor.subTask(String.format("Working set %s", set.getString("label")));
					IWorkingSet newWs = wsManager.createWorkingSet(set);
					if(newWs != null) {
						IWorkingSet oldWs = wsManager.getWorkingSet(newWs.getName());
						if(oldWs == null) {
							wsManager.addWorkingSet(newWs);
						} else if(replaceAll) {
							replaceWorkingSet(wsManager, newWs, oldWs);
						} else if(mergeAll) {
							mergeWorkingSets(newWs, oldWs);
						} else if(!skipAll) {
							// a working set with the same name has been found
							String title = TeamUIMessages.ImportProjectSetDialog_duplicatedWorkingSet_title;
							String msg = NLS.bind(TeamUIMessages.ImportProjectSetDialog_duplicatedWorkingSet_message, newWs.getName());
							String[] buttons = new String[]{ TeamUIMessages.ImportProjectSetDialog_duplicatedWorkingSet_replace, TeamUIMessages.ImportProjectSetDialog_duplicatedWorkingSet_merge, TeamUIMessages.ImportProjectSetDialog_duplicatedWorkingSet_skip, IDialogConstants.CANCEL_LABEL };
							final AdviceDialog dialog = new AdviceDialog(shell, title, null, msg, MessageDialog.QUESTION, buttons, 0);

							shell.getDisplay().syncExec(new Runnable() {

								public void run() {
									dialog.open();
								}
							});

							switch(dialog.getReturnCode()) {
							case 0: // overwrite
								replaceWorkingSet(wsManager, newWs, oldWs);
								replaceAll = dialog.applyToAll;
								break;
							case 1: // combine
								mergeWorkingSets(newWs, oldWs);
								mergeAll = dialog.applyToAll;
								break;
							case 2: // skip
								skipAll = dialog.applyToAll;
								break;
							case 3: // cancel
							default:
								mainMonitor.setCanceled(true);
							}
						}
					}

					mainMonitor.worked(1);
				}
			}

			result.setImportedProjects(newProjects.toArray(new IProject[newProjects.size()]));

			return result;
		} catch (TeamException e) {
			throw new InvocationTargetException(e);
		}
	}

	private static Map<String, List<String>> filterProjects(XMLMemento xmlMemento, Shell shell) {
		Map<String, List<String>> providersToProjects = new LinkedHashMap<String, List<String>>();

		IMemento[] providers = xmlMemento.getChildren("provider"); //$NON-NLS-1$
		for(IMemento provider : providers) {
			IMemento[] projects = provider.getChildren("project"); //$NON-NLS-1$

			List<String> references = getReferences(providersToProjects, provider.getString("id")); //$NON-NLS-1$

			for(IMemento project : projects) {
				references.add(project.getString("reference")); //$NON-NLS-1$
			}
		}

		final FilterProjectsDialog dialog = new FilterProjectsDialog(shell, providersToProjects);
		shell.getDisplay().syncExec(new Runnable() {

			public void run() {
				dialog.open();
			}

		});

		if(dialog.getReturnCode() == Window.OK) {
			return dialog.getFilteredProjects();
		} else {
			return null;
		}

		//return providersToProjects;
	}

	private static List<String> getReferences(Map<String, List<String>> providersToReferences, String provider) {
		if(!providersToReferences.containsKey(provider)) {
			providersToReferences.put(provider, new LinkedList<String>());
		}
		return providersToReferences.get(provider);
	}

	private static String getProjectName(ProjectSetCapability serializer, String ref) {
		String projectName = serializer.getProject(ref);
		if(projectName == null) {
			int lastIndex = Math.max(ref.lastIndexOf('/'), Math.max(ref.lastIndexOf(','), ref.lastIndexOf('\\')));
			if(lastIndex == -1) {
				return ref;
			}
			return ref.substring(lastIndex + 1);
		}

		return projectName;
	}

	private static XMLMemento filenameToXMLMemento(String filename) throws InvocationTargetException {
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(filename), "UTF-8"); //$NON-NLS-1$
			return XMLMemento.createReadRoot(reader);
		} catch (UnsupportedEncodingException e) {
			throw new InvocationTargetException(e);
		} catch (FileNotFoundException e) {
			throw new InvocationTargetException(e);
		} catch (WorkbenchException e) {
			throw new InvocationTargetException(e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new InvocationTargetException(e);
				}
			}
		}
	}

	private static XMLMemento stringToXMLMemento(String stringContents) throws InvocationTargetException {
		StringReader reader = null;
		try {
			reader = new StringReader(stringContents);
			return XMLMemento.createReadRoot(reader);
		} catch (WorkbenchException e) {
			throw new InvocationTargetException(e);
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}

	/**
	 * Check if given file is a valid psf file
	 * 
	 * @param filename
	 * @return <code>true</code> is file is a valid psf file
	 */
	public static boolean isValidProjectSetFile(String filename) {
		try {
			return filenameToXMLMemento(filename).getString("version") != null; //$NON-NLS-1$
		} catch (InvocationTargetException e) {
			return false;
		}
	}

	/**
	 * Check if given string is a valid project set
	 * 
	 * @param psfContent
	 * @return <code>true</code> if psfContent is a valid project set
	 */
	public static boolean isValidProjectSetString(String psfContent) {
		if(psfContent == null) {
			return false;
		}
		try {
			return stringToXMLMemento(psfContent).getString("version") != null; //$NON-NLS-1$
		} catch (InvocationTargetException e) {
			return false;
		}
	}

	private static void mergeWorkingSets(IWorkingSet newWs, IWorkingSet oldWs) {
		IAdaptable[] oldElements = oldWs.getElements();
		IAdaptable[] newElements = newWs.getElements();

		Set<IAdaptable> combinedElements = new HashSet<IAdaptable>();
		combinedElements.addAll(Arrays.asList(oldElements));
		combinedElements.addAll(Arrays.asList(newElements));

		oldWs.setElements(combinedElements.toArray(new IAdaptable[0]));
	}

	private static void replaceWorkingSet(IWorkingSetManager wsManager, IWorkingSet newWs, IWorkingSet oldWs) {
		if(oldWs != null) {
			wsManager.removeWorkingSet(oldWs);
		}
		wsManager.addWorkingSet(newWs);
	}

	private static class AdviceDialog extends MessageDialog {

		boolean applyToAll;

		public AdviceDialog(Shell parentShell, String dialogTitle, Image dialogTitleImage, String dialogMessage, int dialogImageType, String[] dialogButtonLabels, int defaultIndex) {
			super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
		}

		@Override
		protected Control createCustomArea(Composite parent) {
			final Button checkBox = new Button(parent, SWT.CHECK);
			checkBox.setText(TeamUIMessages.ImportProjectSetDialog_duplicatedWorkingSet_applyToAll);
			checkBox.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					applyToAll = checkBox.getSelection();
				}
			});
			return checkBox;
		}
	}

}
