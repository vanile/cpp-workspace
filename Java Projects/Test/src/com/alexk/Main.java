/**
 * 
 */
package com.alexk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author thefurryman
 *
 */
public class Main extends JFrame {

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
	
	public static void main(String[] args) {
		new Main();
	}
		
	public Main() {
		super("Basic Swing App");
		
		setSize(400,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p.add(b);
		t.setEditable(false);
		p.add(t);
		p.add(ta);
		p.add(l);
		p.add(cb);
		add(p);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Main2 main = new Main2();
				//main.newScreen();
				Nice nice = Nice.getInstance();
			}
		});
		t.setText("nice");
		setVisible(true);
	}
}
