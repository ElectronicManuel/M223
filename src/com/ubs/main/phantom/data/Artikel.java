package com.ubs.main.phantom.data;

public class Artikel {
	
	private int id;
	private String name;
	private int bestand;
	
	public Artikel(int id, String name, int bestand) {
		this.id = id;
		this.name = name;
		this.bestand = bestand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBestand() {
		return bestand;
	}

	public void setBestand(int bestand) {
		this.bestand = bestand;
	}

}
