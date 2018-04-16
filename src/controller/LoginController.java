package controller;

import login.LoginFrame;
import login.LoginPanel;
import model.LoginModel;

public class LoginController {
	
	private LoginModel model;
	
	private LoginFrame frame;
	private LoginPanel panel;

	public LoginController() {
		model = new LoginModel(this);
		panel = new LoginPanel(this);
		frame = new LoginFrame(panel, this);
	}
	
	public LoginModel getModel() {
		return this.model;
	}
	
	public LoginFrame getFrame() {
		return this.frame;
	}
	
	public LoginPanel getPanel() {
		return this.panel;
	}
}
