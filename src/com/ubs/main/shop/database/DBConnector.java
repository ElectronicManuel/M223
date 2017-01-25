package com.ubs.main.shop.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	
	private static String connectString = "jdbc:mysql://localhost/M223?useSSL=false";
	
	public static Connection openConnection(int isolationLevel)
    {
		try
		{
   			Class.forName( "com.mysql.jdbc.Driver" );
   			Connection c = DriverManager.getConnection( connectString, "root", "root" ); 
   			c.setTransactionIsolation(isolationLevel);
   			c.setAutoCommit(false);
   			return c;
		}
		catch( Exception e)
		{
    			System.out.println( "No connection to " + connectString ); 
    			e.printStackTrace();
		}
		return null;
    }

}
