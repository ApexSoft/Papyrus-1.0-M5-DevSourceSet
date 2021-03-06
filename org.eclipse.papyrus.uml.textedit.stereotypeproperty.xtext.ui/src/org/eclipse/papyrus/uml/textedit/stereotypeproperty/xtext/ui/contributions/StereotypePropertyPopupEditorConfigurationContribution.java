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
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.textedit.stereotypeproperty.xtext.ui.contributions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.papyrus.uml.alf.alf.BOOLEAN_LITERAL;
import org.eclipse.papyrus.uml.alf.alf.INTEGER_LITERAL;
import org.eclipse.papyrus.uml.alf.alf.NameExpression;
import org.eclipse.papyrus.uml.alf.alf.STRING_LITERAL;
import org.eclipse.papyrus.uml.profile.structure.AppliedStereotypeProperty;
import org.eclipse.papyrus.uml.textedit.stereotypeproperty.xtext.AppliedStereotypePropertyEditorUtil;
import org.eclipse.papyrus.uml.textedit.stereotypeproperty.xtext.ui.internal.AppliedStereotypePropertyActivator;
import org.eclipse.papyrus.uml.xtext.integration.DefaultXtextDirectEditorConfiguration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;

import com.google.inject.Injector;

public class StereotypePropertyPopupEditorConfigurationContribution extends DefaultXtextDirectEditorConfiguration {

	@Override
	public Injector getInjector() {
		return AppliedStereotypePropertyActivator
				.getInstance()
				.getInjector(
						AppliedStereotypePropertyActivator.ORG_ECLIPSE_PAPYRUS_UML_TEXTEDIT_STEREOTYPEPROPERTY_XTEXT_APPLIEDSTEREOTYPEPROPERTY);
	}

	@Override
	protected ICommand getParseCommand(EObject umlObject, EObject xtextObject) {
		CompositeCommand command = new CompositeCommand("");
		final AppliedStereotypeProperty appliedStereotypeProperty = (AppliedStereotypeProperty) umlObject;
		Object[] result = AppliedStereotypePropertyEditorUtil.getPossibleElements(appliedStereotypeProperty);
		// ref element stereotype application
		if (appliedStereotypeProperty.getStereotypeProperty().getType().eClass().getName().equals("Stereotype")) {
			command.add(reconcileRefToStereotypeApp(appliedStereotypeProperty, xtextObject, result));
		}

		// int
		if (appliedStereotypeProperty.getStereotypeProperty().getType().getName().equals("Integer")) {
			command.add(reconcileInteger(appliedStereotypeProperty, xtextObject));
		}
		// boolean
		if (appliedStereotypeProperty.getStereotypeProperty().getType().getName().equals("Boolean")) {
			command.add(reconcileBoolean(appliedStereotypeProperty, xtextObject));
		}
		// string
		if (appliedStereotypeProperty.getStereotypeProperty().getType().getName().equals("String")) {
			command.add(reconcileString(appliedStereotypeProperty, xtextObject));
		}
		// dataType
		if (appliedStereotypeProperty.getStereotypeProperty().getType().eClass().getName().equals("DataType")) {
			command.add(reconcileString(appliedStereotypeProperty, xtextObject));
		}
		// primitiveType
		if (appliedStereotypeProperty.getStereotypeProperty().getType().eClass().getName().equals("PrimitiveType")) {
			command.add(reconcileString(appliedStereotypeProperty, xtextObject));
		}
		if (appliedStereotypeProperty.getStereotypeProperty().getType() instanceof Enumeration) {
			command.add(reconcileEnumerationLiteral(appliedStereotypeProperty, xtextObject, result));
		}
		// ref element
		if (appliedStereotypeProperty.getStereotypeProperty().getType() instanceof Element) {
			command.add(reconcileRefToElement(appliedStereotypeProperty, xtextObject, result));
		}
		return command;
	}

