package edu.cpp.cs.cs141.class_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

import java.awt.*;



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
/**
 * 
 * This class controls all game specific mechanics and interactions between 
 * objects in the game. It controls player and enemy actions aswell.
 */
public class GameEngine implements Serializable {
	/**
	 * An object that encapsulates the multiple parts of the GUI.
	 * By placing those parts in one class, it is easier to avoid stackoverflow errors due to indirect recursion
	 */
	private VisualGame gamer;
	/**
	 * The class responsible creating pop up messages.
	 * These messages contain warnings for when the player tries to make an illegal move.
	 * They also contain bits of information for when the player picks up power-ups
	 */
	private GUIMessage message;
	/**
	 * This is the 2 dimensional array that the player is traversing through
	 * The array keeps track of the location of all objects within the array.
	 */
	private Grid grid;
	/**
	 * This class allows the user to interact with the game.
	 * The userInterface is also responsible for any output text that is printer on to the console.
	 */
	private UserInterface userI;
	
	/**
	 * determines whether or not the player has moved
	 * A player cannot move when this is false
	 */
	private boolean hasMoved;

	/**
	 * Determines what direction the player is facing
	 * 1) up 2) down 3) left 4) right
	 * The player can only see in the direction they are facing
	 */
	private int direction;

	/**
	 * 	Determines whether or not the player has looked already or not
	 *  The player cannot look again until this variable is false;
	 */
	private boolean hasLooked;
	/**
	 * The gun that the player is holding.
	 * This gun starts off with one ammo and loses one ammo every time it is fired.
	 * The gun can be reloaded if the player finds a bullet
	 * The gun cannot shoot when it has no ammo.
	 */
	private Gun gun;
	/**
	 * The number of turns that the player is invincible.
	 * While this number is greater than zero, the player cannot die.
	 */
	private int invincible;
	/**
	 * A variable for keeping track of whether or not the player has won.
	 * The player cannot perform any actions if they win.
	 */
	private boolean win;
	/**
	 * A variable for keeping track of whether or not the player is alive.
	 * The player cannot perform any actions if they are not alive.
	 */
	private boolean alive;
	/**
	 * A variable for keeping track of the difficulty of the game.
	 * If hardMode is true, the enemy gain an AI and will chase the player when they are in the same row or column
	 */
	private boolean hardMode;
	/**
	 * A variable for keeping track of whether or not the game is being debugged or played
	 * If this is true, the game enters a debugging mode, where all the objects within the grid can be seen.
	 * The exit is also visible.
	 */
	private boolean debug;
	/**
	 * Determines if the player has a radar
	 * If the player has a radar, they know the location of the exit.
	 */
	private boolean hasRadar;
	/**
	 * Determines if the gui is running or not.
	 * If the gui is running, pop-up messages will appear instead of text on the console
	 * A graohical representation of the grid will also be shown instead of a text based one
	 */
	private boolean gui;
	
	/**
	 * The default constructor for the GameEngine class.
	 * The constructor creates all of the private objects.
	 * All of the booleans are set to false, except gui and alive which are set to true.
	 * Direction is also initialized as 1 instead of 0;
	 */
	public GameEngine() 
	{
		gamer = new VisualGame();
		gui = true;
		message = new GUIMessage();
		hasRadar = false;
		gun = new Gun();
		grid = new Grid();
		userI = new UserInterface();
		hasMoved = false;
		direction = 1;
		hasLooked = false;
		invincible = 0;
		win = false;
		alive = true;
		hardMode = false;
		debug = false;
	}

	/**
	 * A getter method for the private variable gui
	 * This allows other objects to determine whether or not the gui is currently showing 
	 * 
	 * @return whether or not the gui is on or off
	 */
	public boolean isGui() {
		return gui;
	}


	/**
	 * A setter method for the private variable gui
	 * This allows other objects to set the status of gui
	 * When gui is true, the gui will appear
	 * When the gui is false, the text based interface will appear.
	 * 
	 * @param gui sets whether or not the gui is on or off
	 */
	public void setGui(boolean gui) {
		this.gui = gui;
	}
	
