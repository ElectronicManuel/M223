package com.ubs.main.daotest.data;

import java.util.Date;

public class Mitarbeiter {
	
	private int persid;
	private String vorname;
	private String name;
	private String abteilung;
	private String email;
	private String telefon;
	private String fax;
	private String gebauede;
	private Date gbdatum;
	
	public int getPersid() {
		return persid;
	}
	public void setPersid(int persid) {
		this.persid = persid;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbteilung() {
		return abteilung;
	}
	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getGebauede() {
		return gebauede;
	}
	public void setGebauede(String gebauede) {
		this.gebauede = gebauede;
	}
	public Date getGbdatum() {
		return gbdatum;
	}
	public void setGbdatum(Date gbdatum) {
		this.gbdatum = gbdatum;
	}
	
}
