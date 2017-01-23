package com.ubs.main.phantom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ubs.main.phantom.data.Artikel;

public class LagerDAO {
	
	public static int insert(Connection con, Artikel a) throws SQLException {
		String sql = "INSERT INTO Artikel (A_ID, A_Name, A_Bestand) VALUES (?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		int count = 0;
		
		pstmt.setInt(1, a.getId());
		pstmt.setString(2, a.getName());
		pstmt.setInt(3, a.getBestand());
		
		count = pstmt.executeUpdate();
		
		return count;
	}
	
	public static int delete(Connection con, Artikel a) throws SQLException {
		String sql = "DELETE FROM Artikel WHERE A_ID=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, a.getId());
		
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	public static int getArtikelCount(Connection con) throws SQLException {
		String sql = "SELECT COUNT(A_ID) FROM Artikel";
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			return rs.getInt(1);
		}
		
		return 0;
	}
	
	public static int getBestandSum(Connection con) throws SQLException {
		String sql = "SELECT SUM(A_Bestand) FROM Artikel";
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			return rs.getInt(1);
		}
		
		return 0;
	}
	
}
