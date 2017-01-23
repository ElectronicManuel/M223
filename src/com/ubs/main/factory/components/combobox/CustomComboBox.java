package com.ubs.main.factory.components.combobox;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.ubs.main.factory.components.CustomComponent;
import com.ubs.main.factory.controller.FormOptions;
import com.ubs.main.factory.gui.AutoForm;

@SuppressWarnings("rawtypes")
public class CustomComboBox extends CustomComponent<JComboBox> implements ItemListener {
	
	@SuppressWarnings("unchecked")
	public CustomComboBox(FormOptions opts, AutoForm form) {
		super(opts, form);
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("Bitte auswählen");
		
		for(Object o : ((ComboSettings)options.getSettings()).getObjects()) {
			list.add(o);
		}
		
		setComponent(new JComboBox(list.toArray()));
		
		getComponent().addItemListener(this);
		
		verify();
	}

	@Override
	public Object getValue() {
		return getComponent().getSelectedItem();
	}

	@Override
	public void clear() {
		getComponent().setSelectedIndex(0);
	}

	@Override
	public boolean isNull() {
		boolean nil = getComponent().getSelectedIndex() == 0;
		return nil;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		verify();
	}
	
	@Override
	public void setColor(Color c) {
		getComponent().setForeground(c);
	}

	@Override
	public boolean setValue(Object toSet) {
		getComponent().setSelectedItem(toSet);
		return true;
	}

}
