package view.register;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RegisterFrame extends JFrame {

	/* The default version id*/
	private static final long serialVersionUID = 1L;
	
	/* The title of this component */
	public static final String TITLE = "Unnamed | Register";
	
	/* Initiate all relevant components */
	public RegisterFrame(JPanel panel) {
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
