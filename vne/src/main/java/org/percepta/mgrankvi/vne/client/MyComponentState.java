package org.percepta.mgrankvi.vne.client;

import java.util.LinkedList;
import java.util.List;

public class MyComponentState extends com.vaadin.shared.AbstractComponentState {

	// State can have both public variable and bean properties
	public String text = "MyComponent";
    public List<String> urls = new LinkedList<String>();

}