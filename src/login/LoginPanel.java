package login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import components.Button;
import components.Field;
import components.Label;
import components.PasswordField;
import controller.LoginController;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800, HEIGHT = 500;

	private String ls = System.getProperty("file.separator");
	
	private String iconPath = "res" + ls + "icon.png";
	private String buttonPath = "res" + ls + "submit.png";
	
	private PasswordField password;
	private Field email;
	
	private LoginController controller;

	/**
	 * Create the panel.
	 */
	public LoginPanel(LoginController controller) {
		this.controller = controller;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(36, 47, 65));
		setLayout(null);
		addLoginPanelListener();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(97, 212, 195));
		panel.setBounds(0, 0, 400, 500);
		add(panel);
		panel.setLayout(null);

		Label title = new Label("You are your own boss", 54, 65, 295, 36, panel);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);;

		JLabel image = new JLabel("");
		BufferedImage img = null;
		ImageIcon icon = null;
		try {
			img = ImageIO.read(new File(iconPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		image.setIcon(icon);
		image.setBounds(6, 148, 388, 530);
		panel.add(image);

		Label PlanYourself = new Label("Plan yourself", 54, 100, 295, 36, this);
		PlanYourself.setHorizontalAlignment(SwingConstants.CENTER);
		PlanYourself.setForeground(Color.WHITE);

		Label signLabel = new Label("Sign In", 505, 65, 61, 30, this);
		signLabel.setForeground(Color.WHITE);

		Label emailLabel = new Label("EMAIL", 505, 150, 107, 30, this);
		emailLabel.setForeground(Color.LIGHT_GRAY);

		Label passwordLabel = new Label("PASSWORD", 505, 250, 131, 30, this);
		passwordLabel.setForeground(Color.LIGHT_GRAY);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(505, 215, 185, 12);
		add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBounds(505, 315, 185, 12);
		add(separator_2);

		password = new PasswordField(505, 292, 185, 28, this);
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					controller.getModel().processCredentials();
				}
			}
		});

		email = new Field(505, 192, 185, 28, this);

		JButton submit = new JButton("SUBMIT");
		try {
			img = ImageIO.read(new File(buttonPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		submit.setIcon(icon);
		submit.setContentAreaFilled(false);
		submit.setBorderPainted(false);
		submit.setForeground(new Color(255, 255, 255));
		submit.setBackground(new Color(97, 212, 195));
		submit.setBounds(505, 390, 185, 42);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.getModel().processCredentials();
			}
		});
		add(submit);

		Button forgotPassword = new Button("forgot password?", 505, 454, 185, 29, this);
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
		forgotPassword.setForeground(Color.lightGray);
		forgotPassword.setBackground(new Color(97, 212, 195));
	}
	
	private void addLoginPanelListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					controller.getModel().processCredentials();
				}
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
