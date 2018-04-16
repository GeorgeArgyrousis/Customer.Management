package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.LoginController;
import view.managment.ManagmentFrame;

public class LoginModel extends DatabaseModel{
	
	/* The query used to login into the management view */
	private String loginQuery = "select ID,Email,Password from Data where ID=? and Email=? and Password=?";
	
	/* Check if there is an matching id */
	private String registeredQuery = "select ID from Data where ID=?";
	
	/* The controller conecting the parts together */
	private LoginController controller;
	
	/* Initiate all relevant components */
	public LoginModel(LoginController controller) {
		super();
		this.controller = controller;
		//TODO :: handle with errors;
	}
	
	@SuppressWarnings("deprecation")
	/* Get the current email and password and check if they match the 
	 * current admins status. An admin has id number 0 */
	public void processCredentials() {
		try {
			PreparedStatement statement = connection.prepareStatement(loginQuery);
			statement.setString(1, "0");
			statement.setString(2, controller.getPanel().getEmail().getText());
			statement.setString(3, controller.getPanel().getPassword().getText());

			ResultSet result = statement.executeQuery();
			boolean AdminUserExists = (result.next() ? true : false);
			if (AdminUserExists) {
				controller.getFrame().dispose();
				new ManagmentFrame();
			} else {
				JOptionPane.showMessageDialog(null, "Email or Password incorrect!");
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Check if there is at least one registered account as admin */
	public boolean isRegistered() {
		try {
			PreparedStatement statement = connection.prepareStatement(registeredQuery);
			statement.setInt(1, 0);
			ResultSet result = statement.executeQuery();
			boolean exists = (result.next() ? true : false);
			result.close();
			statement.close();
			if (exists) return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
