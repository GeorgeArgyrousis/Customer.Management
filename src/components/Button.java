package components;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JButton{
	
	private static final long serialVersionUID = 1L;

	public Button(String name, int x, int y, int width, int height, JPanel parent) {
		setText(name);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setBounds(x, y, width, height);
		parent.add(this);
	}
	
	public void addIcon(String path) {
		ImageIcon icon = null;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		setIcon(icon);
	}

}
