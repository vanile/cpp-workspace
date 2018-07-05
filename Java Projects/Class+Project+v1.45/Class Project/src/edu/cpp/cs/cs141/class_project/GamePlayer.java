package edu.cpp.cs.cs141.class_project;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import java.awt.event.*;

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
public class GamePlayer implements KeyListener, Serializable
{
	/**
	 * The object that is responsible for saving and loading.
	 * This also the GamePlayer to also be able to use save and load
	 */
	private Data game;
	/**
	 * A window that contains all the components of the GUI
	 */
	private JFrame frame;
	/**
	 * A panel that is used for organizing the 2d array.
	 * The layout that will be used with this panel is gridbaglayout
	 */
	private JPanel panel;
	/**
	 * An object that control the spacing of the components within the panel
	 * This control the spacing of the JLabels with the images
	 */
	private GridBagConstraints gbc;
	/**
	 * An object that control the spacing of the components within the panel
	 * This controls the spacing of the JLabels with text.
	 */
	private GridBagConstraints textGBC;
	/**
	 * The menu that contains many options for player to perform numerous tasks
	 * The 3 options are: File, Options, and Mode
	 */
	private JMenuBar menuBar;
	/**
	 * An exit option that exists in the menu under File
	 * This option will cause the whole entire JFrame to close.
	 */
	private JMenuItem mntmExit;
	/**
	 * A save option that exists in the menu under File
	 * This option allows the player to save the gameEngine and give a custom name to their save file
	 */
	private JMenuItem mntmSave;
	/**
	 * A load option that exists in the menu under File
	 * This option allows the player to load a gameEngine by specifying which file to load.
	 */
	private JMenuItem mntmLoad;

	/**
	 * A File category that exists within the menu.
	 * The file category contains options for saving, loading, and exiting.
	 */
	private JMenu mnFile;
	/**
	 * An options category that exists within the menu.
	 * The option category contains the two difficulties: Easy mode and Hard Mode
	 */
	private JMenu mnOptions;
	/**
	 * A option to change the game's mode to easy mode.
	 * In easy mode the enemy's movements are completely random.
	 * Since this is a radio button, this cannot be selected at the same time as hard mode
	 */
	private JRadioButtonMenuItem rdbtnmntmEasyMode;
	/**
	 * An option to change the game's mode to hard mode.
	 * In hard mode, the enemy has advanced AI and are capable of following the player
	 * Since this is a radio button, this cannot be selected at the same time as easy mode.
	 */
	private JRadioButtonMenuItem rdbtnmntmHardMode;
	/**
	 * The group that contains the two radio buttons for easy mode and hard mode
	 * This makes it so that only one of the two buttons can be pressed at a time.
	 */
	private ButtonGroup group;
	/**
	 * The group that contains the two radio buttons for normal mode and debug mode
	 * This makes is so that only one of the two buttons can be presssed at a time.
	 */
	private ButtonGroup debugGroup;
	/**
	 * This is the direction that the player is facing.
	 * 1) up 2) down 3) left 4) right
	 */
	private int direction;
	/**
	 * A category that exists within the menu.
	 * This category contains the options: Normal Mode and Debug Mode
	 */
	private JMenu mnMode;
	/**
	 * An option to change the game to normal mode
	 * In normal mode, the player can only see two space in the direction that they are looking.
	 * Since this is a radio button, this cannot be selected at the same time as debug mode
	 */
	private JRadioButtonMenuItem rdbtnmntmNormalMode;
	/**
	 * An option to change the game to debug mode
	 * In debug mode, the player can see all of the objects within the grid.
	 * Since this is a radio button, this cannot be selected at the same time as normal mode
	 */
	private JRadioButtonMenuItem rdbtnmntmDebugMode;
	
	
	/**
	 * The default constructor for the GamePlayer class
	 * In this constructor all of the objects are initialized.
	 * The JFrame is also set to a resolution of 1110 by 768 pixels
	 */
	public GamePlayer()
	{
		direction = 1;
		frame = new JFrame("Game");
		panel  = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		textGBC = new GridBagConstraints();
		menuBar = new JMenuBar();
		mnFile = new JMenu("File");
		mntmExit = new JMenuItem("Exit");
		mntmLoad = new JMenuItem("Load");
		mntmSave = new JMenuItem("Save");
		mnOptions = new JMenu("Options");
		group = new ButtonGroup();
		debugGroup = new ButtonGroup();
		frame.setBounds(100,100,1110,768);
		setUp();
	}
	
