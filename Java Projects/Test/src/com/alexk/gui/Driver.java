package com.alexk.gui;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Driver {

	public static void main(String[] args) {
		new Driver();
	}
	
	public Driver() {
		ControlPanel cp = new ControlPanel();
		
		WindowsComponent button = new Button("Yess");
		cp.addComponent(button.getComponent());
	
		cp.display();
	}

}
