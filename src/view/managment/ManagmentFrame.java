package view.managment;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ManagmentFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final String TITLE = "";

	public ManagmentFrame(){
		setTitle(TITLE);
		setLayout(new BorderLayout());
		add(new ManagmentPanel(), BorderLayout.CENTER);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
