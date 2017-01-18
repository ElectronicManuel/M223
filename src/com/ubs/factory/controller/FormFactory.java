package com.ubs.factory.controller;

import java.awt.event.ActionListener;
import java.util.List;

import com.ubs.factory.components.CustomComponent;
import com.ubs.factory.components.combobox.ComboSettings;
import com.ubs.factory.components.combobox.CustomComboBox;
import com.ubs.factory.components.radio.CustomRadioList;
import com.ubs.factory.components.radio.RadioSettings;
import com.ubs.factory.components.textfield.CustomTextField;
import com.ubs.factory.gui.AutoForm;

public class FormFactory {
	
	public static AutoForm produceForm(List<FormOptions> fields, String title, ActionListener submitListener) {
		AutoForm auto = new AutoForm(fields, title, submitListener);
		return auto;
	}
	
	public static CustomComponent getComponent(FormOptions options, AutoForm form) {
		if(options.getType() == InputType.TEXTFIELD) {
			return new CustomTextField(options, form);
		}
		else if(options.getType() == InputType.COMBOBOX) {
			return new CustomComboBox(options, form, ((ComboSettings)options.getSettings()).getObjects());
		}
		else if(options.getType() == InputType.RADIO) {
			return new CustomRadioList(options, form, ((RadioSettings)options.getSettings()).getObjects());
		}
		return null;
	}

}
