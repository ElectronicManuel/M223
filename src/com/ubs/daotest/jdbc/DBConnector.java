package com.ubs.daotest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector
{

    private static Connection con = null; 
    
    private String connectString = "jdbc:mysql://localhost/M223?useSSL=false";
    
    
    public Connection openConnection(int isolationLevel)
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
    
    public static Connection getConnection()
    {
        if( con == null )
        {
        	con = new DBConnector().openConnection(Connection.TRANSACTION_REPEATABLE_READ);
            return con;
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


