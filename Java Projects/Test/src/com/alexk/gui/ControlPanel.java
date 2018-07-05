package com.alexk.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlPanel extends JFrame {

	private JPanel panel;
	
	public ControlPanel() {
		super("Control Panel");
		panel = new JPanel();
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
//		JButton b = new JButton("doug");
//		addComponent(b);
//		addComponent();
	}
	
	public void addComponent(JComponent c) {
		panel.add(c);
		add(panel);
		//panel.repaint();
	}
	
	public void addComponent() {
		JButton b = new JButton("4head");
		panel.add(b);
	}
	public void display() {
		add(panel);
		setVisible(true);
	}
	
	public JComponent getComponent() {
		return panel;
	}
}
