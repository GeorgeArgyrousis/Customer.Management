package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	
	/* The connection to the database */
	private Connection connection;
	
	/* The connection path */
	private String path;
	
	/* Is the connection currently runnning */
	private boolean running;
	
	/* Initiate all relevant components */
	public DatabaseConnection(String path){
		this.path = path;
		this.running = false;
	}
	
	/* Connect to the database and return the
	 * connection */
	public Connection connectToDatabase(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + path);
			running = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean isRunning(){
		return this.running;
	}
}