	/**
	 * A getter method for the private variable VisualGrid
	 * This allows other classes to access methods that are within the VisualGame through GameEngine
	 * 
	 * @return the gui for the game
	 */
	public VisualGame getVisualGame()
	{
		return gamer;
	}
	
	/**
	 * A getter method for the private variable direction
	 * This method allows other objects to know what direction the player is facing as well.
	 * 
	 * @return the direction the player is facing
	 */
	public int getDirection()
	{
		return direction;
	}
	
	/**
	 * A setter method that allows altercation to the private variable hasLooked
	 * This allows other objects (specifically the GUI) to change the status of hasLooked 
	 * @param choice false if you haven't looked
	 * true if you have looked.
	 */
	public void setLooked(boolean choice)
	{
		hasLooked = choice;
	}
	
	/**
	 * A getter method that returns the status of hasLooked.
	 * This tells whether or not the player has looked this turn.
	 * @return@whether or not you have looked this turn or not.
	 */
	public boolean getLooked()
	{
		return hasLooked;
	}
	
	/**
	 * A getter method for the private variable debug.
	 * This will tell whether or not debug mode is on or off.
	 * @return whether or not debug mode is on or off
	 */
	public boolean getDebug()
	{
		return debug;
	}
	
	/**
	 * A setter method that allows the player to toggle debug mode
	 * When true, debug mode will be on and the player can see the whole map
	 * When false, player can only see 2 spaces in the direction that they are looking.
	 * 
	 * @param mod whether or not debug mode is on or off
	 */
	public void setDebug(boolean mod)
	{
		debug = mod;
	}

	
	/**
	 * A getter method for the private boolean hasRadar.
	 * This method will tell other classes if the player in the grid has the radar.
	 * 
	 * @return Whether or not the player has the radar power-up.
	 */
	public boolean getRadarStatus()
	{
		return hasRadar;
	}

	/**
	 * A getter method for the private variable invincible
	 * This allows other classes to access invincible and determine how many turns their invincibility will last
	 * 
	 * @return the number of turns your invincibility will last
	 */
	public int getInvincible()
	{
		return invincible;
	}
	
	/**
	 * A getter method that allows access to the gun object within a gameEngine object
	 * This is useful for getting the amount of bullets remaining in the gun.
	 * 
	 * @return The gun in the game engine
	 */
	public Gun getGun()
	{
		return gun;
	}
	
	/**
	 * A setter method that allows the user to change the difficulty.
	 * The difficulty can only be changed through this method.
	 * This can also be used to toggle the two difficulties.
	 * 
	 * @param mode the difficulty that the game will be. True for hard mode, false for easy mode
	 */
	public void setHardMode(boolean mode)
	{
		hardMode = mode;
	}
	
	/**
	 * A getter method that checks if you have won the game or not.
	 * You win the game when you reach the briefcase.
	 * This method returns true when you have won the game and returns false when you are still playing the game.
	 * 
	 * @return Whether or not you have won the game
	 */
	public boolean checkWin()
	{
		return win;
	}
	
	/**
	 * A getter method that checks if you are alive or dead
	 * This method returns true if you are alive
	 * This method returns false if you are dead
	 * 
	 * @return whether or not you are alive
	 */
	public boolean checkAlive()
	{
		return alive; 
	}
	
	/**
	 * A getter method that allows access to the grid within the game engine
	 * 
	 * @return The grid inside the game engine
	 */
	public Grid getGrid()
	{
		return grid;
	}
	
	/**
	 * A getter method that checks if hard mode is on or off
	 * If hard mode is on the ninjas have advanced AI and will follow the player
	 * 
	 * @return the game mode
	 */
	public boolean getMode()
	{
		return hardMode;
	}
	
