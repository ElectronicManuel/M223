package com.ubs.main.factory.controller;

import java.awt.event.ActionListener;
import java.util.List;

import com.ubs.main.factory.components.CustomComponent;
import com.ubs.main.factory.components.combobox.CustomComboBox;
import com.ubs.main.factory.components.date.CustomDate;
import com.ubs.main.factory.components.radio.CustomRadioList;
import com.ubs.main.factory.components.textfield.CustomTextField;
import com.ubs.main.factory.gui.AutoForm;

public class FormFactory {
	
	public static AutoForm produceForm(List<FormOptions> fields, String title, ActionListener submitListener) {
		AutoForm auto = new AutoForm(fields, title, submitListener);
		return auto;
	}
	
	@SuppressWarnings("rawtypes")
	public static CustomComponent getComponent(FormOptions options, AutoForm form) {
		if(options.getType() == InputType.TEXTFIELD) {
			return new CustomTextField(options, form);
		}
		else if(options.getType() == InputType.COMBOBOX) {
			return new CustomComboBox(options, form);
		}
		else if(options.getType() == InputType.RADIO) {
			return new CustomRadioList(options, form);
		}
		else if(options.getType() == InputType.DATE) {
			return new CustomDate(options, form);
		}
		return null;
	}

}
