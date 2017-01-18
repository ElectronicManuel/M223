package com.ubs.factory.controller;

import com.ubs.factory.components.CustomSettings;
import com.ubs.factory.components.Validator;

public class FormOptions {
	
	private String key;
	private String displayName;
	private InputType type;
	private boolean required;
	private CustomSettings settings;
	private Validator validator;
	
	public FormOptions(String key, String displayName, InputType type, boolean required, CustomSettings settings, Validator val) {
		this.key = key;
		this.displayName = displayName;
		this.type = type;
		this.required = required;
		this.settings = settings;
		this.validator = val;
	}
	
	/* Getters / Setters */
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public InputType getType() {
		return type;
	}
	public void setType(InputType type) {
		this.type = type;
	}
	public CustomSettings getSettings() {
		return settings;
	}
	public void setSettings(CustomSettings settings) {
		this.settings = settings;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
}
