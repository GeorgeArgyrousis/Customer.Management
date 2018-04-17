package components;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private int x, y;

	public Frame(String TITLE, JPanel panel) {
		setTitle(TITLE);
		setUndecorated(true);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		setLocation(getLocation().x + e.getX() - x,
	    getLocation().y + e.getY() - y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
        y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
