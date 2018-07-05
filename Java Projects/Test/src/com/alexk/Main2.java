package com.alexk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main2 extends JFrame {

	JPanel p = new JPanel();
	JButton b = new JButton("Button");
	JTextField t = new JTextField("TextField", 20);
	JTextArea ta = new JTextArea("Text\nArea", 5, 20);
	JLabel l = new JLabel("What's up");
	String choices[] = {
			"Hallo",
			"Bonjour",
			"Hola"
	};
	JComboBox cb = new JComboBox(choices);
		
	public Main2() {
		super("Basic Swing App 2");
		
		setSize(400,400);
		setResizable(false);
		
		p.add(b);
		t.setEditable(false);
		p.add(t);
		p.add(ta);
		p.add(l);
		p.add(cb);
		add(p);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main2 main = new Main2();
				//main.newScreen();
			}
		});
		t.setText("nice");
		setVisible(true);
	}
}
