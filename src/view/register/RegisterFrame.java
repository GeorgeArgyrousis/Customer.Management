package view.register;

import javax.swing.JPanel;

import components.Frame;

public class RegisterFrame extends Frame {

	/* The default version id*/
	private static final long serialVersionUID = 1L;
	
	/* The title of this component */
	public static final String TITLE = "Unnamed | Register";
	
	/* Initiate all relevant components */
	public RegisterFrame(JPanel panel) {
		super(TITLE, panel);
	}
}
