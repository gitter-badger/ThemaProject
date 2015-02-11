package database;

import java.sql.*;

public class Query
{
  public static void main( String args[] )
  {
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
  }
  
  /**
   * Test functie
   * Gebruikt voor package testing
   * @return {@link Object}
   */
  public Object test(){
	  return "test";
  }
}