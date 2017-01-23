package com.ubs.main.meeting.data;

public class Tutorium {
	
	private String name;
	
	public Tutorium(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
