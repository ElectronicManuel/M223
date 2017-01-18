package com.ubs.factory.components.textfield;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.ubs.factory.Design;
import com.ubs.factory.components.CustomComponent;
import com.ubs.factory.components.Validator;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.gui.AutoForm;

@SuppressWarnings("serial")
public class CustomTextField extends JTextField implements CustomComponent {
	
	private FormOptions opts;
	private TextSettings set;
	private AutoForm form;
	private Validator validator;
	private boolean valid = true;
	
	public CustomTextField(FormOptions opts, AutoForm form) {
		super();
		// Settings
		this.form = form;
		this.opts = opts;
		validator = opts.getValidator();
		set = (TextSettings) opts.getSettings();
		setColumns(set.getMaxLength());
		
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
				
				if(((String)getValue()).length() == 0 && opts.isRequired()) {
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
		return getText();
	}

	@Override
	public void clear() {
		setText("");
	}

}
