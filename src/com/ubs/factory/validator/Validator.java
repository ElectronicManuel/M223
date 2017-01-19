package com.ubs.factory.validator;

public abstract class Validator {
	
	public abstract boolean validate(Object o);
	
	public final static Validator EMAIL = new Email();
	public final static Validator LETTERS_SPACES_ONLY = new LettersSpacesOnly();
	public final static Validator BIRTHDATE = new Birthdate();

}
