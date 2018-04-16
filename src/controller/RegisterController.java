package controller;

import model.RegisterModel;
import view.register.RegisterFrame;
import view.register.RegisterPanel;

public class RegisterController {

	/* The register frame */
	private RegisterFrame frame;
	
	/* The register panel */
	private RegisterPanel panel;
	
	/* The register model */
	private RegisterModel model;
	
	/* Initiate the register components */
	public RegisterController() {
		model = new RegisterModel(this);
		panel = new RegisterPanel(this);
		frame = new RegisterFrame(panel);
	}
	
	public RegisterFrame getFrame() {
		return this.frame;
	}
	
	public RegisterPanel getPanel() {
		return this.panel;
	}
	
	public RegisterModel getModel() {
		return this.model;
	}
}
