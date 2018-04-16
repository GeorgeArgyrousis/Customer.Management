package view.management;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagementFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final String TITLE = "Management | Unnamed";

	public ManagementFrame(JPanel panel){
		setTitle(TITLE);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
