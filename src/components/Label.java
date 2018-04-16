package components;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Label extends JLabel{

	private static final long serialVersionUID = 1L;

	public Label(String name, int x, int y, int width, int height, JPanel parent) {
		setText(name);
		setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		setBounds(x, y, width, height);
		parent.add(this);
	}
}
