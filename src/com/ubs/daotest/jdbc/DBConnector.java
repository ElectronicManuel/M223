package com.ubs.daotest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector
{

    private static Connection con = null; 
    
    private String connectString = "jdbc:mysql://localhost/M223?useSSL=false";
    
    
    private Connection openConnection()
    {
		try
		{
   			Class.forName( "com.mysql.jdbc.Driver" );
   			con = DriverManager.getConnection( connectString, "root", "root" ); 
   			
   			con.setAutoCommit(false);
   			return con;
		}
		catch( Exception e)
		{
    			System.out.println( "No connection to " + connectString ); 
    			e.printStackTrace();
		}
		return null;
    }
    
    public static Connection getConnection()
    {
        if( con == null )
        {
            return new DBConnector().openConnection();
        }
        return con;
    }
    public static void closeConnection()
    {
        try
        {
            getConnection().close();
            con = null;
        }
        catch( Exception e )
        {
            System.out.println( "CLOSE CONNECTION: " + e.getMessage() );
        }
    }    
   
}


