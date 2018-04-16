package model;

import java.sql.Connection;

import connection.DatabaseConnection;

public abstract class DatabaseModel {

	/* The file separator of the current system */
	protected String ls = System.getProperty("file.separator");

	/* The database path */
	protected String dbPath = "db" + ls + "Customers.sqlite";
	
	/* Maintain the connection to the database */
	protected Connection connection;
	
	public DatabaseModel(){
		this.connection = new DatabaseConnection(dbPath).connectToDatabase();
	}
	
}
