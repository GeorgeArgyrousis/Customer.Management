package view.login;
import javax.swing.JPanel;

import components.Frame;
import controller.LoginController;
import controller.RegisterController;

public class LoginFrame extends Frame {

	/* The default verison ID */
	private static final long serialVersionUID = 1L;
	
	/* The title of the frame */
	private static final String TITLE = "Login | Unnamed";
	
	/* The controller connecting the login */
	private LoginController controller;

	/* Constructor initiating all relevant components */
	public LoginFrame(JPanel panel, LoginController controller){
		super(TITLE, panel);
		this.controller = controller;
		switchFrames();
	}
	
	/* If there is no user created as an administrator
	 * create a new administrating account */
	private void switchFrames() {
		if (controller.getModel().isRegistered()) {
			dispose();
			new RegisterController();
		}
	}
}
