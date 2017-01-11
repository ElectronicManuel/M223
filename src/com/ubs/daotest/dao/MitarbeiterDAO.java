package com.ubs.daotest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ubs.daotest.data.Mitarbeiter;
import com.ubs.daotest.jdbc.DBConnector;

public class MitarbeiterDAO {
	
	public static Mitarbeiter insert(Mitarbeiter m) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "INSERT INTO Mitarbeiter (vorname, name, abteilung, email, telefon, fax, gebauede, geburtsdatum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
		
		pstmt.setString(1, m.getName());
		pstmt.setString(2, m.getVorname());
		pstmt.setString(3, m.getAbteilung());
		pstmt.setString(4, m.getEmail());
		pstmt.setString(5, m.getTelefon());
		pstmt.setString(6, m.getFax());
		pstmt.setString(7, m.getGebauede());
		pstmt.setDate(8, new java.sql.Date(m.getGbdatum().getTime()));
		pstmt.executeUpdate();
		
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			int newId = rs.getInt(1);
			m.setPersid(newId);
		}
		
		con.commit();
		
		DBConnector.closeConnection();
		
		return m;
	}
	
	public static int update(Mitarbeiter m) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "UPDATE Mitarbeiter SET vorname=?, name=?, abteilung=?, email=?, telefon=?, fax=?, gebauede=?, geburtsdatum=? WHERE persid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, m.getName());
		pstmt.setString(2, m.getVorname());
		pstmt.setString(3, m.getAbteilung());
		pstmt.setString(4, m.getEmail());
		pstmt.setString(5, m.getTelefon());
		pstmt.setString(6, m.getFax());
		pstmt.setString(7, m.getGebauede());
		pstmt.setDate(8, new java.sql.Date(m.getGbdatum().getTime()));
		pstmt.setInt(9, m.getPersid());
		int result = pstmt.executeUpdate();
		
		con.commit();
		
		DBConnector.closeConnection();
		
		return result;
	}
	
	public static int delete(Mitarbeiter m) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "DELETE FROM Mitarbeiter WHERE persid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, m.getPersid());
		int result = pstmt.executeUpdate();
		
		con.commit();
		
		DBConnector.closeConnection();
		
		return result;
	}
	
	public static Mitarbeiter getById(int id) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "SELECT persid, vorname, name, abteilung, email, telefon, fax, gebauede, geburtsdatum FROM Mitarbeiter WHERE persid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		con.commit();
		Mitarbeiter m = null;
		if(rs.next()) {
			m = new Mitarbeiter();
			m.setPersid(rs.getInt(1));
			m.setName(rs.getString(2));
			m.setVorname(rs.getString(3));
			m.setAbteilung(rs.getString(4));
			m.setEmail(rs.getString(5));
			m.setTelefon(rs.getString(6));
			m.setFax(rs.getString(7));
			m.setGebauede(rs.getString(8));
			m.setGbdatum(new java.util.Date(rs.getDate(9).getTime()));
		}
		DBConnector.closeConnection();
		
		return m;
	}
	
	public static Mitarbeiter getByQuery(String query) throws SQLException {
		Connection con = DBConnector.getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		con.commit();
		Mitarbeiter m = null;
		if(rs.next()) {
			m = new Mitarbeiter();
			m.setPersid(rs.getInt(1));
			m.setName(rs.getString(2));
			m.setVorname(rs.getString(3));
			m.setAbteilung(rs.getString(4));
			m.setEmail(rs.getString(5));
			m.setTelefon(rs.getString(6));
			m.setFax(rs.getString(7));
			m.setGebauede(rs.getString(8));
			m.setGbdatum(new java.util.Date(rs.getDate(9).getTime()));
		}
		DBConnector.closeConnection();
		
		return m;
	}
	
	public static int getMitarbeiterCount() throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "SELECT COUNT(persid) FROM Mitarbeiter";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		con.commit();
		if(rs.next()) {
			return rs.getInt(1);
		}
		DBConnector.closeConnection();
		
		return 0;
	}
	
}
