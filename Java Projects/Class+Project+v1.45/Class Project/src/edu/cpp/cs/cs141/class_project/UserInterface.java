package edu.cpp.cs.cs141.class_project;

import java.io.Serializable;
import java.util.*;

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
 * @author thefurryman
 *
 */
public class UserInterface implements Serializable {
	transient Scanner scan;

	public UserInterface() 
	{
		scan = new Scanner(System.in);
	}
	
	/**
	 * Prompts user for the filename when they save the file
	 * The characters will be converted to lowercase and all saved files will be lowercase as well
	 * Loading a file is not case sensitive.
	 * 
	 * @return the filename of the saved file
	 */
	public String promptSave()
	{
		System.out.println("Type in the name of the save file");
		String saveFile = scan.next().toLowerCase() + ".dat";
		return saveFile;
	}
	
	/**
	 * Promps user for a filename to load a file
	 * characters converted to lowercase.
	 * @return
	 */
	public String promptLoad()
	{
		System.out.println("Type in a file name to load");
		String loadFile = scan.next().toLowerCase() + ".dat";
		return loadFile;
	}
	
	/**
	 * prompts user for a numerical value to move the player
	 * @return
	 */
	public int moveDirection()
	{
		int direction = 0;
		while(direction > 4 || direction < 1 )
		{
			System.out.println("Please choose a direction to move. \n 1) Up 2) Down 3) Left 4) Right ");
			direction = scan.nextInt();
		}
		return direction;
	}
	
	/**
	 * prompts user for a numerical value to use the player's look action
	 * @return
	 */
	public int lookDirection()
	{
		int direction = 0;
		while(direction > 4 || direction < 1 )
		{
			System.out.println("Please choose a direction to look. \n 1) Up 2) Down 3) Left 4) Right ");
			direction = scan.nextInt();
		}
		return direction;
	}
	
	/**
	 * prompts user for a numerical value to shoot when using the shoot action
	 * @return
	 */
	public int shootDirection()
	{
		int direction = 0;
		while(direction > 4 || direction < 1 )
		{
			System.out.println("Please choose a direction to shoot. \n 1) Up 2) Down 3) Left 4) Right ");
			direction = scan.nextInt();
		}
		return direction;
	}
	
	/**
	 * Message upon starting the game is printed to the screen
	 */
	public void welcomeMsg() 
	{
		System.out.println("Welcome to the game!");
	}
	/**
	 * This is the hardmode toggle and can either be true or false
	 * @return
	 */
	public boolean hardMode(){
		boolean hardMode;
		System.out.println("Would you like to play Hard Mode? 1) Yes 2) No ");
		int choice = scan.nextInt();
		if (choice == 1)
			hardMode = true;
		else 
			hardMode = false;
		return hardMode;
	}
	

	/**
	 * Main menu of the game when player first starts the game
	 * @return
	 */
	public int mainMenu() 
	{
		System.out.println("1)Play 2)Load 3)HardMode 4) Debug 5) Debug/HardMode 6)GUI 7) Quit");
		int choice = scan.nextInt();
		return choice;
	}

	/**
	 * Prints varying statements depending on entered boolean parameter.
	 * @param debug
	 */
	public void debugMode(boolean debug) {
		if (debug == true)
			System.out.println("Debug mode has been turned on.");
		else 
			System.out.println("Debug mode has been turned off.");
	}


	/**
	 * Message prints out to user telling them game has been saved.
	 */
	public void gameSaved() {
		System.out.println("Game has been saved");		
	}

	/**
	 * Prints out message depending on if hardmode is on or off.
	 * @param hardMode
	 */
	public void hardMode(boolean hardMode) {
		if(hardMode == true)
			System.out.println("HardMode has been turned on.");
		else
			System.out.println("HardMode has been turned off");
	}

	/**
	 * Prints invalid method if user enteres wrong input
	 */
	public void invalid() {
		System.out.println("Invalid input: Please try again.");
	}

	/**
	 * message telling the user that the game has been loaded.
	 */
	public void gameLoaded() {
		System.out.println("Game has been loaded");
	}

	/**
	 * upon getting invincibile powerup, this message is printed.
	 * @param invincible
	 */
	public void invincible(int invincible) {
		System.out.println("You are invinvible for " + invincible + " turns");
	}

	/**
	 * Message stating that there is no ammo left in the gun.
	 */
	public void noAmmo() {
		System.out.println("You have no ammo!");
		
	}

	/**
	 * Messge stating when the player shoots a room.
	 */
	public void roomShot() {
		System.out.println("You shot a room");
	}

	/**
	 * prints when enemy is shot when player uses shoot action
	 */
	public void enemyShot() {
		System.out.println("You hit an enemy");	
	}

	/**
	 * prints message when player uses shoot and doesn't hit anything
	 */
	public void missedShot() {
		System.out.println("You missed.");
	}

	/**
	 * prints when player tries to save/load and file does not exist
	 */
	public void fileNotFound() {
		System.out.println("File not found");
		
	}

	/**
	 * prints out message when player tries to look at the same direction 
	 */
	public void alreadyLooked() {
		System.out.println("You already looked this turn");		
	}

	/**
	 * prints out message when player does a wrong action
	 */
	public void illegalMove() {
		System.out.println("Can't move there, try a different action");		
	}

	/**
	 * Prints out when player dies with lives left
	 * @param lives
	 */
	public void respawn(int lives) {
		System.out.println("You were killed! Number of lives remaining: " + lives);
	}

	/**
	 * This message is printed out when the player loses the game.
	 */
	public void lostGame() {
		System.out.println("You lose...");		
	}

	/**
	 * prints out message when player tries to move out of the grid.
	 */
	public void wallBlocking() {
		System.out.println("There is a wall there. You cannot enter.");		
	}

	/**
	 * prints out message when player picks up powerup
	 */
	public void addBullet() {
		System.out.println("You picked up an additional bullet");		
	}

	/**
	 * 
	 * prints out message when player picks up radar powerup
	 * @param briefRow
	 * @param briefCol
	 */
	public void radar(int briefRow, int briefCol) {
		System.out.println("You picked up a radar. The exit is located at: (" + briefRow +"," + briefCol + ")");
		
	}

	/**
	 * Prints this message when player moves onto an enemy and kills himself.
	 */
	public void ranIntoEnemy() {
		System.out.println("You moved into an enemy and died");
	}

	/**
	 * Prints when player is trying to enter a tile with an enemy
	 */
	public void enemyBlocking() {
		System.out.println("There is an enemy there. You cannot move there.");
	}

	/**
	 * Prints out this message when the player wins the game.
	 */
	public void wonGame() {
		System.out.println("YOU WIN!");
	}

	/**
	 * Prints out this message when entering the wrong room.
	 */
	public void doorLocked() {
		System.out.println("This door is locked");
	}

	/**
	 * Prints out user options each turn of the game.
	 * @return
	 */
	public int userOptions() {
		System.out.println("1) Move 2) Look 3) Shoot 4) Save 5) Load 6) Debug 7)HardMode 8)GUI (will start a new game) 9) Quit");
		int choice = scan.nextInt();
		return choice;
	}

	
}
