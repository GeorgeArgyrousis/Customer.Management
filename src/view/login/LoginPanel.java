package view.login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import components.Button;
import components.Field;
import components.Label;
import components.Panel;
import components.PasswordField;
import components.Separator;
import controller.LoginController;

public class LoginPanel extends JPanel {

	/* The default version id */
	private static final long serialVersionUID = 1L;
	
	/* The width and height of the app */
	private static final int WIDTH = 800, HEIGHT = 500;

	/* The file separator of the current system */
	private String ls = System.getProperty("file.separator");
	
	/* The icon path for the login window */
	private String iconPath = "res" + ls + "icon.png";
	
	/* The button image path */
	private String buttonPath = "res" + ls + "submit.png";
	
	/* The field responsible for the password */
	private PasswordField password;
	
	/* The field responsible for the email */
	private Field email;
	
	/* The button responsible for the submit request */
	private Button submit;
	
	/* The button responsible for changing the password */
	private Button forgotPassword;
	
	/* The controller of the login form */
	private LoginController controller;

	/**
	 * Create the panel.
	 */
	public LoginPanel(LoginController controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(36, 47, 65));
		setLayout(null);
		LoginPanelListener();
		
		Panel panel = new Panel(0, 0, 400, 500);
		panel.setBackground(new Color(97, 212, 195));
		add(panel);

		Label title = new Label("You are your own boss", 54, 65, 295, 36, panel);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);;

		Label image = new Label("", 6, 148, 388, 530, panel);
		image.addIcon(iconPath);

		Label PlanYourself = new Label("Plan yourself", 54, 100, 295, 36, this);
		PlanYourself.setHorizontalAlignment(SwingConstants.CENTER);
		PlanYourself.setForeground(Color.WHITE);

		Label signLabel = new Label("Sign In", 505, 65, 61, 30, this);
		signLabel.setForeground(Color.WHITE);

		Label emailLabel = new Label("EMAIL", 505, 150, 107, 30, this);
		emailLabel.setForeground(Color.LIGHT_GRAY);

		Label passwordLabel = new Label("PASSWORD", 505, 250, 131, 30, this);
		passwordLabel.setForeground(Color.LIGHT_GRAY);

		new Separator(505, 215, 185, 12, this);

		new Separator(505, 315, 185, 12, this);

		password = new PasswordField(505, 292, 185, 28, this);
		passwordListener();

		email = new Field(505, 192, 185, 28, this);

		submit = new Button("SUBMIT", 505, 390, 185, 42, this);
		submit.setForeground(new Color(255, 255, 255));
		submit.setBackground(new Color(97, 212, 195));
		submit.addIcon(buttonPath);
		submitListener();

		forgotPassword = new Button("forgot password?", 505, 454, 185, 29, this);
		forgotPassword.setForeground(Color.lightGray);
		forgotPassword.setBackground(new Color(97, 212, 195));
		forgotPasswordListener();
	}
	
	private void LoginPanelListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.getModel().processCredentials();
				}
			}
		});
	}
	
	private void passwordListener() {
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.getModel().processCredentials();
				}
			}
		});
	}
	
	private void submitListener() {
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getModel().processCredentials();
			}
		});
	}
	
	private void forgotPasswordListener() {
		forgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
	
	public JTextField getEmail() {
		return this.email;
	}
	
	public JPasswordField getPassword() {
		return this.password;
	}
}
