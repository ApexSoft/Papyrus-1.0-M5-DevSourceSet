/*****************************************************************************
 * Copyright (c) 2013 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Soyatec - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Gate;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OccurrenceSpecification;

/**
 * Helper class for determine the message connections. Both for connecting and reconnecting.
 * 
 * @author Jin Liu (jin.liu@soyatec.com)
 */
public class MessageConnectionHelper {

	public static boolean debug = false;

	private MessageConnectionHelper() {
	}

	public static boolean canReorientSource(Message message, Element newSource) {
		if(message == null || newSource == null) {
			return false;
		}
		Element target = null;
		MessageEnd receiveEvent = message.getReceiveEvent();
		if(receiveEvent instanceof OccurrenceSpecification) {
			EList<Lifeline> covereds = ((OccurrenceSpecification)receiveEvent).getCovereds();
			if(!covereds.isEmpty()) {
				target = covereds.get(0);
			}
		} else if(receiveEvent instanceof Gate) {
			target = ((Gate)receiveEvent).getOwner();
		}
		return canExist(message, newSource, target);
	}

	public static boolean canReorientTarget(Message message, Element newTarget) {
		if(message == null || newTarget == null) {
			return false;
		}
		Element source = null;
		MessageEnd sendEvent = message.getSendEvent();
		if(sendEvent instanceof OccurrenceSpecification) {
			EList<Lifeline> covereds = ((OccurrenceSpecification)sendEvent).getCovereds();
			if(!covereds.isEmpty()) {
				source = covereds.get(0);
			}
		} else if(sendEvent instanceof Gate) {
			source = ((Gate)sendEvent).getOwner();
		}
		return canExist(message, source, newTarget);
	}

	public static boolean canExist(Message message, Element source, Element target) {
		MessageSort messageSort = null;
		if(message != null) {
			messageSort = message.getMessageSort();
		}
		return canExist(message, messageSort, source, target);
	}

	public static boolean canExist(Message message, MessageSort messageSort, Element source, Element target) {
		if(debug) {
			print(messageSort, source, target);
		}
		if(MessageSort.ASYNCH_CALL_LITERAL == messageSort) {
			return canExistAsynchMessage(message, source, target);
		} else if(MessageSort.ASYNCH_SIGNAL_LITERAL == messageSort) {
			if(source == null) {
				return canExistFoundMessage(message, target);
			}
			if(target == null) {
				return canExistLostMessage(message, source);
			}
		} else if(MessageSort.SYNCH_CALL_LITERAL == messageSort) {
			return canExistSynchMessage(message, source, target);
		} else if(MessageSort.CREATE_MESSAGE_LITERAL == messageSort) {
			return canExistCreateMessage(message, source, target);
		} else if(MessageSort.DELETE_MESSAGE_LITERAL == messageSort) {
			return canExistDeleteMessage(message, source, target);
		} else if(MessageSort.REPLY_LITERAL == messageSort) {
			return canExistReplyMessage(message, source, target);
		}
		return false;
	}

	public static boolean canExist(MessageSort messageSort, Element source, Element target) {
		return canExist(null, messageSort, source, target);
	}

	private static void print(MessageSort messageSort, Element source, Element target) {
		StringBuffer buf = new StringBuffer();
		if(messageSort != null) {
			buf.append(messageSort.getName());
			buf.append("[");
		}
		buf.append("Source: ");
		if(source != null) {
			buf.append(source.eClass().getName());
			if(source instanceof NamedElement) {
				buf.append("(");
				buf.append(((NamedElement)source).getName());
				buf.append(")");
			}
		} else {
			buf.append("null");
		}
		buf.append(", Target: ");
		if(target != null) {
			buf.append(target.eClass().getName());
			if(target instanceof NamedElement) {
				buf.append("(");
				buf.append(((NamedElement)target).getName());
				buf.append(")");
			}
		} else {
			buf.append("null");
		}
		buf.append("]");
		System.out.println(new String(buf));
	}

	public static boolean canExistReplyMessage(Message message, Element source, Element target) {
		if(target instanceof Message) {
			return false;
		}
		if(source instanceof ExecutionSpecification && target instanceof Lifeline) {
			if(((ExecutionSpecification)source).getCovereds().contains(target)) {
				return false;
			}
		}
		if(target instanceof Gate) {
			Message ownMessage = ((Gate)target).getMessage();
			if(ownMessage == null) {
				return true;
			}
			return ownMessage == message;
		}
		return true;
	}

	public static boolean canExistDeleteMessage(Message message, Element source, Element target) {
		return true;
	}

	public static boolean canExistCreateMessage(Message message, Element source, Element target) {
		if(target != null) {
			if(false == target instanceof Lifeline) {
				return false;
			}
			if(target == source) {
				return false;
			}
		}
		return true;
	}

	public static boolean canExistSynchMessage(Message message, Element source, Element target) {
		if(source != null && !(source instanceof ExecutionSpecification || source instanceof Lifeline || source instanceof ExecutionOccurrenceSpecification)) {
			return false;
		}
		if(target != null && !(target instanceof ExecutionSpecification || target instanceof Lifeline || target instanceof ExecutionOccurrenceSpecification)) {
			return false;
		}
		return true;
	}

	public static boolean canExistLostMessage(Message message, Element source) {
		if(source instanceof Gate) {
			Message ownMessage = ((Gate)source).getMessage();
			if(ownMessage == null) {
				return true;
			}
			return ownMessage == message;
		}
		return true;
	}

	public static boolean canExistFoundMessage(Message message, Element target) {
		if(target instanceof Gate) {
			return message == null ? ((Gate)target).getMessage() == null : message == ((Gate)target).getMessage();
		}
		return true;
	}

	public static boolean canExistAsynchMessage(Message message, Element source, Element target) {
		if(target instanceof Message) {
			return false;
		}
		//Only available for ExecutionSpecification and Lifeline.
		if(target != null && !(target instanceof ExecutionSpecification || target instanceof Lifeline || target instanceof InteractionFragment || target instanceof MessageEnd)) {
			return false;
		}
		if(source instanceof Gate) {
			Message ownerMessage = ((Gate)source).getMessage();
			return ownerMessage == null ? true : ownerMessage == message;
		}
		if(target instanceof Gate) {
			Message ownerMessage = ((Gate)target).getMessage();
			return ownerMessage == null ? true : ownerMessage == message;
		}
		return true;
	}
}
