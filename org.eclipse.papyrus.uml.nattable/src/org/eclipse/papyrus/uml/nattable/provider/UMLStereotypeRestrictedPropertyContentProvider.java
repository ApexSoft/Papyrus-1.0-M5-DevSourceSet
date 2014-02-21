/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Juan Cadavid (CEA LIST) juan.cadavid@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.nattable.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.nattable.utils.AxisUtils;
import org.eclipse.papyrus.infra.widgets.providers.IRestrictedContentProvider;
import org.eclipse.papyrus.uml.nattable.manager.axis.UMLStereotypePropertyAxisManager;
import org.eclipse.papyrus.uml.tools.providers.UMLStereotypePropertyContentProvider;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Stereotype;

/**
 * Restricted Content Provider for the properties of Stereotypes
 * 
 * @author JC236769
 * 
 */
//TODO we maybe should use the AbstractRestrictedContentProvider
//of extends a  generic uml content provider?
public class UMLStereotypeRestrictedPropertyContentProvider extends UMLStereotypePropertyContentProvider implements IRestrictedContentProvider {

	/**
	 * we show the value according to the table content
	 * if <code>true</code> -> the properties of stereotypes which are not applied on elements visible on the table aren't returned
	 */
	private boolean isRestricted;

	/**
	 * the stereotype axis manager
	 */
	private UMLStereotypePropertyAxisManager umlStereotypePropertyManager;

	/**
	 * the list of the allowed contents (Profile and Stereotype) when we are in restricted mode
	 */
	private Collection<Element> restrictedElements;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param umlStereotypePropertyManager
	 *        the UML stereotype axis manager
	 * @param isRestricted
	 *        restrict mode
	 */
	public UMLStereotypeRestrictedPropertyContentProvider(final UMLStereotypePropertyAxisManager umlStereotypePropertyManager, final boolean isRestricted) {
		super();
		setIgnoreBaseProperty(true);
		//		setIgnoreInheritedElements(true);
		this.isRestricted = isRestricted;
		this.umlStereotypePropertyManager = umlStereotypePropertyManager;
		init();
	}

