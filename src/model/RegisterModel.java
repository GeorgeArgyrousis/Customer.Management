package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.LoginController;
import controller.RegisterController;

public class RegisterModel extends DatabaseModel{

	/* The query registering the master user */
	private String registerQuery = "insert into Data (ID,Name,Surname,Password,Email) values (?,?,?,?,?)";
	
	/* The register controller */
	private RegisterController controller;
	
	public RegisterModel(RegisterController controller) {
		super();
		this.controller = controller;
	}
	
	/* Register a user as masterUser using the defined attributes */
	public void register(String name, String surname, String password, String email) {
		if (controller.getPanel().checkEmptyFields()) return;
		try {
			PreparedStatement statement = this.connection.prepareStatement(registerQuery);
			statement.setString(1, "0");
			statement.setString(2, name);
			statement.setString(3, surname);
			statement.setString(4, password);
			statement.setString(5, email);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		controller.getFrame().dispose();
		new LoginController();
	}
}
