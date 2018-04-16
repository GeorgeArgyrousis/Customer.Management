package register;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

import connection.DatabaseConnection;
import controller.LoginController;

public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800, HEIGHT = 500;

	private String ls = System.getProperty("file.separator");

	private String dbPath = "db" + ls + "Customers.sqlite";
	private String submitPath = "res" + ls + "submit.png";

	private String registerQuery = "insert into Data (ID,Name,Surname,Password,Email) values (?,?,?,?,?)";

	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;

	private Color dark = new Color(36, 47, 65);
	private Color light = new Color(97, 212, 195);
	private Color silver = new Color(238, 238, 238);

	private DatabaseConnection appConnection;
	private Connection connection;

	private JFrame frame;

	/**
	 * Create the panel.
	 */
	public RegisterPanel(JFrame frame) {
		this.frame = frame;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setBackground(dark);
		setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		appConnection = new DatabaseConnection(dbPath);
		this.connection = appConnection.connectToDatabase();

		JPanel panel = new JPanel();
		panel.setBackground(light);
		panel.setBounds(0, 0, 400, 500);
		add(panel);
		panel.setLayout(null);

		JLabel nameLabel = new JLabel("NAME");
		nameLabel.setForeground(dark);
		nameLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		nameLabel.setBounds(105, 70, 107, 30);
		panel.add(nameLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(dark);
		separator.setBounds(105, 134, 185, 12);
		panel.add(separator);

		nameField = new JTextField();
		nameField.setForeground(dark);
		nameField.setColumns(10);
		nameField.setCaretColor(dark);
		nameField.setBorder(null);
		nameField.setBackground(light);
		nameField.setBounds(105, 112, 185, 28);
		panel.add(nameField);

		JLabel surnameLabel = new JLabel("SURNAME");
		surnameLabel.setForeground(dark);
		surnameLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		surnameLabel.setBounds(105, 204, 107, 30);
		panel.add(surnameLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(dark);
		separator_1.setBounds(105, 269, 185, 12);
		panel.add(separator_1);

		surnameField = new JTextField();
		surnameField.setForeground(dark);
		surnameField.setColumns(10);
		surnameField.setCaretColor(dark);
		surnameField.setBorder(null);
		surnameField.setBackground(light);
		surnameField.setBounds(105, 246, 185, 28);
		panel.add(surnameField);

		JLabel emailLabel = new JLabel("EMAIL");
		emailLabel.setForeground(dark);
		emailLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		emailLabel.setBounds(105, 344, 107, 30);
		panel.add(emailLabel);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(dark);
		separator_2.setBounds(105, 409, 185, 12);
		panel.add(separator_2);

		emailField = new JTextField();
		emailField.setForeground(dark);
		emailField.setColumns(10);
		emailField.setCaretColor(dark);
		emailField.setBorder(null);
		emailField.setBackground(light);
		emailField.setBounds(105, 386, 185, 28);
		panel.add(emailField);

		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setForeground(silver);
		passwordLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		passwordLabel.setBounds(505, 69, 107, 30);
		add(passwordLabel);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(silver);
		separator_3.setBounds(505, 133, 185, 12);
		add(separator_3);

		passwordField = new JPasswordField();
		passwordField.setForeground(silver);
		passwordField.setColumns(10);
		passwordField.setCaretColor(silver);
		passwordField.setBorder(null);
		passwordField.setBackground(dark);
		passwordField.setBounds(505, 111, 185, 28);
		add(passwordField);

		JLabel confirmLabel = new JLabel("CONFIRM PASSWORD");
		confirmLabel.setForeground(silver);
		confirmLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		confirmLabel.setBounds(505, 203, 194, 30);
		add(confirmLabel);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(silver);
		separator_4.setBounds(505, 268, 185, 12);
		add(separator_4);

		confirmField = new JPasswordField();
		confirmField.setForeground(silver);
		confirmField.setColumns(10);
		confirmField.setCaretColor(silver);
		confirmField.setBorder(null);
		confirmField.setBackground(dark);
		confirmField.setBounds(505, 245, 185, 28);
		add(confirmField);

		JButton register = new JButton("");
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
		register.setBounds(505, 384, 185, 42);
		BufferedImage img = null;
		ImageIcon icon = null;
		try {
			img = ImageIO.read(new File(submitPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		register.setIcon(icon);
		register.setContentAreaFilled(false);
		register.setBorderPainted(false);
		add(register);
	}

	@SuppressWarnings("deprecation")
	public void register() {
		if (checkEmptyFields()) {
			return;
		}
		try {
			PreparedStatement statement = this.connection.prepareStatement(registerQuery);
			statement.setString(1, "0");
			statement.setString(2, nameField.getText());
			statement.setString(3, surnameField.getText());
			statement.setString(4, passwordField.getText());
			statement.setString(5, emailField.getText());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		frame.dispose();
		new LoginController();
	}

	@SuppressWarnings("deprecation")
	public boolean checkEmptyFields() {
		int count = 0;
		if (nameField.getText().isEmpty()) {
			count++;
		}
		if (surnameField.getText().isEmpty()) {
			count++;
		}
		if (emailField.getText().isEmpty()) {
			count++;
		}
		if (passwordField.getText().isEmpty()) {
			count++;
		}
		if (confirmField.getText().isEmpty()) {
			count++;
		}
		if (!passwordField.getText().equals(confirmField.getText())) {
			JOptionPane.showMessageDialog(null, "Passwords don't match!");
			return true;
		}
		if (count != 0) {
			JOptionPane.showMessageDialog(null, "All Fields must be filled!");
			return true;
		}
		return false;
	}
}
