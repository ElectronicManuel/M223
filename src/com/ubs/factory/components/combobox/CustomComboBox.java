package com.ubs.factory.components.combobox;

import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.ubs.factory.Design;
import com.ubs.factory.components.CustomComponent;
import com.ubs.factory.components.Validator;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.gui.AutoForm;

@SuppressWarnings({ "serial", "rawtypes" })
public class CustomComboBox extends JComboBox implements CustomComponent {
	
	private FormOptions opts;
	private AutoForm form;
	private Validator validator;
	private boolean valid = true;
	
	@SuppressWarnings("unchecked")
	public CustomComboBox(FormOptions opts, AutoForm form, Object[] objects) {
		super(objects);
		this.form = form;
		this.opts = opts;
		validator = opts.getValidator();
		
		setVerifier();
	}
	
	private void setVerifier() {
		InputVerifier ver = new InputVerifier() {
			
			@Override
			public boolean verify(JComponent input) {
				boolean oldValid = valid;
				if(validator != null) {
					if(validator.validate(getValue())) {
						setBackground(Design.getOkColor());
						valid = true;
					}
					else {
						setBackground(Design.getErrorColor());
						valid = false;
					}
				}
				else {
					setBackground(Design.getOkColor());
					valid = true;
				}
				
				if(getValue() == null && opts.isRequired()) {
					setBackground(Design.getOkColor());
					valid = true;
				}
				
				if(oldValid != valid) {
					form.valueChanged();
				}
				return valid;
			}
		};
		setInputVerifier(ver);
	}

	@Override
	public JComponent getComponent() {
		return this;
	}
	
	@Override
	public boolean isValid() {
		if(getInputVerifier() != null) {
			return getInputVerifier().verify(this);
		}
		else {
			return false;
		}
	}

	@Override
	public Object getValue() {
		return getSelectedItem();
	}

	@Override
	public void clear() {
		setSelectedIndex(0);
	}

}
