package com.ubs.factory.components.radio;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.ubs.factory.components.CustomComponent;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.gui.AutoForm;

@SuppressWarnings("serial")
public class CustomRadioList extends JPanel implements CustomComponent, ItemListener {
	
	private FormOptions opts;
	private RadioSettings set;
	private AutoForm form;
	//private Validator validator;
	private boolean valid = true;
	
	private ButtonGroup group;
	private List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	
	public CustomRadioList(FormOptions opts, AutoForm form, Object[] objects) {
		super();
		this.form = form;
		this.opts = opts;
		//validator = opts.getValidator();
		set = (RadioSettings) opts.getSettings();
		
		init();
		
		setVerifier();
	}
	
	private void init() {
		group = new ButtonGroup();
		
		for(Object o : set.getObjects()) {
			JRadioButton b = new JRadioButton(o.toString());
			b.addItemListener(this);
			group.add(b);
			buttons.add(b);
			add(b);
		}
	}
	
	private void setVerifier() {
	}

	@Override
	public JComponent getComponent() {
		return this;
	}
	
	@Override
	public boolean isValid() {
		boolean oldValid = valid;
		try {
			if(opts.isRequired() && getValue() == null) {
				valid = false;
			}
			else {
				valid = true;
			}
		}
		catch(NullPointerException ex) {
			valid = false;
		}
		
		if(oldValid != valid) {
			form.valueChanged();
		}
		
		return valid;
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
		isValid();
	}

	@Override
	public void clear() {
		group.clearSelection();
	}

}
