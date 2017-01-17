package com.ubs.factory.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class AutoForm extends JPanel {
	
	private static final long serialVersionUID = -3062620578246256524L;
	
	private Map<String, InputType> fields;
	private Map<String, JComponent> components = new LinkedHashMap<String, JComponent>();
	
	public AutoForm(Map<String, InputType> fields) {
		this.fields = fields;
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
		for(String key : fields.keySet()) {
			// Getting type
			InputType type = fields.get(key);
			
			// Setting position
			c.gridy = i;
			c.gridx = 0;
		
			// Adding label
			add(new JLabel(key), c);
			
			// Moving +1 @ x
			c.gridx = 1;
			
			// Call the corresponding add method
			if(type == InputType.TEXTFIELD) {
				addTextField(c, key);
			}
			else if(type == InputType.TEXTAREA) {
				addTextArea(c, key);
			}
			else if(type == InputType.)
			i++;
		}
	}
	
	private void addTextField(GridBagConstraints c, String key) {
		JTextField field = new JTextField(20);
		components.put(key, field);
		add(field, c);
	}
	
	private void addTextArea(GridBagConstraints c, String key) {
		JTextArea area = new JTextArea(3, 20);
		components.put(key, area);
		add(area, c);
	}
	
	

}
