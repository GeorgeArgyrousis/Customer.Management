package view.management;

import javax.swing.JPanel;

import components.Frame;

public class ManagementFrame extends Frame{
	
	/* The default verison ID */
	private static final long serialVersionUID = 1L;
	
	/* The title of the frame */
	public static final String TITLE = "Management | Unnamed";

	public ManagementFrame(JPanel panel){
		super(TITLE, panel);
	}
}