	/**
	 * inits the fields of the table
	 */
	protected void init() {
		setProfiles(new ArrayList<Profile>(getAllAvailableProfiles()));
		this.restrictedElements = calculusOfAllowedElements();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.tools.providers.UMLStereotypePropertyContentProvider#getElements()
	 * 
	 * @return
	 */
	@Override
	public Object[] getElements() {
		if(this.isRestricted) {
			final boolean isColumnManager = umlStereotypePropertyManager.isUsedAsColumnManager();
			final List<?> elements;
			if(isColumnManager) {
				elements = this.umlStereotypePropertyManager.getTableManager().getRowElementsList();
			} else {
				elements = this.umlStereotypePropertyManager.getTableManager().getColumnElementsList();
			}
			if(elements.isEmpty()) {
				return new Object[0];
			} else {
				List<Object> values = Arrays.asList(super.getElements());
				values = new ArrayList<Object>(values);
				values.retainAll(restrictedElements);
				return values.toArray();
			}
		} else {
			return super.getElements();
		}
	}

	/**
	 * 
	 * @return
	 *         the list of the allowed profiles and stereotypes
	 */
	protected Collection<Element> calculusOfAllowedElements() {
		final Collection<Element> restrictedElements = new HashSet<Element>();
		//we are restricted so we show only the elements available for the current contents of the table
		final Set<Stereotype> restrictedStereotypes = new HashSet<Stereotype>();
		final boolean isColumnManager = umlStereotypePropertyManager.isUsedAsColumnManager();
		final List<?> elementsList;
		if(isColumnManager) {
			elementsList = this.umlStereotypePropertyManager.getTableManager().getRowElementsList();
		} else {
			elementsList = this.umlStereotypePropertyManager.getTableManager().getColumnElementsList();
		}
		for(Object object : elementsList) {
			Object representedElement = AxisUtils.getRepresentedElement(object);
			if(representedElement instanceof Element) {
				restrictedStereotypes.addAll(((Element)representedElement).getAppliedStereotypes());
			}
		}

		final Collection<Namespace> stereotypesNamespace = new HashSet<Namespace>();
		stereotypesNamespace.addAll(restrictedStereotypes);
		for(Stereotype stereotype : restrictedStereotypes) {
			stereotypesNamespace.addAll(stereotype.allNamespaces());
		}

		restrictedElements.addAll(restrictedStereotypes);
		restrictedElements.addAll(stereotypesNamespace);
		return restrictedElements;
	}

	protected Profile getTopRootProfile(final Profile profile, final Collection<Profile> availableProfiles) {
		EObject container = profile.eContainer();
		Profile topProfile = profile;
		while(container != null) {
			if(container instanceof Profile) {
				if(availableProfiles.contains(container)) {
					topProfile = (Profile)container;
				}
			}
			container = container.eContainer();
		}

		return topProfile;
	}

	/**
	 * 
	 * @return
	 *         the list of all the profiles applied somewhere in the model
	 */
	protected Set<Profile> getAllAvailableProfiles() {
		final Set<Profile> coll = new HashSet<Profile>();
		final EObject context = umlStereotypePropertyManager.getTableManager().getTableContext();
		if(context instanceof Element) {
			final EObject container = EcoreUtil.getRootContainer(context);
			if(container instanceof Package) {
				//get the list of all profile applied in the model
				final Collection<Profile> allAppliedProfiles = getAppliedProfilesInWholePackage((Package)container);


				for(Profile profile : allAppliedProfiles) {
					coll.add(getTopRootProfile(profile, allAppliedProfiles));
				}


				//				//				coll.addAll(allAppliedProfiles);
				//				//for each of these profiles, we look for the top profile which is applied in the model
				//				//for example, if only SysML::Blocks is applied, we don't want to display SysML as root profile, but only SysML::Blocks 
				//				for(final Profile current : allAppliedProfiles) {
				//					Profile topPackage = null;
				//					for(final Package pack : current.allOwningPackages()) {
				//						if(pack instanceof Profile && allAppliedProfiles.contains(pack)) {
				//							topPackage = (Profile)pack;
				//							break;
				//						}
				//					}
				//					if(topPackage == null) {
				//						topPackage = current;
				//					}
				//					coll.add(topPackage);
				//				}
			}
		}
		return coll;
	}


	/**
	 * 
	 * @param pack
	 *        a package
	 * @return
	 *         a collection with all profiles applied in the package and its subpackage
	 *         //TODO : should be moved in an upper plugin
	 */
	private static final Collection<Profile> getAppliedProfilesInWholePackage(final Package pack) {
		final Collection<Profile> appliedProfiles = new HashSet<Profile>();
		final List<ProfileApplication> result = getInstancesFilteredByType(pack, ProfileApplication.class, null);
		for(ProfileApplication profileApplication : result) {
			if(EcoreUtil.getRootContainer((((ProfileApplication)profileApplication).getApplyingPackage())) == pack) {//restriction to avoid to find profile application from an imported model
				appliedProfiles.add(profileApplication.getAppliedProfile());
			}
		}
		return appliedProfiles;
	}

	/**
	 * Retrieve an list of all instances in the model that are instances of
	 * the java.lang.Class metaType or with a stereotype applied
	 * 
	 * @param <T>
	 * 
	 * @param metaType
	 *        selected classes
	 * @param model
	 *        to check
	 * @param appliedStereotype
	 *        may be null, metatype is ignored if not null
	 * @return a list containing the selected instances
	 */
	//TODO : replace me by ElementUtils.getInstance...
	@SuppressWarnings("unchecked")
	private static final <T extends EObject> List<T> getInstancesFilteredByType(final Package topPackage, final java.lang.Class<T> metaType, final Stereotype appliedStereotype) {
		// retrieve parent element
		// Package topPackage = Util.topPackage(element);
		// Assert.isNotNull(topPackage,
		// "Top package should not be null for element " + element);
		Iterator<EObject> iter = topPackage.eAllContents();
		List<T> filteredElements = new ArrayList<T>();

		while(iter.hasNext()) {
			EObject currentElt = iter.next();

			// If currentElt is an ElementImport, it is replaced by the imported
			// Element.
			if(currentElt instanceof ElementImport) {
				ElementImport elementImport = (ElementImport)currentElt;
				currentElt = elementImport.getImportedElement();
			}

			/* package imports treatment */
			else if(currentElt instanceof PackageImport) {
				Iterator<EObject> piIter = ((PackageImport)currentElt).getImportedPackage().eAllContents();
				while(piIter.hasNext()) {
					EObject piCurrentElt = piIter.next();
					if(piCurrentElt instanceof Element) {
						if(appliedStereotype != null) {

							Iterator<Stereotype> appStIter = ((Element)piCurrentElt).getAppliedStereotypes().iterator();
							while(appStIter.hasNext()) {
								Stereotype currentSt = (Stereotype)appStIter.next();

								if(currentSt.conformsTo(appliedStereotype)) {
									filteredElements.add((T)piCurrentElt);
								}
							}

						} else { // if (appliedStereotype == null)
							if(metaType.isInstance(piCurrentElt)) {
								filteredElements.add((T)piCurrentElt);
							}

							/** add imported meta elements */
							else if(piCurrentElt instanceof ElementImport) {
								Iterator<EObject> eIter = ((ElementImport)piCurrentElt).getImportedElement().eAllContents();
								while(eIter.hasNext()) {
									EObject currentEIelt = eIter.next();
									if(metaType.isInstance(currentEIelt))
										filteredElements.add((T)currentEIelt);
								}
							}
						}
					}

				}
			}

			// Filtering elements
			if(currentElt instanceof Element) {

				if(appliedStereotype != null) {

					Iterator<Stereotype> appStIter = ((Element)currentElt).getAppliedStereotypes().iterator();
					while(appStIter.hasNext()) {
						Stereotype currentSt = (Stereotype)appStIter.next();

						if(currentSt.conformsTo(appliedStereotype)) {
							filteredElements.add((T)currentElt);
						}
					}

				} else { // if (appliedStereotype == null)
					if(metaType.isInstance(currentElt)) {
						filteredElements.add((T)currentElt);
					}

					/** add imported meta elements */
					else if(currentElt instanceof ElementImport) {
						Iterator<EObject> eIter = ((ElementImport)currentElt).getImportedElement().eAllContents();
						while(eIter.hasNext()) {
							EObject currentEIelt = eIter.next();
							if(metaType.isInstance(currentEIelt))
								filteredElements.add((T)currentEIelt);
						}
					}
				}
			}
		}

		return filteredElements;
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.widgets.providers.IRestrictedContentProvider#setRestriction(boolean)
	 * 
	 * @param isRestricted
	 *        Setter for {@link #isRestricted}
	 */
	public void setRestriction(boolean isRestricted) {
		this.isRestricted = isRestricted;
	}


	/**
	 * 
	 * @see org.eclipse.papyrus.uml.tools.providers.UMLStereotypePropertyContentProvider#getChildren(java.lang.Object)
	 * 
	 * @param parentElement
	 * @return
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if(!(parentElement instanceof Package || parentElement instanceof Stereotype)) {
			return new Object[0];
		}
		final Object[] children = super.getChildren(parentElement);
		if(!isRestricted || !(parentElement instanceof Package)) {
			return children;
		} else {
			//we are restricted so we show only the elements available for the current contents of the table
			final Collection<Object> returnedValues = new ArrayList<Object>();
			returnedValues.addAll(Arrays.asList(children));
			returnedValues.retainAll(this.restrictedElements);
			return returnedValues.toArray();
		}
	}


	/**
	 * 
	 * @see org.eclipse.papyrus.uml.tools.providers.UMLStereotypePropertyContentProvider#dispose()
	 * 
	 */
	@Override
	public void dispose() {
		super.dispose();
		this.umlStereotypePropertyManager = null;
		this.restrictedElements.clear();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.infra.widgets.providers.IRestrictedContentProvider#isRestricted()
	 * 
	 * @return
	 */
	public boolean isRestricted() {
		return isRestricted;
	}

}
