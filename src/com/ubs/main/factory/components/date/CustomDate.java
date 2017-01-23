package com.ubs.main.factory.components.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.ubs.main.factory.components.CustomComponent;
import com.ubs.main.factory.controller.FormOptions;
import com.ubs.main.factory.gui.AutoForm;

public class CustomDate extends CustomComponent<JTextField> implements DocumentListener {
	
	private SimpleDateFormat format;

	public CustomDate(FormOptions options, AutoForm form) {
		super(options, form);
		format = new SimpleDateFormat("dd.MM.yyyy");
		setComponent(new JTextField());
		getComponent().getDocument().addDocumentListener(this);
		getComponent().setColumns(20);
		
		verify();
	}

	@Override
	public boolean isNull() {
		boolean nil = getComponent().getText().length() == 0;
		return nil;
	}

	@Override
	public Object getValue() {
		Date toReturn = null;
		try {
			toReturn = format.parse(getComponent().getText());
		} catch (ParseException e) {
		}
		
		return toReturn;
	}
	
	@Override
	public boolean isValidType() {
		boolean val = false;
		
		val = getValue() != null;
		
		return val;
	}

	@Override
	public void clear() {
		getComponent().setText("");
	}

	@Override
	public boolean setValue(Object toSet) {
		if(toSet instanceof Date) {
			Date d = (Date)toSet;
			getComponent().setText(format.format(d));
			return true;
		}
		else {
			return false;
		}
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

}
