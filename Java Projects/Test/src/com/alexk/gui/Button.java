package com.alexk.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Button extends WindowsComponent {

	private JButton button;
	
	public Button(String name) {
		button = new JButton(name);
		operation();
	}
	
	public JComponent getComponent() {
		return button;
	}

	@Override
	public void operation() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello");
			}
		});

		
	}
}
