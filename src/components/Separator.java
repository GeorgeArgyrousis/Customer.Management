package components;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Separator extends JSeparator{
	
	private static final long serialVersionUID = 1L;

	public Separator(int x, int y, int width, int height, JPanel parent) {
		setForeground(Color.WHITE);
		setBounds(x, y, width, height);
		parent.add(this);
	}

}
