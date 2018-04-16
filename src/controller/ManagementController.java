package controller;

import model.ManagementModel;
import view.management.ManagementFrame;
import view.management.ManagementPanel;

public class ManagementController {

	private ManagementFrame frame;
	private ManagementPanel panel;
	private ManagementModel model;
	
	public ManagementController() {
		panel = new ManagementPanel(this);
		model = new ManagementModel(this);
		frame = new ManagementFrame(panel);
	}

	public ManagementFrame getFrame() {
		return frame;
	}

	public ManagementPanel getPanel() {
		return panel;
	}

	public ManagementModel getModel() {
		return model;
	}
	
	
}
