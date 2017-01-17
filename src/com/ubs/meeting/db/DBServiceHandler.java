package com.ubs.meeting.db;

import com.ubs.meeting.db.mysql.MySQLAnmeldungDAO;

public class DBServiceHandler {
	
	public static DBService<?> getDBService() {
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			// MySQL Driver gefunden
			
			System.out.println("MySQL is being used");
			return new MySQLAnmeldungDAO();
		} catch (ClassNotFoundException e) {
		}
		
		return null;
	}

}
