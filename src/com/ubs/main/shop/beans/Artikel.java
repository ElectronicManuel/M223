package com.ubs.main.shop.beans;

import com.ubs.main.shop.annotations.Column;
import com.ubs.main.shop.annotations.Id;
import com.ubs.main.shop.annotations.Table;

@Table(name="Artikel")
public class Artikel {
	
	@Id
	@Column(name="A_ID")
	private int id;
	
	@Column(name="A_Name")
	private String name;
	
	@Column(name="A_Bestand")
	private int bestand;
	
	public Artikel(int id, String name, int bestand) {
		this.id = id;
		this.name = name;
		this.bestand = bestand;
	}
	
	public Artikel() {
	}

}
