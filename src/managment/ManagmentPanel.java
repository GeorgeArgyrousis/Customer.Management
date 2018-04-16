package managment;

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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import connection.DatabaseConnection;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManagmentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800, HEIGHT = 500;

	private int clickedId;

	private JTextField searchField;
	private JTable table;

	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblNumber;
	private JLabel lblEmail;

	private JTextField nameField;
	private JTextField surnameField;
	private JTextField numberField;
	private JTextField emailField;

	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;

	private String sName = "NAME";
	private String sSurname = "SURNAME";
	private String sNumber = "NUMBER";
	private String sEmail = "EMAIL";

	private String ls = System.getProperty("file.separator");

	private String addPath = "res" + ls + "add.png";
	private String updatePath = "res" + ls + "update.png";
	private String removePath = "res" + ls + "remove.png";
	private String colonPath = "res" + ls + "colon.png";
	private String dbPath = "db" + ls + "Customers.sqlite";

	private String searchQuery = "select Name,Surname,Number,Email from Data where Name=? or Surname=? or Number=? or Email=?";
	private String loadQuery = "select Name,Surname,Number,Email from Data where ID!=0";
	private String saveQuery = "insert into Data (ID,Name,Surname,Number,Email) values (?,?,?,?,?)";
	private String idQuery = "select ID from Data";
	private String updateQuery;
	private String removeQuery;
	private String clickQuery;

	private Color dark = new Color(36, 47, 65);
	private Color light = new Color(97, 212, 195);

	private DatabaseConnection appConnection;
	private Connection connection;
	private JLabel colon;

	public ManagmentPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setBackground(light);
		setLayout(null);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		appConnection = new DatabaseConnection(dbPath);
		this.connection = appConnection.connectToDatabase();

		JPanel functionPanel = new JPanel();
		functionPanel.setBackground(dark);
		functionPanel.setBounds(0, 0, 225, 500);
		add(functionPanel);
		functionPanel.setLayout(null);

		JButton add = new JButton("ADD");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addData();
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		BufferedImage img = null;
		ImageIcon icon = null;
		try {
			img = ImageIO.read(new File(addPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		add.setIcon(icon);
		add.setContentAreaFilled(false);
		add.setBorderPainted(false);
		add.setForeground(new Color(255, 255, 255));
		add.setBackground(light);
		add.setBounds(19, 207, 185, 42);
		functionPanel.add(add);

		JButton update = new JButton("UPDATE");
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateData();
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		try {
			img = ImageIO.read(new File(updatePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		update.setIcon(icon);
		update.setContentAreaFilled(false);
		update.setBorderPainted(false);
		update.setForeground(new Color(255, 255, 255));
		update.setBackground(new Color(97, 212, 195));
		update.setBounds(19, 291, 185, 42);
		functionPanel.add(update);

		JButton remove = new JButton("REMOVE");
		remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeData();
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		try {
			img = ImageIO.read(new File(removePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		remove.setIcon(icon);
		remove.setContentAreaFilled(false);
		remove.setBorderPainted(false);
		remove.setForeground(new Color(255, 255, 255));
		remove.setBackground(new Color(97, 212, 195));
		remove.setBounds(19, 375, 185, 42);
		functionPanel.add(remove);

		searchField = new JTextField();
		/*searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//loadTable();
			}
		});*/
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchDatabase();
			}
		});
		searchField.setCaretColor(Color.WHITE);
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchField.setText("");
			}
		});
		searchField.setText("Search...");
		searchField.setBounds(19, 129, 185, 28);
		searchField.setBackground(new Color(36, 47, 65));
		searchField.setForeground(new Color(255, 255, 255));
		searchField.setBorder(null);
		searchField.setColumns(10);
		functionPanel.add(searchField);

		JSeparator separator = new JSeparator();
		separator.setBounds(19, 152, 185, 12);
		functionPanel.add(separator);

		colon = new JLabel("");
		colon.setBounds(19, 18, 185, 90);
		try {
			img = ImageIO.read(new File(colonPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		colon.setIcon(icon);
		functionPanel.add(colon);

		JLabel lblNewLabel = new JLabel("George Argyrousis (C) 2017");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 478, 213, 16);
		functionPanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 10, 555, 290);
		scrollPane.setBorder(null);
		add(scrollPane);

		table = new JTable();
		table.setShowGrid(false);
		table.setForeground(dark);
		table.setSelectionBackground(light);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickToBox();
			}
		});
		loadTable();
		scrollPane.setViewportView(table);

		nameField = new JTextField();
		nameField.setBounds(300, 353, 185, 28);
		nameField.setBackground(new Color(97, 212, 195));
		nameField.setForeground(dark);
		nameField.setBorder(null);
		nameField.setColumns(10);
		add(nameField);

		surnameField = new JTextField();
		surnameField.setBounds(300, 424, 185, 28);
		surnameField.setBackground(new Color(97, 212, 195));
		surnameField.setForeground(dark);
		surnameField.setBorder(null);
		surnameField.setColumns(10);
		add(surnameField);

		numberField = new JTextField();
		numberField.setBounds(527, 353, 185, 28);
		numberField.setBackground(new Color(97, 212, 195));
		numberField.setForeground(dark);
		numberField.setBorder(null);
		numberField.setColumns(10);
		add(numberField);

		emailField = new JTextField();
		emailField.setBounds(527, 424, 186, 28);
		emailField.setBackground(new Color(97, 212, 195));
		emailField.setForeground(dark);
		emailField.setBorder(null);
		emailField.setColumns(10);
		add(emailField);

		separator_1 = new JSeparator();
		separator_1.setForeground(dark);
		separator_1.setBounds(300, 376, 185, 12);
		add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setForeground(dark);
		separator_2.setBounds(300, 447, 185, 12);
		add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setForeground(dark);
		separator_3.setBounds(527, 376, 185, 12);
		add(separator_3);

		separator_4 = new JSeparator();
		separator_4.setForeground(dark);
		separator_4.setBounds(527, 447, 185, 12);
		add(separator_4);

		lblName = new JLabel(sName);
		lblName.setForeground(dark);
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblName.setBounds(300, 322, 107, 30);
		add(lblName);

		lblSurname = new JLabel(sSurname);
		lblSurname.setForeground(dark);
		lblSurname.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblSurname.setBounds(300, 393, 107, 30);
		add(lblSurname);

		lblNumber = new JLabel(sNumber);
		lblNumber.setForeground(dark);
		lblNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNumber.setBounds(527, 322, 107, 30);
		add(lblNumber);

		lblEmail = new JLabel(sEmail);
		lblEmail.setForeground(dark);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblEmail.setBounds(527, 393, 107, 30);
		add(lblEmail);
	}

	public void clickToBox() {
		try {
			int row = table.getSelectedRow();
			String name = (table.getModel().getValueAt(row, 0)).toString();
			String surname = (table.getModel().getValueAt(row, 1)).toString();
			clickQuery = "select * from Data where Name='" + name + "' and Surname='" + surname + "'";
			PreparedStatement statement = this.connection.prepareStatement(clickQuery);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				clickedId = result.getInt("ID");
				nameField.setText(result.getString("Name"));
				surnameField.setText(result.getString("Surname"));
				numberField.setText(result.getString("Number"));
				emailField.setText(result.getString("Email"));
			}
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void searchDatabase(){
		try {
			PreparedStatement statement = this.connection.prepareStatement(searchQuery);
			String text = searchField.getText();
			if(searchField.getText().isEmpty()){
				loadTable();
				return;
			}
			statement.setString(1, text);
			statement.setString(2, text);
			statement.setString(3, text);
			statement.setString(4, text);
			ResultSet result = statement.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(result));
			
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
	public void addData() {
		if (checkEmptyFields()) {
			return;
		}
		try {
			PreparedStatement statement = this.connection.prepareStatement(saveQuery);
			statement.setString(1, Integer.toString(selectId()));
			statement.setString(2, nameField.getText());
			statement.setString(3, surnameField.getText());
			statement.setString(4, numberField.getText());
			statement.setString(5, emailField.getText());
			statement.execute();
			loadTable();
			clearBoxes();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateData() {
		try {
			updateQuery = "Update Data set Name='" + nameField.getText() + "',Surname='" + surnameField.getText() + "',Number='" + numberField.getText() + "',Email='" + emailField.getText() + "' where ID='" + clickedId + "'";
			PreparedStatement statement = this.connection.prepareStatement(updateQuery);
			statement.execute();
			loadTable();
			clearBoxes();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeData() {
		removeQuery = "delete from Data where ID='" + clickedId + "'";
		try {
			PreparedStatement statement = this.connection.prepareStatement(removeQuery);
			statement.execute();
			loadTable();
			clearBoxes();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int selectId() {
		int value = 0;
		int max = 0;
		try {
			PreparedStatement statement = this.connection.prepareStatement(idQuery);
			ResultSet result = statement.executeQuery();
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

	public void loadTable() {
		try {
			PreparedStatement statement = this.connection.prepareStatement(loadQuery);
			ResultSet result = statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(result));
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clearBoxes() {
		nameField.setText("");
		surnameField.setText("");
		numberField.setText("");
		emailField.setText("");
	}

	public boolean checkEmptyFields() {
		int count = 0;
		if (nameField.getText().isEmpty()) {
			count++;
		}
		if (surnameField.getText().isEmpty()) {
			count++;
		}
		if (numberField.getText().isEmpty()) {
			count++;
		}
		if (emailField.getText().isEmpty()) {
			count++;
		}
		if (count > 2) {
			return true;
		}
		return false;
	}
}
