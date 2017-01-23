package com.ubs.main.factory.components.textfield;

import com.ubs.main.factory.components.CustomSettings;

public class TextSettings extends CustomSettings {
	
	private int maxLength;
	
	public TextSettings(int maxLength) {
		setMaxLength(maxLength);
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
}
