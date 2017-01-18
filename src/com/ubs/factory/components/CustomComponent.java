package com.ubs.factory.components;

import javax.swing.JComponent;

public interface CustomComponent {
	
	public JComponent getComponent();
	public boolean isValid();
	public Object getValue();
	
	public void clear();

}
