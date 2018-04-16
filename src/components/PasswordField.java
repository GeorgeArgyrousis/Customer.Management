package components;

import java.awt.Color;


import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordField(int x, int y, int width, int height, JPanel parent) {
		setCaretColor(Color.WHITE);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(36, 47, 65));
		setBorder(null);
		setBounds(505, 292, 185, 28);
		parent.add(this);
	}
}
