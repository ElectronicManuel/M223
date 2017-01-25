package com.ubs.main.shop.beans;

import java.lang.reflect.Field;

import com.ubs.main.shop.annotations.Column;
import com.ubs.main.shop.annotations.Id;

public class Attribute {
	
	private String column;
	private boolean id;
	private Object value;
	private Class<?> type;
	
	private Field field;
	
	public Attribute(Field field) {
		this.field = field;
		setColumn(field.getDeclaredAnnotation(Column.class).name());
		setId(field.getDeclaredAnnotation(Id.class) != null);
		setType(field.getType());
	}
	
	public Attribute() {
	}
	
	public void calcValue(Object o) throws IllegalArgumentException, IllegalAccessException {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		setValue(field.get(o));
		field.setAccessible(accessible);
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}
