package com.alexk.gui;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class WindowsComponent extends JComponent{
	
	public JComponent getComponent() {
		return this;
	}
	
	public abstract void operation();
}
