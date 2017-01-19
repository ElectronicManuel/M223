package com.ubs.factory.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Birthdate extends Validator {

	@Override
	public boolean validate(Object o) {
		
		Date d = (Date)o;
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(d);
		int year = c.get(Calendar.YEAR);
		c.setTime(new Date());
		int currentYear = c.get(Calendar.YEAR);
		if(year > 1800 && year <= currentYear) {
			return true;
		}
		
		return false;
	}

}
