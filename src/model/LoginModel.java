package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.DatabaseConnection;
import controller.LoginController;
import managment.ManagmentFrame;

public class LoginModel {

	private String ls = System.getProperty("file.separator");

	private String dbPath = "db" + ls + "Customers.sqlite";
	private String loginQuery = "select ID,Email,Password from Data where ID=? and Email=? and Password=?";
	private String registeredQuery = "select ID from Data where ID=?";
	
	private Connection connection;
	private LoginController controller;
	
	public LoginModel(LoginController controller) {
		this.controller = controller;
		this.connection = new DatabaseConnection(dbPath).connectToDatabase();
		//TODO :: handle with errors;
	}
	
	@SuppressWarnings("deprecation")
	public void processCredentials() {
		try {
			/* What we are asking from the database */
			PreparedStatement statement = connection.prepareStatement(loginQuery);
			statement.setString(1, "0");
			statement.setString(2, controller.getPanel().getEmail().getText());
			statement.setString(3, controller.getPanel().getPassword().getText());

			ResultSet result = statement.executeQuery();
			int count = 0;
			while (result.next()) {
				count++;
			}
			if (count == 1) {
				controller.getFrame().dispose();
				new ManagmentFrame();
			} else {
				JOptionPane.showMessageDialog(null, "Email or Password incorrect!");
			}
			result.close();
			statement.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/* Check if there is at least one registered account as admin */
	public boolean isRegistered() {
		try {
			PreparedStatement statement = connection.prepareStatement(registeredQuery);
			statement.setInt(1, 0);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				result.close();
				statement.close();
				return false;
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