	/**
	 * this method is used to reconcile Enumeration literal with application of
	 * the stereotype
	 * 
	 * @param appliedStereotypeProperty
	 *            the application of stereotype
	 * @param xtextObject
	 *            the AST of the grammar
	 * @param possibleElement
	 *            list of possible elements that can be used
	 */
	protected ICommand reconcileEnumerationLiteral(final AppliedStereotypeProperty appliedStereotypeProperty,
			EObject xtextObject, Object[] possibleElement) {
		ArrayList<NameExpression> eObjects = getAllElementRef(xtextObject);

		// cardinality 1
		if (appliedStereotypeProperty.getStereotypeProperty().getUpper() == 1) {
			if (eObjects.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, null);
			} else {
				NameExpression nameExpression = eObjects.get(0);
				String id = nameExpression.getId();
				// EObject
				// foundStereotypeApplication=AppliedStereotypePropertyEditorUtil.getNamedElementFor(nameExpression,
				// result);
				EObject foundStereotypeApplication = null;
				// look for object
				for (int i = 0; i < possibleElement.length; i++) {
					if (possibleElement[i] instanceof EEnumLiteral
							&& ((EEnumLiteral) possibleElement[i]).getName().equals(id)) {
						foundStereotypeApplication = (EEnumLiteral) possibleElement[i];
					}
					if (possibleElement[i] instanceof Enumerator
							&& ((Enumerator) possibleElement[i]).getName().equals(id)) {
						return createUpdateCommand(appliedStereotypeProperty, possibleElement[i]);
					}
				}
				if (foundStereotypeApplication != null) {
					return createUpdateCommand(appliedStereotypeProperty, foundStereotypeApplication);
				}
			}
		}
		// cardinality *
		else {
			if (eObjects.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, new ArrayList());
			} else {
				// iterate on NameExpression
				ArrayList<Object> stereotypeApplicationList = new ArrayList<Object>();
				Iterator<NameExpression> iterator = eObjects.iterator();
				while (iterator.hasNext()) {
					NameExpression nameExpression = iterator.next();
					String id = nameExpression.getId();
					Object foundStereotypeApplication = null;
					// look for object
					for (int i = 0; i < possibleElement.length; i++) {
						if (possibleElement[i] instanceof EEnumLiteral
								&& ((EEnumLiteral) possibleElement[i]).getName().equals(id)) {
							foundStereotypeApplication = (EEnumLiteral) possibleElement[i];
						}
						if (possibleElement[i] instanceof Enumerator
								&& ((Enumerator) possibleElement[i]).getName().equals(id)) {
							foundStereotypeApplication = (Enumerator) possibleElement[i];
						}
					}

					if (foundStereotypeApplication != null) {
						stereotypeApplicationList.add(foundStereotypeApplication);
					}
				}
				return createUpdateCommand(appliedStereotypeProperty, stereotypeApplicationList);
			}
		}
		return null;
	}

	/**
	 * this method is used to reconcile references to Stereotype Application
	 * with the current application of the stereotype
	 * 
	 * @param appliedStereotypeProperty
	 *            the application of stereotype
	 * @param xtextObject
	 *            the AST of the grammar
	 * @param possibleElement
	 *            list of possible elements that can be used
	 */
	@SuppressWarnings("rawtypes")
	protected ICommand reconcileRefToStereotypeApp(final AppliedStereotypeProperty appliedStereotypeProperty,
			EObject xtextObject, Object[] result) {
		ArrayList<NameExpression> eObjects = getAllElementRef(xtextObject);

		// cardinality 1
		if (appliedStereotypeProperty.getStereotypeProperty().getUpper() == 1) {
			if (eObjects.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, null);
			} else {
				NameExpression nameExpression = eObjects.get(0);
				EObject foundStereotypeApplication = AppliedStereotypePropertyEditorUtil.getApplicationStereotypeFor(
						nameExpression, result);
				return createUpdateCommand(appliedStereotypeProperty, foundStereotypeApplication);
			}

		}
		// cardinality *
		else {
			if (eObjects.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, new ArrayList());
			} else {
				// iterate on NameExpression
				ArrayList<EObject> stereotypeApplicationList = new ArrayList<EObject>();
				Iterator<NameExpression> iterator = eObjects.iterator();
				while (iterator.hasNext()) {
					NameExpression nameExpression = iterator.next();
					EObject foundStereotypeApplication = AppliedStereotypePropertyEditorUtil
							.getApplicationStereotypeFor(nameExpression, result);
					if (foundStereotypeApplication != null) {
						stereotypeApplicationList.add(foundStereotypeApplication);
					}
				}
				return createUpdateCommand(appliedStereotypeProperty, stereotypeApplicationList);
			}

		}
	}

	/**
	 * this method is used to reconcile integer with the current application of
	 * the stereotype
	 * 
	 * @param appliedStereotypeProperty
	 *            the application of stereotype
	 * @param xtextObject
	 *            the AST of the grammar
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ICommand reconcileInteger(final AppliedStereotypeProperty appliedStereotypeProperty, EObject xtextObject) {
		ArrayList<INTEGER_LITERAL> intList = AppliedStereotypePropertyEditorUtil.get_INTEGER(xtextObject);
		// cardinality 1
		if (appliedStereotypeProperty.getStereotypeProperty().getUpper() == 1) {
			if (intList.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, null);
			} else {
				INTEGER_LITERAL theint = intList.get(0);
				Integer value = new Integer(theint.getValue());
				return createUpdateCommand(appliedStereotypeProperty, value.intValue());
			}
		}
		// cardinality *
		else {
			if (intList.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, new ArrayList());
			} else {
				// iterate on NameExpression
				ArrayList resultList = new ArrayList();
				Iterator<INTEGER_LITERAL> iterator = intList.iterator();
				while (iterator.hasNext()) {
					INTEGER_LITERAL theint = iterator.next();
					Integer value = new Integer(theint.getValue());
					resultList.add(value.intValue());
				}
				return createUpdateCommand(appliedStereotypeProperty, resultList);
			}
		}
	}

	/**
	 * this method is used to reconcile boolean with the current application of
	 * the stereotype
	 * 
	 * @param appliedStereotypeProperty
	 *            the application of stereotype
	 * @param xtextObject
	 *            the AST of the grammar
	 */

	@SuppressWarnings("rawtypes")
	protected ICommand reconcileBoolean(final AppliedStereotypeProperty appliedStereotypeProperty, EObject xtextObject) {
		ArrayList<BOOLEAN_LITERAL> booleanList = AppliedStereotypePropertyEditorUtil.get_BOOLEAN(xtextObject);
		// cardinality 1
		if (appliedStereotypeProperty.getStereotypeProperty().getUpper() == 1) {
			if (booleanList.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, null);
			} else {
				BOOLEAN_LITERAL theboolean = booleanList.get(0);
				Boolean value = new Boolean(theboolean.getValue().toString());
				return createUpdateCommand(appliedStereotypeProperty, value.booleanValue());
			}
		}
		// cardinality *
		else {
			if (booleanList.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, new ArrayList());
			} else {
				// iterate on NameExpression
				ArrayList<Boolean> resultList = new ArrayList<Boolean>();
				Iterator<BOOLEAN_LITERAL> iterator = booleanList.iterator();
				while (iterator.hasNext()) {
					BOOLEAN_LITERAL theboolean = iterator.next();
					Boolean value = new Boolean(theboolean.getValue().toString());
					resultList.add(value.booleanValue());
				}
				return createUpdateCommand(appliedStereotypeProperty, resultList);
			}
		}
	}

	/**
	 * this method is used to reconcile references element with the current
	 * application of the stereotype
	 * 
	 * @param appliedStereotypeProperty
	 *            the application of stereotype
	 * @param xtextObject
	 *            the AST of the grammar
	 * @param possibleElement
	 *            list of possible elements that can be used
	 */
	@SuppressWarnings("rawtypes")
	protected ICommand reconcileRefToElement(final AppliedStereotypeProperty appliedStereotypeProperty,
			EObject xtextObject, Object[] result) {
		ArrayList<NameExpression> eObjects = getAllElementRef(xtextObject);

		// cardinality 1
		if (appliedStereotypeProperty.getStereotypeProperty().getUpper() == 1) {
			if (eObjects.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, null);
			} else {
				NameExpression nameExpression = eObjects.get(0);
				EObject foundStereotypeApplication = AppliedStereotypePropertyEditorUtil.getNamedElementFor(
						nameExpression, result);
				return createUpdateCommand(appliedStereotypeProperty, foundStereotypeApplication);
			}

		}
		// cardinality *
		else {
			if (eObjects.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, new ArrayList());
			} else {
				// iterate on NameExpression
				ArrayList<EObject> stereotypeApplicationList = new ArrayList<EObject>();
				Iterator<NameExpression> iterator = eObjects.iterator();
				while (iterator.hasNext()) {
					NameExpression nameExpression = iterator.next();
					EObject foundStereotypeApplication = AppliedStereotypePropertyEditorUtil.getNamedElementFor(
							nameExpression, result);
					if (foundStereotypeApplication != null) {
						stereotypeApplicationList.add(foundStereotypeApplication);
					}
				}
				return createUpdateCommand(appliedStereotypeProperty, stereotypeApplicationList);
			}

		}
	}

	/**
	 * this method is used to reconcile Strings with the current application of
	 * the stereotype
	 * 
	 * @param appliedStereotypeProperty
	 *            the application of stereotype
	 * @param xtextObject
	 *            the AST of the grammar
	 */
	@SuppressWarnings("rawtypes")
	protected ICommand reconcileString(final AppliedStereotypeProperty appliedStereotypeProperty, EObject xtextObject) {
		ArrayList<STRING_LITERAL> theStringList = AppliedStereotypePropertyEditorUtil.get_STRING(xtextObject);
		// cardinality 1
		if (appliedStereotypeProperty.getStereotypeProperty().getUpper() == 1) {
			if (theStringList.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, null);
			} else {
				STRING_LITERAL theString = theStringList.get(0);
				return createUpdateCommand(appliedStereotypeProperty, theString.getValue());
			}
		}
		// cardinality *
		else {
			if (theStringList.size() == 0) {
				return createUpdateCommand(appliedStereotypeProperty, new ArrayList());
			} else {
				// iterate on NameExpression
				ArrayList<String> resultList = new ArrayList<String>();
				Iterator<STRING_LITERAL> iterator = theStringList.iterator();
				while (iterator.hasNext()) {
					STRING_LITERAL theString = iterator.next();
					resultList.add(theString.getValue());
				}
				return createUpdateCommand(appliedStereotypeProperty, resultList);
			}
		}
	}

	protected ICommand createUpdateCommand(final AppliedStereotypeProperty appliedStereotypeProperty, final Object value) {
		AbstractTransactionalCommand setValueCommand = new AbstractTransactionalCommand(
				TransactionUtil.getEditingDomain(appliedStereotypeProperty), "", Collections.EMPTY_LIST) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				appliedStereotypeProperty.getBaseElement().setValue(appliedStereotypeProperty.getStereotype(),
						appliedStereotypeProperty.getStereotypeProperty().getName(), value);
				return CommandResult.newOKCommandResult();

			}
		};
		return setValueCommand;

	}

	private ArrayList<NameExpression> getAllElementRef(EObject xtextObject) {
		ArrayList<NameExpression> result = new ArrayList<NameExpression>();
		TreeIterator<EObject> iterator = xtextObject.eAllContents();
		while (iterator.hasNext()) {
			EObject eObject = iterator.next();
			if (eObject instanceof NameExpression) {
				result.add((NameExpression) eObject);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.papyrus.infra.gmfdiag.xtext.glue.PopupEditorConfiguration
	 * #getTextToEdit(java.lang.Object)
	 */
	@Override
	public String getTextToEdit(Object editedObject) {
		if (editedObject instanceof AppliedStereotypeProperty) {
			return AppliedStereotypePropertyEditorUtil.getLabel((AppliedStereotypeProperty) editedObject).trim();
			// TODO: default values not supported by the grammar
			// TODO: either complete the grammar, or use another label provider
		}
		return "<UNDEFINED>";
	}

}
