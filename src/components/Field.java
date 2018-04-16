package components;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Field extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Field(int x, int y, int width, int height, JPanel parent) {
		setCaretColor(Color.WHITE);
		setBackground(new Color(36, 47, 65));
		setForeground(new Color(255, 255, 255));
		setBorder(null);
		setBounds(x, y, width, height);
		setColumns(10);
		parent.add(this);
	}
}
