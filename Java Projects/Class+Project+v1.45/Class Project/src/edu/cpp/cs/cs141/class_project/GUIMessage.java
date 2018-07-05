package edu.cpp.cs.cs141.class_project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.*;
/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment: Class Project
 *
 * In this assignment, our team created a two dimensional grid game. 
 * The grid is 9 by 9 with 9 rooms equally distributed throughout the grid with one of them having the briefcase. 
 * That briefcase is the goal that the spy (the player) is trying to capture. 
 * The player must traverse through the grid while avoiding enemies. 
 * The player can only see two spaces in front of him and cannot tell if the room has the briefcase without checking the room. 
 * The player is also aided by power-ups. 
 * Once the player finds the briefcase they win. 
 * However, if they die three times, they lose.
 *
 * Team: Static Shock
 *   -Alexander Kimea
 *   -George Tong
 *   -Joshua Tellez
 *   -Ray Zuniga
 *   -Valerie Orozco
 */
public class GUIMessage  implements Serializable
{
	public GUIMessage()
	{
		
	}
	
	/**
	 * Creates a pop up that will always be on top of the other windows.
	 * This pop up will have a message and an ok button that will close the pop up
	 * @param message The message that you want to be in the pop up
	 */
	public void dialog(String message)
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setBounds(600, 300, 320, 144);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		JLabel error = new JLabel(message);
		error.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(error);
		panel.add(Box.createRigidArea(new Dimension(0,30)));
		JButton quit = new JButton("Ok");
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		panel.add(quit);
		frame.add(panel);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}
