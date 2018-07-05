package edu.cpp.cs.cs141.class_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
public class Data 
{
	/**
	 * The game engine that controls the actions of the game
	 */
	private GameEngine game;

	/**
	 * A user-interface that allows the user to interact with the game.
	 */
	private UserInterface userI;
	
	/**
	 * The default constructor of the Data object
	 * By default the gameEngine and user-interface are initialized
	 */
	public Data()
	{
		game = new GameEngine();
		userI = new UserInterface();
	}
	
	/**
	 * A getter method for the private object GameEngine
	 * This allows other objects to access the gameEngine through the data class.
	 * 
	 * @return the gameEngine in a Data object
	 */
	public GameEngine getGame() 
	{
		return game;
	}
	
	/**
	 * The method that allows the users to play the game 
	 * At first a main menu is shown to the players
	 * In the main menu, the players may choose their difficulty and their mode
	 * They also have access to a GUI.
	 * Afterwards, this method allows players to do multiple actions such as: moving, shooting, looking, saving, and loading
	 * The GUI can also be opened at any time during the game, but the GUI will start a new game upon clicking the start game button.
	 */
	public void play()
	{
		userI.welcomeMsg();
		int main = userI.mainMenu();
		switch(main){
		case 1:
			break;
		case 2: 
			load(userI.promptLoad());
			if(game.getDebug())
			{
				game.getGrid().printDebugModeGrid();
			}
			else
			{
				game.getGrid().viewableTiles(game.getDirection());
				game.getGrid().printGrid();
			}
			userI.gameLoaded();
			break;
		case 3:
			game.setHardMode(true);
			break;
		case 4:
			game.setDebug(true);
			break;
		case 5:
			game.setHardMode(true);
			game.setDebug(true);
			break;	
		case 6:
			game.getVisualGame().playGame();
			break;
		case 7:
			System.exit(0);
		}
		if(main != 6)
		{
			game.setGui(false);
		}
		if(game.isGui() == false)
		{
			game.getGrid().setUp();
			if(game.getDebug())
				game.getGrid().printDebugModeGrid();
			else{
				game.getGrid().viewableTiles(game.getDirection());
				game.getGrid().printGrid();
			}
			int choice = userI.userOptions();
			while(!game.checkWin() && game.checkAlive() && game.isGui() == false)
			{
				switch(choice)
				{
				case 1:
					game.playerMove(userI.moveDirection());
					if(game.isHasMoved())
					{
						game.checkForEnemies();
						game.checkInvincible();
					}
					game.enemiesMove(game.getMode());
					if(game.checkAlive() && !game.checkWin())
					{
						if(game.getDebug())
							game.getGrid().printDebugModeGrid();
						else{
							game.getGrid().viewableTiles(game.getDirection());
							game.getGrid().printGrid();
						}
					}
					break;
				case 2:
					game.look();
					if(game.getDebug())
						game.getGrid().printDebugModeGrid();
					else{
						game.getGrid().viewableTiles(game.getDirection());
						game.getGrid().printGrid();
					}
					break;
				case 3:
					game.shoot();
					if(game.isHasMoved())
					{
						game.checkInvincible();
					}
					game.enemiesMove(game.getMode());
					if(game.getDebug())
						game.getGrid().printDebugModeGrid();
					else{
						game.getGrid().viewableTiles(game.getDirection());
						game.getGrid().printGrid();
					}
					break;
				case 4:
					save(userI.promptSave());
					userI.gameSaved();
					break;
				case 5:
					load(userI.promptLoad());
					if(game.getDebug())
					{
						game.getGrid().printDebugModeGrid();
					}
					else
					{
						game.getGrid().viewableTiles(game.getDirection());
						game.getGrid().printGrid();
					}
					userI.gameLoaded();
					break;
				case 6:
					game.setDebug(!game.getDebug());
					if(game.getDebug())
					{
						game.getGrid().removeSeenTiles();
						game.getGrid().printDebugModeGrid();
					}
					else
					{
						game.getGrid().viewableTiles(game.getDirection());
						game.getGrid().printGrid();
					}
					userI.debugMode(game.getDebug());
					break;
				case 7:
					game.setHardMode(!game.getMode());
					if(game.getDebug())
					{
						game.getGrid().printDebugModeGrid();
					}
					else
					{
						game.getGrid().viewableTiles(game.getDirection());
						game.getGrid().printGrid();
					}
					userI.hardMode(game.getMode());
					break;
				case 8:
					game.setGui(true);
					game.getVisualGame().playGame();
					break;
				case 9:
					System.exit(0);
					break;
				default:
					userI.invalid();
					break;
				}
				if (choice != 2 && choice != 4 && choice != 5 && choice != 6 && choice != 7)
				{
					//allows you to look again
					game.setLooked(false);
				}
				if(!game.checkWin() && game.checkAlive() && game.isGui() == false)
				{
					choice = userI.userOptions();
				}
			}
		}
	}
	
	/**
	 * Loads the file
	 * If the file is not available, the user will get a message telling them that the file was not found.
	 * The user can then try to load the file again through the console.
	 * 
	 * @param loadFile the name of the file you wish to load
	 */
	public void load(String loadFile)
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(loadFile);
		} catch (FileNotFoundException e) 
		{
			userI.fileNotFound();
			load(userI.promptLoad());
			
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			game = (GameEngine) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves a file and gives the file a specific lower-case name.
	 * The object that is saved is the gameEngine which encapsulates many other objects
	 * These are saved as a .dat file
	 * 
	 * @param saveFile The name of the save file
	 */
	public void save(String saveFile)
	{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream (saveFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