	/**
	 * A getter method that checks if the player has moved or not.
	 * Returns true if the player has already moved.
	 * Returns false if the player hasn't moved.
	 * @return whether or not the player has moved
	 */
	public boolean checkMoved()
	{
		return hasMoved;
	}
	
	
	/**
	 * A method that checks if you are invincible
	 * If the player is invincible then the invincibility counter will decrease by one until it reaches 0
	 * 
	 */
	public void checkInvincible()
	{
		if(invincible > 0)
		{
			invincible--;
			if(!gui)
			{
				userI.invincible(invincible);
			}
		}
	}
	
	/**
	 * Kills an enemy if the enemy is shot
	 * The player is prompted to shoot in a certain direction as well.
	 * The gun canno shoot if the ammo is 0. Instead a warning message will appear.
	 */
	public void shoot()
	{
		if(gun.getAmmo() > 0)
		{
			int dir = userI.shootDirection();
			switch(dir)
			{
			case 1:
				shootUp();
				break;
			case 2:
				shootDown();
				break;
			case 3:
				shootLeft();
				break;
			case 4:
				shootRight();
				break;
			}
		}
		else
		{
			userI.noAmmo();
		}
		
	}
	
	/**
	 * Checks for an enemy in the same row and to the right of your current location
	 * Shoots and kills the enemy if there is an enemy
	 */
	public void shootRight()
	{
		if(gun.getAmmo() > 0)
		{
			boolean missed = false;
			boolean hit = false;
			for (int row = grid.getPlayerLocationRow(); row <= grid.getPlayerLocationRow(); row++) 
			{
				for (int col = grid.getPlayerLocationCol(); col < grid.getGrid()[0].length; col++) 
				{
					if(grid.getGrid()[row][col] instanceof Room){
						if(gui)
						{
							message.dialog("You hit a room and the bullet was stopped");
						}
						else
						{
							userI.roomShot();
						}
						missed = true;
						break;
					}
					else if(grid.getGrid()[row][col] instanceof Enemy)
					{
						grid.getGrid()[row][col] = null;
						if(gui)
						{
							message.dialog("You hit an enemy");
						}
						else
						{
							userI.enemyShot();
						}
						hit = true;
					}
				}
				if(missed)
					break;
			}
			hasMoved = true;
			gun.shoot();
			
			if(hit == false && missed == false)
			{
				if(gui)
				{
					message.dialog("You missed");
				}
				else
				{
					userI.missedShot();
				}
			}
		}
		else
		{
			if(gui)
			{
				message.dialog("Out of ammo");
			}
			else
			{
				userI.noAmmo();
			}
		}
	}
	
	/**
	 * Checks for an enemy in the same row and to the left of your current location
	 * Shoots and kills the enemy if there is an enemy
	 */
	public void shootLeft()
	{
		if(gun.getAmmo() > 0)
		{
			boolean missed = false;
			boolean hit = false;
			for (int row = grid.getPlayerLocationRow(); row <= grid.getPlayerLocationRow(); row++) 
			{
				for (int col = grid.getPlayerLocationCol(); col > -1; col--) 
				{
					if(grid.getGrid()[row][col] instanceof Room){
						if(gui)
						{
							message.dialog("You hit a room and the bullet was stopped");
						}
						else
						{
							userI.roomShot();
						}
						missed = true;
						break;
						
					}
					else if(grid.getGrid()[row][col] instanceof Enemy)
					{
						grid.getGrid()[row][col] = null;
						if(gui)
						{
							message.dialog("You hit an enemy");
						}
						else
						{
							userI.enemyShot();
						}
						hit = true;
					}
				}
				if(missed)
					break;
			}
			hasMoved = true;
			gun.shoot();
			if(hit == false && missed == false)
			{
				if(gui)
				{
					message.dialog("You missed");
				}
				else
				{
					System.out.println("You missed");
				}
			}
		}
		else 
		{
			if(gui)
			{
				message.dialog("Out of ammo");
			}
			else
			{
				userI.noAmmo();
			}
		}
	}
	
