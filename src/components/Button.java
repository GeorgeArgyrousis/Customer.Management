package components;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JButton{
	
	public Button(String name, int x, int y, int width, int height, JPanel parent) {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setBounds(x, y, width, height);
		parent.add(this);
	}

}
