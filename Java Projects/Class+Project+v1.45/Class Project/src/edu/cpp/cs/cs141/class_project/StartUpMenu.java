package edu.cpp.cs.cs141.class_project;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.Serializable;

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
public class StartUpMenu implements Serializable
{
	private JFrame frame;
	private GamePlayer gamePlay;
	int rows = 9;
	int col = 9;
	private JRadioButtonMenuItem rdbtnmntmEasyMode;
	private JRadioButtonMenuItem rdbtnmntmHardMode;

	/**
	 * Create the application.
	 */
	public StartUpMenu() 
	{
		gamePlay = new GamePlayer();
	}
	
	/**
	 * Launch the application.
	 */
	public void play() {
		initialize();
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Team StaticShock's Game");
		frame.setBounds(100, 100, 711, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gamePlay.getData().getGame().setGui(true);
				frame.dispose();
				try {
					gamePlay.createGrid();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(287, 356, 122, 33);
		frame.getContentPane().add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 704, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		

		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame load = new JFrame();
				load.setVisible(true);
				load.setAlwaysOnTop(true);
				JPanel pan = new JPanel();
				load.setBounds(600,300,420,200);
				load.setResizable(false);
				load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
				JLabel input = new JLabel("Enter name of the file you want to load. Press enter to confirm.");
				input.setAlignmentX(Component.CENTER_ALIGNMENT);
				pan.add(Box.createRigidArea(new Dimension(0,20)));
				pan.add(input);
				pan.add(Box.createRigidArea(new Dimension(0,20)));
				JTextField loadFile = new JTextField(30);
				pan.add(loadFile);
				load.add(pan);
				loadFile.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						// TODO Auto-generated method stub
						String fileName = loadFile.getText() +".dat";
						gamePlay.getData().load(fileName);
						load.dispose();
					}
				});
				
			}
		});
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		rdbtnmntmEasyMode = new JRadioButtonMenuItem("Easy Mode");
		rdbtnmntmEasyMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gamePlay.getData().getGame().setHardMode(false);
			}
		});
		mnOptions.add(rdbtnmntmEasyMode);
		
		rdbtnmntmHardMode = new JRadioButtonMenuItem("Hard Mode");
		rdbtnmntmHardMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gamePlay.getData().getGame().setHardMode(true);
			}
		});
		mnOptions.add(rdbtnmntmHardMode);
		ButtonGroup group= new ButtonGroup();
		group.add(rdbtnmntmEasyMode);
		mnOptions.addSeparator();
		group.add(rdbtnmntmHardMode);
		

		mnFile.add(mntmLoad);
		mnFile.add(mntmExit);
		
		JMenu mnMode = new JMenu("Mode");
		menuBar.add(mnMode);
		
		JRadioButtonMenuItem rdbtnmntmNormalMode = new JRadioButtonMenuItem("Normal Mode");
		rdbtnmntmNormalMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				gamePlay.getData().getGame().setDebug(false);
			}
		});
		mnMode.add(rdbtnmntmNormalMode);
		
		JRadioButtonMenuItem rdbtnmntmDebugMode = new JRadioButtonMenuItem("Debug Mode");
		rdbtnmntmDebugMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gamePlay.getData().getGame().setDebug(true);
			}
		});
		mnMode.add(rdbtnmntmDebugMode);
		
		ButtonGroup groupMode= new ButtonGroup();
		groupMode.add(rdbtnmntmNormalMode);
		groupMode.add(rdbtnmntmDebugMode);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 704, 441);
		frame.getContentPane().add(lblNewLabel);
		Image img = new ImageIcon(this.getClass().getResource("/Asylum.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
	}
}

