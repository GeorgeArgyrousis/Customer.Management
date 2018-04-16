package register;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final String TITLE = "";

	public RegisterFrame() {
		setTitle(TITLE);
		setLayout(new BorderLayout());
		add(new RegisterPanel(this), BorderLayout.CENTER);
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
