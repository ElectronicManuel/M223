package com.ubs.main.meeting.data;

import java.util.Date;

public class Anmeldung {
	
	private String name;
	private String vorname;
	private String email;
	private Date gb;
	private String adresse;
	private String status;
	private String tutorium;
	private String bemerkung;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getGb() {
		return gb;
	}
	public void setGb(Date gb) {
		this.gb = gb;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTutorium() {
		return tutorium;
	}
	public void setTutorium(String tutorium) {
		this.tutorium = tutorium;
	}
	public String getBemerkung() {
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
	
}
