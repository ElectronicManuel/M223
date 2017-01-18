package com.ubs.factory.components;

import javax.swing.JComponent;

import com.ubs.factory.Design;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.gui.AutoForm;

public abstract class CustomComponent<T extends JComponent> {
	
	protected T component;
	
	protected FormOptions options;
	protected AutoForm form;
	protected Validator validator;
	protected boolean valid;
	
	public CustomComponent(FormOptions options, AutoForm form, Validator validator) {
		this.options = options;
		this.form = form;
		this.validator = validator;
	}
	
	// Methods to implement
	public abstract boolean isNull();
	public abstract Object getValue();
	public abstract void clear();
	
	// Implemented Methods
	
	public void verify() {
		boolean oldValid = valid;
		if(validator != null) {
			if(validator.validate(getValue())) {
				valid = true;
			}
			else {
				valid = false;
			}
		}
		else {
			valid = true;
		}
			
		if(isNull() && !getOptions().isRequired()) {
			valid = true;
		}
		if(isNull() && getOptions().isRequired()) {
			valid = false;
		}
		
		getComponent().setBackground(valid ? Design.getOkColor() : Design.getErrorColor());
		if(oldValid != valid) {
			form.valueChanged();
		}
	}
	
	// Getter / Setter
	public T getComponent() {
		return component;
	}
	public void setComponent(T comp) {
		this.component = comp;
	}
	public FormOptions getOptions() {
		return options;
	}
	public void setOptions(FormOptions options) {
		this.options = options;
	}
	public AutoForm getForm() {
		return form;
	}
	public void setForm(AutoForm form) {
		this.form = form;
	}
	public Validator getValidator() {
		return validator;
	}
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
