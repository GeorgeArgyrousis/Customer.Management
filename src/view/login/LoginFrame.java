package view.login;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.LoginController;
import controller.RegisterController;

public class LoginFrame extends JFrame {

	/* The default verison ID */
	private static final long serialVersionUID = 1L;
	
	/* The title of the frame */
	private final String TITLE = "Login | Unnamed";
	
	/* The controller connecting the login */
	private LoginController controller;

	/* Constructor initiating all relevant components */
	public LoginFrame(JPanel panel, LoginController controller){
		this.controller = controller;
		setTitle(TITLE);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
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
