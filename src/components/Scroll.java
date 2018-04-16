package components;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Scroll extends JScrollPane{
	
	private static final long serialVersionUID = 1L;

	public Scroll(int x, int y, int width, int height, JPanel parent) {
		setBounds(x, y, width, height);
		setBorder(null);
		parent.add(this);
	}

}
