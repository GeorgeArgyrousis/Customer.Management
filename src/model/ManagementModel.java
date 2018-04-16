package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import controller.ManagementController;
import net.proteanit.sql.DbUtils;

public class ManagementModel extends DatabaseModel{

	/* The current clicked id */
	private int clickedId;
	
	private String searchQuery = "select Name,Surname,Number,Email from Data where Name=? or Surname=? or Number=? or Email=?";
	private String loadQuery = "select Name,Surname,Number,Email from Data where ID!=0";
	private String saveQuery = "insert into Data (ID,Name,Surname,Number,Email) values (?,?,?,?,?)";
	private String idQuery = "select ID from Data";
	
	/* The controller of the management */
	private ManagementController controller;
	
	public ManagementModel(ManagementController controller) {
		super();
		this.controller = controller;
		loadTable();
	}
	
	/* When a user clicks on a box from the JTable,
	 * the information contained gets displayed on
	 * the fields from the front-end */
	public void clickToBox(JTable table) {
		try {
			int row = table.getSelectedRow();
			String name = (table.getModel().getValueAt(row, 0)).toString();
			String surname = (table.getModel().getValueAt(row, 1)).toString();
			String clickQuery = "select * from Data where Name='" + name + "' and Surname='" + surname + "'";
			PreparedStatement statement = this.connection.prepareStatement(clickQuery);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				clickedId = result.getInt("ID");
				controller.getPanel().getTName().setText(result.getString("Name"));
				controller.getPanel().getSurname().setText(result.getString("Surname"));
				controller.getPanel().getNumber().setText(result.getString("Number"));
				controller.getPanel().getEmail().setText(result.getString("Email"));
			}
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* execute multiple quaries based on what the
	 * user writes in the field. The method will
	 * try to match the name with all the relavant
	 * rows in the table that match */
	public void searchDatabase(String text){
		if(text.equals("")){
			loadTable();
			return;
		}
		try {
			PreparedStatement statement = connection.prepareStatement(searchQuery);
			statement.setString(1, text);
			statement.setString(2, text);
			statement.setString(3, text);
			statement.setString(4, text);
			ResultSet result = statement.executeQuery();
			
			controller.getPanel().getTable().setModel(DbUtils.resultSetToTableModel(result));
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Check the fields and according to specifications add them in the
	 * database;
	 */
	public void addData(String name, String surname, String number, String email) {
		if (controller.getPanel().checkEmptyFields()) return;
		try {
			PreparedStatement statement = this.connection.prepareStatement(saveQuery);
			statement.setString(1, Integer.toString(selectId()));
			statement.setString(2, name);
			statement.setString(3, surname);
			statement.setString(4, number);
			statement.setString(5, email);
			statement.execute();
			loadTable();
			controller.getPanel().clearBoxes();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Update the row's data based on the changes made
	 * to the information from the input fields */
	public void updateData(String name, String surname, String number, String email) {
		try {
			String updateQuery = "Update Data set Name='" + name + "',Surname='" + surname + "',Number='" + number + "',Email='" + email + "' where ID='" + clickedId + "'";
			PreparedStatement statement = this.connection.prepareStatement(updateQuery);
			statement.execute();
			loadTable();
			controller.getPanel().clearBoxes();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Remove a selected row from the table */
	public void removeData() {
		String removeQuery = "delete from Data where ID='" + clickedId + "'";
		try {
			PreparedStatement statement = this.connection.prepareStatement(removeQuery);
			statement.execute();
			loadTable();
			controller.getPanel().clearBoxes();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Will select the highest id from the
	 * table and return that number + 1 */
	public int selectId() {
		int max = 0;
		try {
			PreparedStatement statement = this.connection.prepareStatement(idQuery);
			ResultSet result = statement.executeQuery();
			int value = 0;
			while (result.next()) {
				value = result.getInt("ID");
				if (value > max) {
					max = value;
				}
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max + 1;
	}

	/* Load, refresh the table and all
	 * of it's contents */
	public void loadTable() {
		try {
			PreparedStatement statement = this.connection.prepareStatement(loadQuery);
			ResultSet result = statement.executeQuery();
			controller.getPanel().getTable().setModel(DbUtils.resultSetToTableModel(result));
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
