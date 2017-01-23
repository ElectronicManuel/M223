package com.ubs.main.factory.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.ubs.main.factory.Design;
import com.ubs.main.factory.components.CustomComponent;
import com.ubs.main.factory.controller.FormFactory;
import com.ubs.main.factory.controller.FormOptions;

@SuppressWarnings({ "rawtypes", "serial" })
public class AutoForm extends JPanel {
	
	private String title;
	
	private List<FormOptions> fields;
	private Map<String, CustomComponent> components = new LinkedHashMap<String, CustomComponent>();
	private ActionListener submitListener;
	private JButton submit;
	
	public AutoForm(List<FormOptions> fields, String title, ActionListener submitListener) {
		this.fields = fields;
		this.title = title;
		this.submitListener = submitListener;
		init();
	}
	
	private void init() {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}
		
		// Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		
		// Components
		int i = 0;
		
		c.gridy = i;
		c.gridx = 0;
		
		c.gridwidth = 2;
		
		JLabel titleLabel = new JLabel(title);
		Font f = new Font("", Font.BOLD, Design.getTitleSize());
		titleLabel.setFont(f);
		add(titleLabel, c);
		
		c.gridwidth = 1;
		
		i++;
		for(FormOptions options : fields) {
			// Setting position
			c.gridy = i;
			c.gridx = 0;
		
			// Adding label
			add(new JLabel(options.getDisplayName() + (options.isRequired() ? " *" : "")), c);
			
			// Moving +1 @ x
			c.gridx = 1;
			
			// Add the corresponding custom component
			CustomComponent comp = FormFactory.getComponent(options, this);
			components.put(options.getKey(), comp);
			add(comp.getComponent(), c);
			
			i++;
		}
		
		// Submit button
		submit = new JButton("Bestätigen");
		submit.setEnabled(isValid());
		if(submitListener != null) {
			submit.addActionListener(submitListener);
		}
		
		c.gridy = i;
		c.gridx = 0;
		
		add(submit, c);
	}
	
	public void valueChanged() {
		try {
			submit.setEnabled(isValid());
		}
		catch(NullPointerException ex) {}
	}
	
	public boolean isValid() {
		boolean valid = true;
		if(components != null) {
			for(String k : components.keySet()) {
				CustomComponent cc = components.get(k);
				if(!cc.isValid()) {
					valid = false;
				}
			}
		}
		return valid;
	}
	
	public Object getValue(String key) {
		if(components.containsKey(key)) {
			return components.get(key).getValue();
		}
		return null;
	}
	
	public Map<String, Object> getValues() {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for(String k : components.keySet()) {
			values.put(k, components.get(k).getValue());
		}
		return values;
	}
	
	public boolean setValue(String key, Object value) {
		if(components.containsKey(key)) {
			CustomComponent cc = components.get(key);
			cc.setValue(value);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clear() {
		for(CustomComponent cc : components.values()) {
			cc.clear();
		}
	}
	
}
