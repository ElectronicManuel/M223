package com.ubs.factory.components.radio;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.ubs.factory.components.CustomComponent;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.gui.AutoForm;

public class CustomRadioList extends CustomComponent<JPanel> implements ItemListener {
	
	private ButtonGroup group;
	private List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	
	public CustomRadioList(FormOptions opts, AutoForm form) {
		super(opts, form);
		setComponent(new JPanel());
		init();
		
		verify();
	}
	
	private void init() {
		group = new ButtonGroup();
		
		for(Object o : ((RadioSettings)getOptions().getSettings()).getObjects()) {
			JRadioButton b = new JRadioButton(o.toString());
			b.addItemListener(this);
			group.add(b);
			buttons.add(b);
			getComponent().add(b);
		}
	}
	
	@Override
	public Object getValue() {
		String selected = null;
		for(JRadioButton b : buttons) {
			if(b.isSelected()) selected = b.getText();
		}
		return selected;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		verify();
	}

	@Override
	public void clear() {
		group.clearSelection();
	}

	@Override
	public boolean isNull() {
		boolean nil = getValue() == null;
		return nil;
	}
	
	@Override
	public void setColor(Color c) {
		for(JRadioButton b : buttons) {
			b.setForeground(c);
		}
	}

	@Override
	public boolean setValue(Object toSet) {
		for(JRadioButton b : buttons) {
			if(b.getText().equals(toSet.toString())) {
				b.setSelected(true);
				return true;
			}
		}
		return false;
	}
	
}
