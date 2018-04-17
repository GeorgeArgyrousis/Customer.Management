package view.register;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import components.Button;
import components.Field;
import components.Label;
import components.Panel;
import components.PasswordField;
import components.Separator;
import controller.RegisterController;

public class RegisterPanel extends JPanel{
	
	/* The default version id */
	private static final long serialVersionUID = 1L;

	/* The width and height of this component */
	private static final int WIDTH = 800, HEIGHT = 500;

	/* The separator of the current system */
	private String ls = System.getProperty("file.separator");
	
	/* The path to the submit button icon */
	private String submitPath = "res" + ls + "submit.png";

	private Button register;
	private Field nameField;
	private Field surnameField;
	private Field emailField;
	private PasswordField passwordField;
	private PasswordField confirmField;

	private Color dark = new Color(36, 47, 65);
	private Color light = new Color(97, 212, 195);
	private Color silver = new Color(238, 238, 238);

	/* The register controller */
	private RegisterController controller;

	/**
	 * Create the panel.
	 */
	public RegisterPanel(RegisterController controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setBackground(dark);
		setLayout(null);
		registerPanelListener();

		Panel panel = new Panel(0, 0, 400, 500);
		panel.setBackground(light);
		add(panel);

		Label nameLabel = new Label("NAME", 105, 70, 107, 30, panel);
		nameLabel.setForeground(dark);

		Separator separator = new Separator(105, 134, 185, 12, panel);
		separator.setForeground(dark);

		nameField = new Field(105, 112, 185, 28, panel);
		nameField.setForeground(dark);
		nameField.setCaretColor(dark);
		nameField.setBackground(light);

		Label surnameLabel = new Label("SURNAME", 105, 204, 107, 30, panel);
		surnameLabel.setForeground(dark);

		Separator separator_1 = new Separator(105, 269, 185, 12, panel);
		separator_1.setForeground(dark);

		surnameField = new Field(105, 246, 185, 28, panel);
		surnameField.setForeground(dark);
		surnameField.setCaretColor(dark);
		surnameField.setBackground(light);

		Label emailLabel = new Label("EMAIL", 105, 344, 107, 30, panel);
		emailLabel.setForeground(dark);

		Separator separator_2 = new Separator(105, 409, 185, 12, panel);
		separator_2.setForeground(dark);

		emailField = new Field(105, 386, 185, 28, panel);
		emailField.setForeground(dark);
		emailField.setCaretColor(dark);
		emailField.setBackground(light);

		Label passwordLabel = new Label("PASSWORD", 505, 69, 107, 30, this);
		passwordLabel.setForeground(silver);

		Separator separator_3 = new Separator(505, 133, 185, 12, this);
		separator_3.setForeground(silver);

		passwordField = new PasswordField(505, 111, 185, 28, this);
		passwordField.setForeground(silver);
		passwordField.setCaretColor(silver);
		passwordField.setBackground(dark);

		Label confirmLabel = new Label("CONFIRM PASSWORD", 505, 203, 194, 30, this);
		confirmLabel.setForeground(silver);

		Separator separator_4 = new Separator(505, 268, 185, 12, this);
		separator_4.setForeground(silver);

		confirmField = new PasswordField(505, 245, 185, 28, this);
		confirmField.setForeground(silver);
		confirmField.setCaretColor(silver);
		confirmField.setBackground(dark);
		confirmFieldListener();

		register = new Button("", 505, 384, 185, 42, this);
		register.addIcon(submitPath);
		registerButtonListener();
	}
	
	private void registerPanelListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					register();
				}
			}
		});
	}
	
	private void registerButtonListener() {
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				register();
			}

			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	/* register a user based on field attributes */
	private void register() {
		controller.getModel().register(nameField.getText(), surnameField.getText(), passwordField.getText(), emailField.getText());
	}

	@SuppressWarnings("deprecation")
	/* Verify that there are no empty fields and
	 * that the passwords match */
	public boolean checkEmptyFields() {
		if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
			emailField.getText().isEmpty() || passwordField.getText().isEmpty() ||
			confirmField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "All Fields must be filled!");
			return true;
		}
		if (!passwordField.getText().equals(confirmField.getText())) {
			JOptionPane.showMessageDialog(null, "Passwords don't match!");
			return true;
		}
		return false;
	}
	
	private void confirmFieldListener() {
		confirmField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					register();
				}
			}
		});
	}
}
