package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

	private Connection connection;
	
	private String path;
	
	private boolean running;
	
	public DatabaseConnection(String path){
		this.path = path;
		this.running = false;
	}
	
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
