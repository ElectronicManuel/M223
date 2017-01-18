package test.factory;

import com.ubs.factory.components.Validator;

public class NameValidator extends Validator {

	@Override
	public boolean validate(Object o) {
		String name = (String)o;
		
		return name.matches("^[a-zA-Z]*$");
	}

}
