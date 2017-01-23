package com.ubs.main.meeting.db.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.ubs.main.daotest.jdbc.DBConnector;
import com.ubs.main.meeting.data.Anmeldung;
import com.ubs.main.meeting.db.DBService;

public class MySQLAnmeldungDAO implements DBService<Anmeldung> {

	public int insert(Anmeldung a) {
		int toReturn = 0;
		Connection con = DBConnector.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO anmeldungen (A_Name, A_Vorname, A_Email, A_GB, A_Adresse, A_Status, A_Tutorium, A_Bemerkung) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			// Werte befüllen
			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getVorname());
			pstmt.setString(3, a.getEmail());
			if(a.getGb() != null) {
				pstmt.setDate(4, new Date(a.getGb().getTime()));
			}
			else {
				pstmt.setNull(4, Types.DATE);
			}
			pstmt.setString(5, a.getAdresse());
			pstmt.setString(6, a.getStatus());
			pstmt.setString(7, a.getTutorium());
			pstmt.setString(8, a.getBemerkung());
			
			toReturn = pstmt.executeUpdate();
			con.commit();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		DBConnector.closeConnection();
		
		return toReturn;
	}

	public int delete(Anmeldung toDelete) {
		return 0;
	}

	public int update(Anmeldung toUpdate) {
		return 0;
	}

	public Anmeldung getById(int id) {
		return null;
	}

	public Anmeldung getByQuery(String query) {
		return null;
	}
	
}