	/**
	 * Checks for an enemy in the same column and to the above of your current location
	 * Shoots and kills the enemy if there is an enemy
	 */
	public void shootUp()
	{
		if(gun.getAmmo() > 0)
		{
			boolean missed = false;
			boolean hit = false;
			for (int row = grid.getPlayerLocationRow(); row > -1; row--) 
			{
				for (int col = grid.getPlayerLocationCol(); col <= grid.getPlayerLocationCol() ; col++) 
				{
					if(grid.getGrid()[row][col] instanceof Room){
						if(gui)
						{
							message.dialog("You hit a room and the bullet was stopped");
						}
						else
						{
							userI.roomShot();
						}
						missed = true;
						break;
						
					}
					else if(grid.getGrid()[row][col] instanceof Enemy)
					{
						grid.getGrid()[row][col] = null;
						if(gui)
						{
							message.dialog("You hit an enemy");
						}
						else
						{
							userI.enemyShot();
						}
						hit = true;
					}
				}
				if(missed)
					break;
			}
			hasMoved = true;
			gun.shoot();
			if(hit == false && missed == false)
			{
				if(gui)
				{
					message.dialog("You missed");
				}
				else
				{
					userI.missedShot();
				}
			}
		}
		else
		{
			if(gui)
			{
				message.dialog("Out of ammo");
			}
			else
			{
				userI.noAmmo();
			}
		}
	}
	
	/**
	 * Checks for an enemy in the same column and to the below of your current location
	 * Shoots and kills the enemy if there is an enemy
	 */
	public void shootDown()
	{
		boolean missed = false;
		
		if(gun.getAmmo() > 0)
		{
			boolean hit = false;
			for (int row = grid.getPlayerLocationRow(); row < grid.getGrid().length; row++) 
			{
				for (int col = grid.getPlayerLocationCol(); col <= grid.getPlayerLocationCol() ; col++) 
				{
					if(grid.getGrid()[row][col] instanceof Room){
						if(gui)
						{
							message.dialog("You hit a room and the bullet was stopped");
						}
						else
						{
							userI.roomShot();
						}
						missed = true;
						break;				
					}
					else if(grid.getGrid()[row][col] instanceof Enemy)
					{
						grid.getGrid()[row][col] = null;
						if(gui)
						{
							message.dialog("You hit an enemy");
						}
						else
						{
							userI.enemyShot();
						}
						hit = true;
					}
				}
				if(missed)
					break;
			}
			hasMoved = true;
			gun.shoot();
			if(hit == false && missed == false)
			{
				if(gui)
				{
					message.dialog("You missed");
				}
				else
				{
					userI.missedShot();
				}
			}
		}
		else
		{
			if(gui)
			{
				message.dialog("Out of ammo");
			}
			else
			{
				userI.noAmmo();
			}

		}
	}
	
	/**
	 * A method that changes the direction that player is looking.
	 * If the player has already looked then they will get a warning message
	 */
	public void look()
	{
		if(hasLooked == false)
		{
			direction = userI.lookDirection();
			hasLooked = true;
		}
		else
		{
			if(gui)
			{
				message.dialog("You already looked this turn");
			}
			else
			{
				userI.alreadyLooked();
			}
		}
	}
	
	/**
	 * A method that is used for changing the direction the player is looking for the GUI
	 * If the player has already looked then they will get a warning message
	 */
	public void lookGui()
	{
		if(hasLooked == false)
		{
			hasLooked = true;
		}
		else
		{
			if(gui)
			{
				message.dialog("You already looked this turn");
			}
			else
			{
				userI.alreadyLooked();
			}
		}
	}
	