	/**
	 * Method used to add all the visual components to the Frame
	 * In addition all of the action listeners are created in this method
	 * Also the minimum amount of spacing between JLabels is initialized in this method 
	 * This method also sets up the grid that is in the gameEngine
	 */
	public void setUp()
	{
		frame.addKeyListener(this);
		frame.setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		menuBar.add(mnOptions);
		rdbtnmntmEasyMode = new JRadioButtonMenuItem("Easy Mode");
		rdbtnmntmEasyMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.getGame().setHardMode(false);
			}
		});
		mnOptions.add(rdbtnmntmEasyMode);
		
		rdbtnmntmHardMode = new JRadioButtonMenuItem("Hard Mode");
		rdbtnmntmHardMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.getGame().setHardMode(true);
			}
		});
		mnOptions.add(rdbtnmntmHardMode);
		
		group.add(rdbtnmntmEasyMode);
		group.add(rdbtnmntmHardMode);
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame save = new JFrame();
				save.setVisible(true);
				JPanel pan = new JPanel();
				save.setBounds(600,300,420,200);
				save.setResizable(false);
				save.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
				JLabel input = new JLabel("Enter name of the file you want to save. Press enter to confirm.");
				input.setAlignmentX(Component.CENTER_ALIGNMENT);
				pan.add(Box.createRigidArea(new Dimension(0,20)));
				pan.add(input);
				pan.add(Box.createRigidArea(new Dimension(0,20)));
				JTextField saveFile = new JTextField(30);
				pan.add(saveFile);
				save.add(pan);
				saveFile.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						// TODO Auto-generated method stub
						String fileName = saveFile.getText() +".dat";
						game.save(fileName);
						save.dispose();
					}
				});
			}
		});
		
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame load = new JFrame();
				load.setVisible(true);
				JPanel pan = new JPanel();
				load.setBounds(600,300,420,200);
				load.setResizable(false);
				load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pan.setLayout(new BoxLayout(pan,BoxLayout.PAGE_AXIS));
				JLabel input = new JLabel("Enter the name of the file you want to load. Press enter to confirm.");
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
						game.load(fileName);
						resetFrame();
						load.dispose();
					}
				});
			}
		});
		
		mnFile.add(mntmSave);
		mnFile.add(mntmLoad);
		mnFile.add(mntmExit);
		
		mnMode = new JMenu("Mode");
		menuBar.add(mnMode);
		
		rdbtnmntmNormalMode = new JRadioButtonMenuItem("Normal Mode");
		rdbtnmntmNormalMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				game.getGame().setDebug(false);
				resetFrame();
			}
		});
		mnMode.add(rdbtnmntmNormalMode);
		
		rdbtnmntmDebugMode = new JRadioButtonMenuItem("Debug  Mode");
		rdbtnmntmDebugMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				game.getGame().setDebug(true);
				resetFrame();
			}
		});
		
		debugGroup.add(rdbtnmntmNormalMode);
		debugGroup.add(rdbtnmntmDebugMode);
		mnMode.add(rdbtnmntmDebugMode);

		gbc.insets = new Insets(0,0,0,0);
		textGBC.insets = new Insets(0,100,0,50);
		game = new Data();
		game.getGame().getGrid().setUp();
	}
	
	/**
	 * Creates a JLabel with instructions on how to play the game
	 * This creates 3 JLabels with: instructions on how to shoot, instructions on how to move, and instructions on how to look
	 */
	public void showDirections()
	{
		JLabel shootingDir = new JLabel("Shoot: W (up) A (Left) S (Down) D (Right)");
		textGBC.gridx = 20;
		textGBC.gridy = 0;
		panel.add(shootingDir,textGBC);
		JLabel movingDir = new JLabel("Move: Arrow Keys");
		textGBC.gridx = 20;
		textGBC.gridy = 1;
		panel.add(movingDir,textGBC);
		JLabel lookingDir = new JLabel("Look: I (up) J (Left) K (Down) L (Right)");
		textGBC.gridx = 20;
		textGBC.gridy = 2;
		panel.add(lookingDir,textGBC);
		
		
	}
	
	/**
	 * Creates a JLabel that show how many turns the player is invincible for
	 * The maximum amount if 5 and at 0 the player is no longer invincible.
	 * This will update with every step as well.
	 */
	public void showInvincibility()
	{
		JLabel invincible = new JLabel("Invincible for " + game.getGame().getInvincible() + " turn(s)");
		textGBC.gridx = 20;
		textGBC.gridy = 8;
		panel.add(invincible,textGBC);
	}
	
	/**
	 * Creates a JLabel that states how many bullets you have left
	 * This Label is placed at row 6 and has a spacing of 50 pixels to the left and right
	 */
	public void showBullets()
	{
		JLabel bullets = new JLabel("Number of Bullets Remaining: " + game.getGame().getGun().getAmmo());
		textGBC.gridx = 20;
		textGBC.gridy = 7;
		panel.add(bullets,textGBC);
	}

	/**
	 * Creates a JLabel that shows how many lives the player has left.
	 * This will update every time a player loses a life.
	 * The game will end when you are out of lives.
	 */
	public void showLives()
	{
		JLabel lives = new JLabel("Number of Lives Remaining: " + game.getGame().getGrid().getPlayer().getLives());
		textGBC.gridx = 20;
		textGBC.gridy = 6;
		panel.add(lives,textGBC);
	}
	
	/**
	 * A method that will show the location of the briefcase/exit if you have the radar power-up
	 * Before the player acquires the radar, this will tell the player that the exit is unknown
	 */
	public void showBrief()
	{
		if(game.getGame().getRadarStatus())
		{
			JLabel radar = new JLabel("The exit is at (" + game.getGame().getGrid().getBriefRow() + "," + game.getGame().getGrid().getBriefCol() + ")");
			textGBC.gridx = 20;
			textGBC.gridy = 5;
			panel.add(radar,textGBC);
		}
		else if(game.getGame().getRadarStatus() != true)
		{
			JLabel radarNotFound = new JLabel("Exit Unknown");
			textGBC.gridx = 20;
			textGBC.gridy = 5;
			panel.add(radarNotFound,textGBC);
		}
	}
	
	/**
	 * A getter method for the gameEngine in the GamePlayer
	 * This method allows other classes to get access to the gameEngine.
	 * @return The game engine
	 */
	public Data getData()
	{
		return game;
	}
	
	/**
	 * This method creates the graphical representation of the 2d array.
	 * The amount of viewable objects printed in the grid will vary depending on the mode.
	 * 
	 * @throws FileNotFoundException Throws an error when the pictures for the objects are not found.
	 */
	public void createGrid() throws FileNotFoundException
	{
		if(game.getGame().getDebug())
		{
			createDebugGrid();
		}
		else
		{
//			createDebugGrid();
			game.getGame().getGrid().viewableTiles(direction);
			createNormalGrid();
		}
	}
	
	/**
	 * Creates a 2d graphical array.
	 * Almost everything in this grid will be black except for the player and two tiles that the player can see.
	 */
	public void createNormalGrid()
	{
		frame.setResizable(false);
		for(int r = 0; r <= 8; r++)
		{
			for(int c = 0; c <= 8; c++)
			{
				checkNormalObject(r,c);
			}
		}
		
		showBullets();
		showInvincibility();
		showDirections();
		showLives();
		showBrief();
		
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * This method checks to see if the object should be given a graphical representation or a black square
	 * This method will only acknowledge the player and the two tiles that are in the direction the player is looking.
	 * All other tiles will be pitch black since the player cannot see that far.
	 * 
	 * @param row the current row that is being checked
	 * @param col the current column that is being checked
	 */
	public void checkNormalObject(int row, int col)
	{
	    if(game.getGame().getGrid().getObject(row, col) instanceof Player)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Spy.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label, gbc);
		}
	    else if(game.getGame().getGrid().getObject(row, col) instanceof SeenTile)
		{
	    	JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/seenTile.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label, gbc);
		}
	    else if(game.getGame().getGrid().getObject(row, col) instanceof Enemy)
		{
	    	if(game.getGame().getGrid().getSeenCol()[1] == col && game.getGame().getGrid().getSeenRow()[1] == row)
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Guard.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label, gbc);
	    	}
	    	else if(game.getGame().getGrid().getSeenCol()[0] == col && game.getGame().getGrid().getSeenRow()[0] == row)
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Guard.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label, gbc);
	    	}
	    	else
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
		}
	    else if(game.getGame().getGrid().getObject(row, col) instanceof Invincibility)
		{
	    	if(game.getGame().getGrid().getSeenCol()[1] == col && game.getGame().getGrid().getSeenRow()[1] == row)
	    	{
				JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Knife.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else if(game.getGame().getGrid().getSeenCol()[0] == col && game.getGame().getGrid().getSeenRow()[0] == row)
	    	{
				JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Knife.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
		}
	    else if(game.getGame().getGrid().getObject(row, col) instanceof AdditionalBullet)
		{
	    	if(game.getGame().getGrid().getSeenCol()[1] == col && game.getGame().getGrid().getSeenRow()[1] == row)
	    	{
				JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Bullet.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else if(game.getGame().getGrid().getSeenCol()[0] == col && game.getGame().getGrid().getSeenRow()[0] == row)
	    	{
				JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Bullet.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
		}
	    else if(game.getGame().getGrid().getObject(row, col) instanceof Room)
		{
	    	if(game.getGame().getGrid().getSeenCol()[1] == col && game.getGame().getGrid().getSeenRow()[1] == row)
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Door.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else if(game.getGame().getGrid().getSeenCol()[0] == col && game.getGame().getGrid().getSeenRow()[0] == row)
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Door.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
		}
	    else if(game.getGame().getGrid().getObject(row, col) instanceof Radar)
		{
	    	if(game.getGame().getGrid().getSeenCol()[1] == col && game.getGame().getGrid().getSeenRow()[1] == row)
	    	{
				JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Old Paper.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else if(game.getGame().getGrid().getSeenCol()[0] == col && game.getGame().getGrid().getSeenRow()[0] == row)
	    	{
				JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Old Paper.png")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
	    	else
	    	{
	    		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
				label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				gbc.gridx = col;
				gbc.gridy = row;
				panel.add(label,gbc);
	    	}
		}
	    else
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
	    
	}
	
	/**
	 * A method that creates a visual representation of all the objects within the grid using a JFrame and multiple JLabels
	 * @throws FileNotFoundException If the image doesn't exist, this exception will be thrown
	 */
	public void createDebugGrid()
	{
		frame.setResizable(false);
		for(int r = 0; r <= 8; r++)
		{
			for(int c = 0; c <= 8; c++)
			{
				checkDebugObject(r,c);
			}
		}
		
		showBullets();
		showInvincibility();
		showDirections();
		showLives();
		showBrief();
		
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	/**
	 * A method that access the Component at [row][col] and removes that component
	 * Afterwards a new JLabel is placed at that location
	 * 
	 * @param row The row which you wish to access
	 * @param col The column which you wish to access
	 * @param label The JLabel you want to replace the current component with
	 */
	public void replaceComponent(int row, int col, JLabel label)
	{
		Component toRemove = panel.getComponent((row*9) + col);
		panel.remove(toRemove);
		gbc.gridx = row;
		gbc.gridy = col;
		panel.add(label,gbc);
	}
	
	/**
	 * A method that checks the grid and determines which JLabel to use for the corresponding object
	 * Each JLabel has a different picture depending on the object
	 * After choosing the picture, this method will add that JLabel to the JPanel with a GridBagLayout
	 * 
	 * @param row The row which you wish to access
	 * @param col The column which you wish to access
	 */
	public void checkDebugObject(int row, int col)
	{
		if(game.getGame().getGrid().getObject(row, col) instanceof Enemy)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Guard.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label, gbc);
		}
		else if(game.getGame().getGrid().getObject(row, col) instanceof Player)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Spy.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label, gbc);
		}
		else if(game.getGame().getGrid().getObject(row, col) instanceof Invincibility)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Knife.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
		else if(game.getGame().getGrid().getObject(row, col) instanceof AdditionalBullet)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Bullet.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
		else if(game.getGame().getGrid().getObject(row, col) instanceof RoomWithBrief)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Exit.jpg")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
		else if(game.getGame().getGrid().getObject(row, col) instanceof Room)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Door.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
		else if(game.getGame().getGrid().getObject(row, col) instanceof Radar)
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Old Paper.png")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
		else
		{
			JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Black2.jpg")), JLabel.CENTER);
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			gbc.gridx = col;
			gbc.gridy = row;
			panel.add(label,gbc);
		}
	}
	
	
	/**
	 * A method that removes all components from the panel.
	 * After that, this method recreates the graphical 2d array in order to show any changes.
	 */
	public void resetFrame()
	{
		panel.removeAll();
		try {
			createGrid();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks around the players for enemies and kills the player if there are enemies
	 * If there are no enemies then this method will check if you are invincible or not and will decrease invincibility if you are
	 * Finally this method will cause the enemies to move
	 */
	public void moveAction()
	{
		if(game.getGame().checkMoved())
		{
			game.getGame().checkForEnemies();
			game.getGame().checkInvincible();
		}
		game.getGame().enemiesMove(game.getGame().getMode());
	}
	
	/**
	 * This method will check if you are invincible and will decrease invincibility if you are.
	 * Afterwards all enemies will move.
	 */
	public void shootAction()
	{
		if(game.getGame().checkMoved())
		{
			game.getGame().checkInvincible();
		}
		game.getGame().enemiesMove(game.getGame().getMode());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * 
	 * Method that controls the characters actions
	 * You can move with the arrow keys and shoot with WASD.
	 * You can also look with IJKL
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(game.getGame().checkAlive() && !game.getGame().checkWin())
		{
			int keyCode = e.getKeyCode();
			switch(keyCode)
			{
				case KeyEvent.VK_UP:
					//move up
					game.getGame().playerMove(1);
					moveAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_DOWN:
					//move down
					game.getGame().playerMove(2);
					moveAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_LEFT:
					//move left
					game.getGame().playerMove(3);
					moveAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_RIGHT:
					//move right
					game.getGame().playerMove(4);
					moveAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_W:
					//shoot up
					game.getGame().shootUp();
					shootAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_S:
					//shoot down
					game.getGame().shootDown();
					shootAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_A:
					//shoot left
					game.getGame().shootLeft();
					shootAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_D:
					//shoot right
					game.getGame().shootRight();
					shootAction();
					resetFrame();
					game.getGame().setLooked(false);
					break;
				case KeyEvent.VK_I:
					//look Up
					if(!game.getGame().getLooked())
					{
						game.getGame().lookGui();
						if(game.getGame().getDebug())
						{
							resetFrame();
						}
						else
						{
							game.getGame().getGrid().viewableTiles(1);
							direction = 1;
							resetFrame();
						}
					}
					else
					{
						game.getGame().lookGui();
					}
					
					break;
				case KeyEvent.VK_J:
					//look Left
					if(!game.getGame().getLooked())
					{
						game.getGame().lookGui();
						if(game.getGame().getDebug())
						{
							resetFrame();
						}
						else
						{
							game.getGame().getGrid().viewableTiles(3);
							direction = 3;
							resetFrame();
						}
					}
					else
					{
						game.getGame().lookGui();
					}
					break;
				case KeyEvent.VK_K:
					//look Down
					if(!game.getGame().getLooked())
					{
						game.getGame().lookGui();
						if(game.getGame().getDebug())
						{
							resetFrame();
						}
						else
						{
							game.getGame().getGrid().viewableTiles(2);
							direction = 2;
							resetFrame();
						}
					}
					else
					{
						game.getGame().lookGui();
					}
					break;
				case KeyEvent.VK_L:
					//look Right
					if(!game.getGame().getLooked())
					{
						game.getGame().lookGui();
						if(game.getGame().getDebug())
						{
							resetFrame();
						}
						else
						{
							game.getGame().getGrid().viewableTiles(4);
							direction = 4;
							resetFrame();
						}
					}
					else
					{
						game.getGame().lookGui();
					}
					break;
		}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		//This method is not used
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		//This method is not used
		// TODO Auto-generated method stub
		
	}

}
