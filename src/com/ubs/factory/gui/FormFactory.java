package com.ubs.factory.gui;

import java.util.Map;

public class FormFactory {
	
	public AutoForm produceForm(Map<String, InputType> fields) {
		AutoForm auto = new AutoForm(fields);
		return auto;
	}

}