	/**
	 * Prerequisite: Player must have moved
	 * 
	 * A method that causes all enemies to move.
	 * Sets hasMoved to false at the end, signaling that the player may move again
	 * 
	 * @param hardMode the difficulty of the game
	 */
	public void enemiesMove(boolean hardMode) {
		if (hardMode == false) 
		{
			if (hasMoved) 
			{
				grid.countEnemies();
				grid.findEnemyLocxs();
				boolean moved = false;
				int timesTried = 0;
				for (int i = 0; i < grid.getNumEnemies(); i++) {
					double randMove = Math.random();
					moved = false;
					// move up
					if (randMove < .25) {
						if ((grid.getRowLocs(i) - 1 > 0)
								&& (grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] == null
										|| grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] instanceof SeenTile || grid
											.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					// move down
					else if (randMove >= .25 && randMove < .50) {
						if ((grid.getRowLocs(i) + 1 < 9)
								&& (grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] == null || grid
										.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] instanceof SeenTile ||  grid
										.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					// move left
					else if (randMove >= .5 && randMove < .75) {
						if (grid.getColLocs(i) - 1 > 0
								&& (grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] == null || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					// move right
					else {
						if (grid.getColLocs(i) + 1 < 9
								&& (grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] == null || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					if (moved == false) {
						i = i - 1;
						timesTried++;

					}
					// Assures no infinite loop
					if (timesTried == 100) {
						i++;
						timesTried = 0;
					}
				}
				hasMoved = false;
				grid.respawnPowerUps();
			}
		}
		else
		{
			enemiesMoveAI();	
		}
	}
	
	/**
	 * The AI for the enemies when hard mode is turned on.
	 * The enemies will check to see if there are any players in the same row or columns as them.
	 * If there are then the enemies will move toward the player.
	 * The enemies cannot see through rooms though.
	 */
	public void enemiesMoveAI(){
		if (hasMoved) 
		{
			grid.countEnemies();
			grid.findEnemyLocxs();
			boolean moved = false;
			int timesTried = 0;

			for (int i = 0; i < grid.getNumEnemies(); i++) {
				double randMove = Math.random();
				moved = false;
				int moveDirection = 0;
				boolean roomBlocking = false;
				for (int row = grid.getRowLocs(i); row > -1; row--) 
				{
					for (int col = grid.getColLocs(i); col <= grid.getColLocs(i); col++) 
					{
						if (grid.getGrid()[row][col] instanceof Room)
						{
							roomBlocking = true;
							break;
						}
						else if (grid.getGrid()[row][col] instanceof Player)
						{
							moveDirection = 1;
						}
					}
					if (roomBlocking)
						break;
				}
				roomBlocking = false;
				for (int row = grid.getRowLocs(i); row < grid.getGrid().length; row++) {
					for (int col = grid.getColLocs(i); col <= grid.getColLocs(i); col++) {
						if (grid.getGrid()[row][col] instanceof Room) {
							roomBlocking = true;
							break;
						} else if (grid.getGrid()[row][col] instanceof Player) {
							moveDirection = 2;
						}
					}
					if(roomBlocking)
						break;
				}
				roomBlocking = false;
				for (int row = grid.getRowLocs(i); row <= grid.getRowLocs(i); row++) {
					for (int col = grid.getColLocs(i); col > -1; col--) {
						if (grid.getGrid()[row][col] instanceof Room) {
							roomBlocking = true;
							break;
						} else if (grid.getGrid()[row][col] instanceof Player) {
							moveDirection = 3;
						}
					}
					if(roomBlocking)
						break;
				}
				roomBlocking = false;
				for (int row = grid.getRowLocs(i); row <= grid.getRowLocs(i); row++) {
					for (int col = grid.getColLocs(i); col < grid.getGrid()[0].length; col++) {
						if (grid.getGrid()[row][col] instanceof Room) {
							roomBlocking = true;
							break;
						} else if (grid.getGrid()[row][col] instanceof Player) {
							moveDirection = 4;
						}
					}
					if(roomBlocking)
						break;
				}

				// move up
				if (moveDirection == 1) {
					if ((grid.getRowLocs(i) - 1 > 0)
							&& (grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] == null
									|| grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] instanceof PowerUps || grid
										.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] instanceof SeenTile)) {
						grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] = new Enemy();
						grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
						moved = true;
					}
				}
				// move down
				else if (moveDirection == 2) {
					if ((grid.getRowLocs(i) + 1 < 9)
							&& (grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] == null
									|| grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] instanceof PowerUps)) {
						grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] = new Enemy();
						grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
						moved = true;
					}
				}
				// move left
				else if (moveDirection == 3) {
					if (grid.getColLocs(i) - 1 > 0
							&& (grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] == null
									|| grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] instanceof PowerUps)) {
						grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] = new Enemy();
						grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
						moved = true;
					}
				}
				// move right
				else if (moveDirection == 4) {
					if (grid.getColLocs(i) + 1 < 9
							&& (grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] == null || grid
									.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] instanceof SeenTile || grid
									.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] instanceof PowerUps)) {
						grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] = new Enemy();
						grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
						moved = true;
					}
				} else if (moveDirection == 0) {
					if (randMove < .25) {
						if ((grid.getRowLocs(i) - 1 > 0)
								&& (grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] == null || grid
										.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i) - 1][grid.getColLocs(i)] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					// move down
					else if (randMove >= .25 && randMove < .50) {
						if ((grid.getRowLocs(i) + 1 < 9)
								&& (grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] == null || grid
										.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] instanceof SeenTile ||  grid
										.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i) + 1][grid.getColLocs(i)] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					// move left
					else if (randMove >= .5 && randMove < .75) {
						if (grid.getColLocs(i) - 1 > 0
								&& (grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] == null || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) - 1] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
					// move right
					else {
						if (grid.getColLocs(i) + 1 < 9
								&& (grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] == null || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] instanceof SeenTile || grid
										.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] instanceof PowerUps)) {
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i) + 1] = new Enemy();
							grid.getGrid()[grid.getRowLocs(i)][grid.getColLocs(i)] = null;
							moved = true;
						}
					}
				}
				if (moved == false) {
					i = i - 1;
					timesTried++;

				}
				// Assures no infinite loop
				if (timesTried == 1000) {
					i++;
					timesTried = 0;
				}
			}
			hasMoved = false;
			grid.respawnPowerUps();
		}

	}
		
	
	/**
	 * A method that chooses allows the player to move in one direction once
	 * hasMoved will become true if the player was able to move
	 * The player cannot move through walls or out of bounds.
	 * 
	 * @param choice -Determines which way the player will move. 1)Up 2)Down 3)Left 4)Right
	 * 
	 */
	public void playerMove(int choice)
	{

		try
		{
			for (int row = 0; row < grid.getGrid().length; row++) 
			{
				for (int col = 0; col < grid.getGrid()[0].length; col++) 
				{
					if (grid.getGrid()[row][col] instanceof Player && !hasMoved)
					{
						switch(choice)
						{
						//move up
						case 1:
							if(grid.getGrid()[row -1][col] == null || grid.getGrid()[row -1][col] instanceof SeenTile)
							{
								grid.getGrid()[row -1][col] = grid.getPlayer();
								grid.getGrid()[row][col] = null;
								hasMoved = true;
							}
							else
							{
								if(checkAhead(-1,0))
								{
									grid.getGrid()[row -1][col] = grid.getPlayer();
									grid.getGrid()[row][col] = null;
									hasMoved = true;
								}
							}
							break;
						//move down
						case 2:
							if(grid.getGrid()[row + 1][col] == null || grid.getGrid()[row + 1][col] instanceof SeenTile)
							{
								grid.getGrid()[row + 1][col] = grid.getPlayer();
								grid.getGrid()[row][col] = null;
								hasMoved = true;
							}
							else
							{
								if(checkAhead(1,0))
								{
									grid.getGrid()[row + 1][col] = grid.getPlayer();
									grid.getGrid()[row][col] = null;
									hasMoved = true;
								}
							}
							break;
						//move left
						case 3:
							if(grid.getGrid()[row][col - 1] == null || grid.getGrid()[row][col - 1] instanceof SeenTile)
							{
								grid.getGrid()[row][col - 1] = grid.getPlayer();
								grid.getGrid()[row][col] = null;
								hasMoved = true;
							}
							else
							{
								if(checkAhead(0,-1))
								{
									grid.getGrid()[row][col - 1] = grid.getPlayer();
									grid.getGrid()[row][col] = null;
									hasMoved = true;
								}
							}
							break;
						//move right
						case 4:
							if(grid.getGrid()[row][col + 1] == null || grid.getGrid()[row][col + 1] instanceof SeenTile)
							{
								grid.getGrid()[row][col + 1] = grid.getPlayer();
								grid.getGrid()[row][col] = null;
								hasMoved = true;
							}
							else
							{
								if(checkAhead(0,1))
								{
									grid.getGrid()[row][col + 1] = grid.getPlayer();
									grid.getGrid()[row][col] = null;
									hasMoved = true;
								}
							}
							break;
						}
					}
				}	
			}
		}
		catch(ArrayIndexOutOfBoundsException error)
		{
			if(gui)
			{
				message.dialog("Cannot move there, try a different action");
			}
			else
			{
				userI.illegalMove();
			}
		}
	}
	
	/**
	 * Prerequisite: The player has already moved
	 * 
	 * A method that checks if there are enemies adjacent to the player
	 * If there are enemies the player will die
	 */
	public void checkForEnemies()
	{
		int row = grid.getPlayerLocationRow();
		int col = grid.getPlayerLocationCol();
		for(int i = -1; i <= 1; i += 2)
		{
			if(inBounds(row,i) && grid.getGrid()[row + i][col] instanceof Enemy && invincible == 0)
			{
				respawn();
			}
			else if(inBounds(col,i) && grid.getGrid()[row][col + i] instanceof Enemy && invincible == 0)
			{
				respawn();
			}
		}
	}
	
	/**
	 * A method that causes the player to respawn back at [8][0]
	 * The player will also lose a life and will be informed of this loss.
	 * If the player runs out of lives, the game will end and the player will be informed that they lost.
	 * 
	 * @param playerRow	The player's current row
	 * @param playerCol	The player's current location
	 */
	public void respawn()
	{
		int row = grid.getPlayerLocationRow();
		int col = grid.getPlayerLocationCol();
		grid.getPlayer().die();
		if(grid.getPlayer().getLives() != 0)
		{
			grid.getGrid()[8][0] = grid.getPlayer();
			if(gui)
			{
				message.dialog("You lost a life");
			}
			else
			{
				userI.respawn(grid.getPlayer().getLives());
			}
			hasMoved = true;
		}
		grid.getGrid()[row][col] = null;
		if(grid.getPlayer().getLives() == 0)
		{
			alive = false;
			if(gui)
			{
				message.dialog("You lose...");
			}
			else
			{
				userI.lostGame();
			}
		}
	}
	
	
	/**
	 * @param x	The first value that you are checking
	 * @param y	The second value that you are checking
	 * @return true	if the sum of x and y are greater than -1 and less than 9
	 * 
	 * A method that makes sure that objects don't go out of bounds
	 */
	public boolean inBounds(int x, int y)
	{
		if(x + y > -1 && x + y < 9)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * @return true if there is a player in front of you.
	 * 
	 * If there is a player, then the player dies
	 * The player will respawn if they still have lives
	 * 
	 */
	public void checkForPlayer(int index, int rowMod, int colMod)
	{
		
		int row = grid.getRowLocs(index);
		int col = grid.getColLocs(index);
		
		if(grid.getGrid()[row + rowMod][col + colMod] instanceof Player && invincible == 0)
		{
			grid.getPlayer().die();
			grid.getGrid()[8][0] = grid.getPlayer();
			grid.getGrid()[row + rowMod][col + colMod] = null;
			if(gui)
			{
				message.dialog("You lost a life");
			}
			else
			{
				userI.respawn(grid.getPlayer().getLives());
			}
			if(grid.getPlayer().getLives() == 0)
			{
				alive = false;
				if(gui)
				{
					message.dialog("You lose...");
				}
				else
				{
					userI.lostGame();
				}
			}
		}
	}
	

	/**
	 * @param modRow
	 * @param modCol
	 * @return true if you can move
	 * @return false if you cannot move
	 * 
	 * A method that checks the space of the location that the player wishes to move to.
	 * The player will determine what is at that location and perform specific actions depending on the object.
	 * The player will check rooms, pick up power ups, or die to enemies.
	 * 
	 */
	public boolean checkAhead(int modRow, int modCol)
	{
		boolean clearToMove = false;
		int row = grid.getPlayerLocationRow() + modRow;
		int col = grid.getPlayerLocationCol() + modCol;
		if(grid.getGrid()[row][col] instanceof Room)
		{
			if(modRow != 1)
			{
				if(gui)
				{
					message.dialog("There is a wall there. You cannot enter");
				}
				else
				{
					userI.wallBlocking();
				}
			}
			else
			{
				checkBrief(modRow,modCol);
			}
		}
		else if(grid.getGrid()[row][col] instanceof AdditionalBullet)
		{
			//moves powerup outside grid so nothing can access or view it
			grid.getAdditionalBullet().setxLoc(-1);
			gun.addBullet();
			if(gui)
			{
				message.dialog("You picked up an additional bullet");
			}
			else
			{
				userI.addBullet();
			}
			clearToMove = true;
		}
		else if(grid.getGrid()[row][col] instanceof Radar)
		{
			grid.getRadar().setxLoc(-1);
			if(gui)
			{
				message.dialog("You found a note with the location of the exit");
			}
			else
			{
				userI.radar(grid.getBriefRow(),grid.getBriefCol());
			}
			hasRadar = true;
			clearToMove = true;
		}
		else if (grid.getGrid()[row][col] instanceof Invincibility)
		{
			grid.getInvincibility().setxLoc(-1);
			if(gui)
			{
				message.dialog("You are now invincible");
			}
			invincible += 6;
			clearToMove = true;
		}
		else if(grid.getGrid()[row][col] instanceof Enemy)
		{
			if(invincible == 0)
			{
				if(gui)
				{
					message.dialog("You moved into an enemy");
				}
				else
				{
					userI.ranIntoEnemy();
				}
				respawn();
			}
			else
			{
				if(gui)
				{
					message.dialog("There is an enemy there. You cannot move there");
				}
				else
				{
					userI.enemyBlocking();
				}
			}
		}
		return clearToMove;
	}
	
	/**
	 * A method that checks if the briefcase is in the room or not
	 * If the briefcase is in the room, the player wins and the game is over.
	 * Checking for a brifcase doesn't take up a turn.
	 * 
	 * @param modRow
	 * @param modCol
	 * @return
	 */
	public void checkBrief(int modRow, int modCol)
	{
		int row = grid.getPlayerLocationRow() + modRow;
		int col = grid.getPlayerLocationCol() + modCol;
		if(grid.getGrid()[row][col] instanceof RoomWithBrief)
		{
			if(gui)
			{
				message.dialog("YOU ESCAPED!... for now");
			}
			else
			{
				userI.wonGame();
			}
			win = true;
		}
		else
		{
			if(gui)
			{
				message.dialog("The exit is not here.");
			}
			else
			{
				userI.wrongRoom();
			}
		}
	}

	
	/**
	 * A getter method for the private variable hasMoved
	 * This allows other objects to determine whether or not the player has moved.
	 * 
	 * @return true if the player has moved and false if the player hasn't moved
	 */
	public boolean isHasMoved() 
	{
		return hasMoved;
	}

	/**
	 * A setter method for the private variable hasMoved
	 * This allows other objects to set whether or not the player has moved.
	 * @param hasMoved sets whether or not the player has moved
	 */
	public void setHasMoved(boolean hasMoved) 
	{
		this.hasMoved = hasMoved;
	}
}