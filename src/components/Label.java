package components;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Label extends JLabel{

	private static final long serialVersionUID = 1L;

	public Label(String name, int x, int y, int width, int height, JPanel parent) {
		setText(name);
		setFont(new Font("Arial", Font.PLAIN, 18));
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
