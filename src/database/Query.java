package database;

import java.sql.*;

public class Query {

	/**
	 * getArtikelen() geeft een lijst met de artikelen die nu in de database
	 * staan.
	 * 
	 * @return {@link String[]}
	 * @throws SQLException
	 */
	public String[] getArtikelen(String database, String column) throws SQLException {
		Statement stmt = makeConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + database + ";");
		int i = 0;
		String[] x = null;
		while (rs.next()) {
			x[i] = rs.getString(column);
			System.out.println(rs.getString(column));
			i++;
		}
		return x;
	}

	/**
	 * setArtikelen zet een nieuw artikel in de lijst met artikelen
	 * 
	 * @return {@link Boolean}
	 * @throws SQLException
	 */
	public boolean setArtikel(String table, String naam) throws SQLException {
		Statement stmt = makeConnection();
		stmt.executeUpdate("INSERT INTO " + table + " VALUES('" + naam + "')");
		return true;
	}

	/**
	 * Voor het uitvoeren van rauwe SQL statements. Dit return een ResultSet en
	 * hiervoor moet SQLlite geimporteerd worden (Eclipse zou dit zelf moeten
	 * regelen)
	 * 
	 * @param input
	 * @return {@link ResultSet}
	 * @throws SQLException
	 */
	public ResultSet rawSQL(String input) throws SQLException {
		Statement stmt = makeConnection();
		return stmt.executeQuery(input);
	}

	/**
	 * Maakt een table in de gewenste database
	 * 
	 * @param naam
	 * @throws SQLException
	 */
	public void makeTable(String naam) throws SQLException {
		Statement stmt = makeConnection();
		stmt.executeUpdate("CREATE TABLE " + naam + " (naam TEXT NOT NULL);");
	}

	/**
	 * Controlleer of database bestaat
	 * 
	 * @param naam
	 * @return {@link boolean}
	 */
	public boolean doesDatabaseExists(String naam) {
		// TODO: Maken
		return true;
	}

	/**
	 * Maakt database connection, niet bedoeld voor gebruik buiten de klasse
	 * Query
	 * 
	 * @return {@link Statement}
	 * @throws SQLException
	 */
	private Statement makeConnection() throws SQLException {
		Connection c = null;
		Statement stmt;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		stmt = c.createStatement();
		return stmt;
	}
}