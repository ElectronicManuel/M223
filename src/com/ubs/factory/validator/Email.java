package com.ubs.factory.validator;

public class Email extends Validator {

	@Override
	public boolean validate(Object o) {
		String email = (String)o;
		
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

}
