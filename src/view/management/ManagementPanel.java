package view.management;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import components.Button;
import components.Field;
import components.Label;
import components.Panel;
import components.Scroll;
import components.Separator;
import controller.ManagementController;

public class ManagementPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 800, HEIGHT = 500;

	private JTable table;

	private Label lblName;
	private Label lblSurname;
	private Label lblNumber;
	private Label lblEmail;
	private Label colon;
	
	private Button add;
	private Button update;
	private Button remove;

	private Field nameField;
	private Field surnameField;
	private Field numberField;
	private Field emailField;
	private Field searchField;

	private Separator separator_1;
	private Separator separator_2;
	private Separator separator_3;
	private Separator separator_4;

	private String ls = System.getProperty("file.separator");

	private String addPath = "res" + ls + "add.png";
	private String updatePath = "res" + ls + "update.png";
	private String removePath = "res" + ls + "remove.png";
	private String colonPath = "res" + ls + "colon.png";

	private Color dark = new Color(36, 47, 65);
	private Color light = new Color(97, 212, 195);
	
	private ManagementController controller;

	public ManagementPanel(ManagementController controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setBackground(light);
		setLayout(null);

		Panel functionPanel = new Panel(0, 0, 225, 500);
		functionPanel.setBackground(dark);
		add(functionPanel);

		add = new Button("ADD", 19, 207, 185, 42, functionPanel);
		add.addIcon(addPath);
		add.setForeground(new Color(255, 255, 255));
		add.setBackground(light);
		addListener();

		update = new Button("UPDATE", 19, 291, 185, 42, functionPanel);
		update.addIcon(updatePath);
		update.setForeground(new Color(255, 255, 255));
		update.setBackground(new Color(97, 212, 195));
		updateListener();

		remove = new Button("REMOVE", 19, 375, 185, 42, functionPanel);
		remove.addIcon(removePath);
		remove.setForeground(new Color(255, 255, 255));
		remove.setBackground(new Color(97, 212, 195));
		removeListener();

		searchField = new Field(19, 129, 185, 28, functionPanel);
		searchField.setText("Search...");
		searchField.setCaretColor(Color.WHITE);
		searchField.setBackground(new Color(36, 47, 65));
		searchField.setForeground(new Color(255, 255, 255));
		searchFieldListener();

		new Separator(19, 152, 185, 12, functionPanel);

		colon = new Label("", 19, 18, 185, 90, functionPanel);
		colon.addIcon(colonPath);

		Label lblNewLabel = new Label("George Argyrousis @ 2017",6, 478, 213, 16, functionPanel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		Scroll scrollPane = new Scroll(235, 10, 555, 290, this);

		table = new JTable();
		table.setShowGrid(false);
		table.setForeground(dark);
		table.setSelectionBackground(light);
		tableListener();
		scrollPane.setViewportView(table);

		nameField = new Field(300, 353, 185, 28, this);
		nameField.setCaretColor(dark);
		nameField.setBackground(new Color(97, 212, 195));
		nameField.setForeground(dark);

		surnameField = new Field(300, 424, 185, 28, this);
		surnameField.setCaretColor(dark);
		surnameField.setBackground(new Color(97, 212, 195));
		surnameField.setForeground(dark);

		numberField = new Field(527, 353, 185, 28, this);
		numberField.setCaretColor(dark);
		numberField.setBackground(new Color(97, 212, 195));
		numberField.setForeground(dark);

		emailField = new Field(527, 424, 186, 28, this);
		emailField.setCaretColor(dark);
		emailField.setBackground(new Color(97, 212, 195));
		emailField.setForeground(dark);

		separator_1 = new Separator(300, 376, 185, 12, this);
		separator_1.setForeground(dark);

		separator_2 = new Separator(300, 447, 185, 12, this);
		separator_2.setForeground(dark);

		separator_3 = new Separator(527, 376, 185, 12, this);
		separator_3.setForeground(dark);

		separator_4 = new Separator(527, 447, 185, 12, this);
		separator_4.setForeground(dark);

		lblName = new Label("NAME", 300, 322, 107, 30, this);
		lblName.setForeground(dark);
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

		lblSurname = new Label("SURNAME", 300, 393, 107, 30, this);
		lblSurname.setForeground(dark);
		lblSurname.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

		lblNumber = new Label("NUMBER", 527, 322, 107, 30, this);
		lblNumber.setForeground(dark);
		lblNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 18));

		lblEmail = new Label("EMAIL", 527, 393, 107, 30, this);
		lblEmail.setForeground(dark);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	}
	
	private void addListener() {
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.getModel().addData(getTName().getText(), getSurname().getText(), getNumber().getText(), getEmail().getText());
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
	
	private void updateListener() {
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.getModel().updateData(getTName().getText(), getSurname().getText(), getNumber().getText(), getEmail().getText());
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
	
	private void removeListener() {
		remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.getModel().removeData();
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
	
	private void tableListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.getModel().clickToBox(getTable());
			}
		});
	}
	
	private void searchFieldListener() {
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				searchField.setText("Search...");
			}
		});
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				controller.getModel().searchDatabase(getSearch().getText());
			}
		});
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchField.setText("");
			}
		});
	}

	/* Clear all the textFields */
	public void clearBoxes() {
		nameField.setText("");
		surnameField.setText("");
		numberField.setText("");
		emailField.setText("");
	}

	public boolean checkEmptyFields() {
		if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
				numberField.getText().isEmpty() || emailField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "All Fields must be filled!");
			return true;
		}
		return false;
	}
	
	public Field getSearch() {
		return searchField;
	}
	
	public Field getTName() {
		return nameField;
	}

	public Field getSurname() {
		return surnameField;
	}

	public Field getNumber() {
		return numberField;
	}

	public Field getEmail() {
		return emailField;
	}
	
	public JTable getTable() {
		return this.table;
	}
}
