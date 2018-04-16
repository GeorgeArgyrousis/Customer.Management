package controller;

import model.LoginModel;
import view.login.LoginFrame;
import view.login.LoginPanel;

public class LoginController {
	
	/* The login model */
	private LoginModel model;
	
	/* The login frame */
	private LoginFrame frame;
	
	/* The login panel */
	private LoginPanel panel;
	
	/* initiate all relevant components */
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
