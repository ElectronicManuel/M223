package com.ubs.main.factory.components.textfield;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.ubs.main.factory.components.CustomComponent;
import com.ubs.main.factory.controller.FormOptions;
import com.ubs.main.factory.gui.AutoForm;

public class CustomTextField extends CustomComponent<JTextField> implements DocumentListener {
	
	public CustomTextField(FormOptions opts, AutoForm form) {
		super(opts, form);
		
		setComponent(new JTextField(((TextSettings)getOptions().getSettings()).getMaxLength()));
		getComponent().getDocument().addDocumentListener(this);
		
		verify();
	}
	
	@Override
	public Object getValue() {
		return getComponent().getText();
	}

	@Override
	public void clear() {
		getComponent().setText("");
	}

	@Override
	public boolean isNull() {
		return getComponent().getText().length() == 0;
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		verify();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		verify();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		verify();
	}

	@Override
	public boolean setValue(Object toSet) {
		String s = (String) toSet;
		getComponent().setText(s);
		return true;
	}

}
