package edu.cpp.cs.cs141.class_project;

import java.io.*;

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
 * This class is the Grid layout 9x9 for the game which controls spawning of all objects
 * and printing itself. It also controls whether or not a tile has been seen by the player.
 *
 */
public class Grid implements Serializable 
{
	/**
	 * The row of the room with the briefcase
	 * This value should be hidden from the player until they get the radar.
	 */
	private int briefRow;
	/**
	 * The column of the room with the briefcase.
	 * This value should be hidden from the player until they get the radar.
	 */
	private int briefCol;
	/**
	 * Number of rows in the 2d array
	 */
	private int ROWS;
	/**
	 * Number of columns in the 2d array
	 */
	private int COLS;
	/**
	 * Creates a player that plays the game
	 */
	private Player player;
	/**
	 * Creates a new 2d array of objects called grid
	 */
	private Object [][] grid;
	
	/**
	 * An array that keeps track of the enemies row 
	 */
	private int [] enemiesRowLocs;
	
	/**
	 * An array that keeps track of the enemies col
	 */
	private int [] enemiesColLocs;
	/**
	 * An integer that keeps track of the number of enemies
	 */
	private int numEnemies;
	/**
	 * An array that keeps track of the visible tiles row location 
	 * 
	 */
	private int[] seenTilesRowLocs;
	/**
	 * An array that keeps track of the visible tiles col location
	 */
	private int[] seenTilesColLocs;
	private SeenTile seenTile0;
	private SeenTile seenTile1;
	private Invincibility invincibility;
	private Radar radar;
	private AdditionalBullet additionalBullet;
	

	/**
	 * Initializes the grid as a 9 by 9 grid
	 * The index points are from 0 to 8
	 * The grid is initially all null
	 */
	public Grid() 
	{
		briefRow = -1;
		briefCol = -1;
		ROWS = 9;
		COLS = 9;
		player = new Player();
		grid = new Object[ROWS][COLS];
		numEnemies = 6;
		invincibility = new Invincibility();
		radar = new Radar();
		additionalBullet = new AdditionalBullet();
		enemiesRowLocs = new int[numEnemies];
		enemiesColLocs = new int[numEnemies];
		seenTile0 = new SeenTile();
		seenTile1 = new SeenTile();
		seenTilesRowLocs = new int[] {seenTile0.getSeenTileRow(),seenTile1.getSeenTileRow()};
		seenTilesColLocs = new int[] {seenTile0.getSeenTileCol(),seenTile1.getSeenTileCol()};
	}

	/**
	 * @return
	 * will return tiles rows that are seen by the player
	 */
	public int[] getSeenRow()
	{
		return seenTilesRowLocs;
	}
	
	/**
	 * @return
	 * will return tiles columns that are seen by the player
	 */
	public int[] getSeenCol()
	{
		return seenTilesColLocs;
	}
	
	/**
	 * A getter method that gets the private variable briefRow
	 * This will tell the player what row the room with the briefcase is at.
	 * 
	 * @return The row of the room with the briefcase
	 */
	public int getBriefRow()
	{
		return briefRow;
	}
	
	/**
	 * A getter method that returns the private variable briefCol
	 * This will tell the player what column the room with the briefcase is at.
	 * 
	 * @return The column of the room with the briefcase
	 */
	public int getBriefCol()
	{
		return briefCol;
	}
	
	/**
	 * A getter method that returns an Object that is in the 2d array of Objects
	 * 
	 * @param row	The row of the array you wish to access
	 * @param col	The column of the array you wish to acess
	 * @return	The object at location [row][col]
	 */
	public Object getObject(int row, int col)
	{
		return grid[row][col];
	}
	
	/**
	 * A getter method that gets the number of rows in the 2d array
	 * 
	 * @return The number of rows
	 */
	public int getRows()
	{
		return ROWS;
	}
	
	/**
	 * A getter method that gets the number of columns in the 2d array
	 * 
	 * @return The number of columns
	 */
	public int getCols()
	{
		return COLS;
	}
	
	public Invincibility getInvincibility() {
		return invincibility;
	}

	public Radar getRadar() {
		return radar;
	}

	public AdditionalBullet getAdditionalBullet() {
		return additionalBullet;
	}

	/**
	 * This method sets up all the objects in the grid
	 * The method spawns: a player, 6 enemies, 8 rooms without a briefcase, 1 room with the briefcase, and one of each powerUp
	 */
	public void setUp()
	{
		spawnPlayer();
		spawnRooms();
		spawnPowerUps();
		spawnEnemies();
	}
	
	/**
	 * @return Number of enemies in the array
	 */
	public int countEnemies()
	{
		numEnemies = 0;
		for(int row = 0; row< grid.length; row++)
		{
			for(int col = 0; col < grid[row].length; col++)
			{
				if(grid[row][col] instanceof Enemy)
				{
					numEnemies++;
				}
			}
		}
		return numEnemies;
	}
	
	/**
	 * @return
	 * will return number of enemmies left on the grid
	 */
	public int getNumEnemies() {
		return numEnemies;
	}

	/**
	 * @return An array of enemy X locations
	 */
	public int[] getRowArray()
	{
		return enemiesRowLocs;
	}
	
	/**
	 * @return The y location of an enemy at index
	 */
	public int getRowLocs(int index)
	{
		return enemiesRowLocs[index];
	}
	
	/**
	 * @return The x locations of an enemy at index
	 */
	public int getColLocs(int index)
	{
		return enemiesColLocs[index];
	}
	
	/**
	 * A method that keeps track of the enemy coordinates
	 * This method uses 2 separate arrays
	 * One array keeps track of the row and the other one keeps track of the column
	 */
	public void findEnemyLocxs()
	{
		int rowArray = 0;
		int colArray = 0;
		enemiesRowLocs = new int[numEnemies];
		enemiesColLocs = new int[numEnemies];
		for(int row = 0; row< grid.length; row++)
		{
			for(int col = 0; col < grid[row].length; col++)
			{
				if(grid[row][col] instanceof Enemy)
				{
					enemiesRowLocs[rowArray] = row;
					enemiesColLocs[colArray] = col;
					rowArray++;
					colArray++;
				}
			}
		}
	}
	/**
	 * @param look
	 * returns tiles that are able to be seen by the player
	 */
	public void viewableTiles(int look) {
		removeSeenTiles();
		int row = getPlayerLocationRow();
		int col = getPlayerLocationCol();
		switch (look) {
		case 1:
					seenTile1.setSeenTileRow(row -2); 
					seenTilesRowLocs[1] = row - 2;
					seenTile1.setSeenTileCol(col); 
					seenTilesColLocs[1] = col;
					seenTile0.setSeenTileRow(row -1);
					seenTilesRowLocs[0] = row - 1;
					seenTile0.setSeenTileCol( col);
					seenTilesColLocs[0] = col;
	
			if (row - 1 >= 0) {
				if (grid[row-1][col] instanceof Room){
					seenTile1.setSeenTileRow(-1);
					seenTilesRowLocs[1] = -1;
					seenTile1.setSeenTileCol( col);
					seenTilesColLocs[1] = col;
				}
					
			}
			
			break;
		case 2:
					seenTile1.setSeenTileRow(row +2);
					seenTilesRowLocs[1] = row + 2;
					seenTile1.setSeenTileCol(col);
					seenTilesColLocs[1] = col;
					seenTile0.setSeenTileRow(row + 1);
					seenTilesRowLocs[0] = row + 1;
					seenTile0.setSeenTileCol(col);
					seenTilesColLocs[0] = col;
			if (row + 1 <= 8) {
				if (grid[row+1][col] instanceof Room){
					seenTile1.setSeenTileRow(-1);
					seenTilesRowLocs[1] = -1;
					seenTile1.setSeenTileCol(col);
					seenTilesColLocs[1] = col;
				}
			}
			break;
		case 3:
					seenTile1.setSeenTileRow(row);
					seenTilesRowLocs[1] = row;
					seenTile1.setSeenTileCol(col - 2);
					seenTilesColLocs[1] = col - 2;
					seenTile0.setSeenTileRow(row);
					seenTilesRowLocs[0] = row;
					seenTile0.setSeenTileCol( col -1);
					seenTilesColLocs[0] = col - 1;
			if (col - 1 >= 0) {
				if(grid[row][col-1] instanceof Room){
					seenTile1.setSeenTileRow(row);
					seenTilesRowLocs[1] = row;
					seenTile1.setSeenTileCol( -1);
					seenTilesColLocs[1] = -1;
				}
			}
					
			break;
		case 4:
					seenTile1.setSeenTileRow(row);
					seenTilesRowLocs[1] = row;
					seenTile1.setSeenTileCol(col+2);
					seenTilesColLocs[1] = col + 2;
					seenTile0.setSeenTileRow(row);
					seenTilesRowLocs[0] = row;
					seenTile0.setSeenTileCol(col+1);
					seenTilesColLocs[0] = col + 1;
			if (col + 1 <= 8) {
				if(grid[row][col+1] instanceof Room){
					seenTile1.setSeenTileRow(row);
					seenTilesRowLocs[1] = row;
					seenTile1.setSeenTileCol(-1);
					seenTilesColLocs[1] = -1;
				}
			}
			break;
		}
		if ((seenTile1.getSeenTileRow() <= 8 && seenTile1.getSeenTileRow() >= 0)
				&& (seenTile1.getSeenTileCol() <= 8 && seenTile1.getSeenTileCol() >= 0)) {
			if (grid[seenTile1.getSeenTileRow()][seenTile1.getSeenTileCol()] == null) {
				grid[seenTile1.getSeenTileRow()][seenTile1.getSeenTileCol()] = seenTile1;
			}
		}

		if ((seenTile0.getSeenTileRow() <= 8 && seenTile0.getSeenTileRow() >= 0)
				&& (seenTile0.getSeenTileCol() <= 8 && seenTile0.getSeenTileCol() >= 0)) {
			if (grid[seenTile0.getSeenTileRow()][seenTile0.getSeenTileCol()] == null) {
				grid[seenTile0.getSeenTileRow()][seenTile0.getSeenTileCol()] = seenTile0;
			}
		}
	}
	public Object[][] getGrid()
	{
		return grid;
	}
	
	/**
	 * This method spawns creates a Player and puts the player in the bottom left hand corner of the grid
	 * The player will always spawn in the same location
	 */
	public void spawnPlayer()
	{
		grid[8][0] = player;
	}
	
	/**
	 * This method spawns 6 Enemies that are at least 3 squares away from the Player's initial spawn 
	 */
	public void spawnEnemies()
	{
		int numNinjas = 6;
		boolean enemySpawned;
		
		for (int i = 1; i <= numNinjas; i++) 
		{
			enemySpawned = false;
			
			while (enemySpawned == false)
			{
				int xLoc = (int) (Math.random() * 9);
				int yLoc = (int) (Math.random() * 9);
				if (xLoc >=5 && yLoc <= 3)
				{
					xLoc -= 4;
					yLoc += 4;
				}
				if(grid[xLoc][yLoc] == null || grid[xLoc][yLoc] instanceof PowerUps)
				{
					grid[xLoc][yLoc] = new Enemy();
					enemySpawned = true;
				}
				else
				{
					enemySpawned = false;
				}	
			}
		}
	}
	
	/**
	 * This method spawn 9 rooms in fixed locations.
	 * However the location of the room with the briefcase will be randomized each time.
	 * If no room with the briefcase is spawned by the end of the loop, then the room with the briefcase will be spawned at grid[4][4]
	 * Private variables briefRow and briefCol are also updated to show which row and col the briefcase is at.
	 */
	public void spawnRooms()
	{
		boolean briefSpawned = false;
		for(int row = 1; row < grid.length - 1; row+=3)
		{
			for(int col = 1; col < grid[row].length - 1; col+=3)
			{
				if(Math.random() <= .6 || briefSpawned == true)
				{
					grid[row][col] = new Room();
				}
				else
				{
					grid[row][col] = new RoomWithBrief();
					briefSpawned = true;
					briefRow = row;
					briefCol = col;
				}
					
			}
		}
		if(briefSpawned == false)
		{
			grid[4][4] = new RoomWithBrief();
			briefRow = 4;
			briefCol = 4;
		}
	}
	
	/**
	 * Randomly determines an empty point in the grid and spawns an additional bullet
	 * Also randomly determines an empty point in the grid and spawns an invincibility powerup
	 */
	public void spawnPowerUps()
	{
		boolean InvincibilitySpawned = false;
		boolean AddBulletSpawned = false;
		boolean RadarSpawned = false;
		
		while(InvincibilitySpawned == false)
		{
			InvincibilitySpawned = spawnInvincibility();
		}
		
		while(AddBulletSpawned == false)
		{
			AddBulletSpawned = spawnAddBullet();
		}
		
		while(RadarSpawned == false)
		{
			RadarSpawned = spawnRadar();
		}
	}
	
	/**
	 * A boolean method that randomly chooses a location in the grid and tries to spawn a new Invincibility Object
	 * If a new Invincibility object is spawned this method returns true.
	 * If no new object is spawned, this method returns false.
	 * 
	 * @return true if a new Invincibility powerup has been added to the grid
	 * @return false if no new Invincibility powerup was added
	 */
	public boolean spawnInvincibility()
	{
		int xLoc = (int) (Math.random() * 9);
		int yLoc = (int) (Math.random() * 9);
		if(grid[xLoc][yLoc] == null)
		{
			grid[xLoc][yLoc] = invincibility;
			invincibility.setxLoc(xLoc);
			invincibility.setyLoc(yLoc);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method randomly spawns a Radar powerup on a random location on the grid
	 * @return true if Radar powerup was added
	 * @return false if no Radar powerup was added
	 */
	public boolean spawnRadar()
	{
		int xLoc = (int) (Math.random() * 9);
		int yLoc = (int) (Math.random() * 9);
		if(grid[xLoc][yLoc] == null)
		{
			grid[xLoc][yLoc] = radar;
			radar.setxLoc(xLoc);
			radar.setyLoc(yLoc);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * A boolean method that randomly chooses a location in the grid and tries to spawn a new AdditionalBullet Object
	 * If a new AdditionalBullet object is spawned this method returns true.
	 * If no new object is spawned, this method returns false.
	 * 
	 * @return true if a new Additional Bullet powerup has been added to the grid
	 * @return false if no new Additional powerup was added
	 */
	public boolean spawnAddBullet()
	{
		int xLoc = (int) (Math.random() * 9);
		int yLoc = (int) (Math.random() * 9);
		if(grid[xLoc][yLoc] == null)
		{
			grid[xLoc][yLoc] = additionalBullet;
			additionalBullet.setxLoc(xLoc);
			additionalBullet.setyLoc(yLoc);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Visual representation of the grid.
	 * Null spots in the grid are represented by X's
	 * Rooms are represented by O
	 * The room with the briefcase is represented with B
	 * The additonal bullet powerup is represented with a A
	 * The invincibility powerup is represented with an I
	 * Radar powerup is represented with an R
	 * Enemies are represented with an E
	 * 
	 */
	public void printGrid() {
		System.out.println("| PLAYER = P | Room = O | Briefcase = B | Bullet = A | "
				+ "Invincibility = I | Radar = R | ENEMY = E | \n");
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] == null) {
						System.out.print("*");
						System.out.print("  ");
				} else if (grid[row][col] instanceof SeenTile){
					System.out.print("+");
					System.out.print("  ");
					
				} else if (grid[row][col] instanceof Player) {
					System.out.print("P");
					System.out.print("  ");
				} else if (grid[row][col] instanceof RoomWithBrief) {
					if (seenTilesRowLocs[1] == row && seenTilesColLocs[1] == col) {
						System.out.print("B");
						System.out.print("  ");
					} else if (seenTilesRowLocs[0] == row && seenTilesColLocs[0] == col) {
						System.out.print("B");
						System.out.print("  ");
					}
					else {
						System.out.print("*");
						System.out.print("  ");
					}
				} else if (grid[row][col] instanceof Room) {
					if (seenTilesRowLocs[1] == row && seenTilesColLocs[1] == col) {
						System.out.print("O");
						System.out.print("  ");
					} else if (seenTilesRowLocs[0] == row && seenTilesColLocs[0] == col) {
						System.out.print("O");
						System.out.print("  ");
					}
					else {
						System.out.print("*");
						System.out.print("  ");
					}
				} else if (grid[row][col] instanceof AdditionalBullet) {
					if (seenTilesRowLocs[1] == row && seenTilesColLocs[1] == col) {
						System.out.print("A");
						System.out.print("  ");
					} else if (seenTilesRowLocs[0] == row && seenTilesColLocs[0] == col) {
						System.out.print("A");
						System.out.print("  ");
					}
					else {
						System.out.print("*");
						System.out.print("  ");
					}
				} else if (grid[row][col] instanceof Invincibility) {
					if (seenTilesRowLocs[1] == row && seenTilesColLocs[1] == col) {
						System.out.print("I");
						System.out.print("  ");
					} else if (seenTilesRowLocs[0] == row && seenTilesColLocs[0] == col) {
						System.out.print("I");
						System.out.print("  ");
					}
					else {
						System.out.print("*");
						System.out.print("  ");
					}
				} else if (grid[row][col] instanceof Radar) {
					if (seenTilesRowLocs[1] == row && seenTilesColLocs[1] == col) {
						System.out.print("R");
						System.out.print("  ");
					} else if (seenTilesRowLocs[0] == row && seenTilesColLocs[0] == col) {
						System.out.print("R");
						System.out.print("  ");
					}
					else {
						System.out.print("*");
						System.out.print("  ");
					}
				} else if (grid[row][col] instanceof Enemy) {
					if (seenTilesRowLocs[1] == row && seenTilesColLocs[1] == col) {
						System.out.print("E");
						System.out.print("  ");
					} else if (seenTilesRowLocs[0] == row && seenTilesColLocs[0] == col) {
						System.out.print("E");
						System.out.print("  ");
					}
					else {
						System.out.print("*");
						System.out.print("  ");
					}
				}
			}
			System.out.println();
		}

	}
	
	/**
	 * Once debug mode is toggled, this will print the debug mode grid layout
	 */
	public void printDebugModeGrid()
	{
		System.out.println("| PLAYER = P | Room = O | Briefcase = B | Bullet = A | "
				+ "Invincibility = I | Radar = R | ENEMY = E | \n");
		for(int row = 0; row< grid.length; row++)
		{
			for(int col = 0; col < grid[row].length; col++)
			{
				if(grid[row][col] == null)
				{
					System.out.print("*");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof RoomWithBrief)
				{
					System.out.print("B");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof Room)
				{
					System.out.print("O");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof AdditionalBullet)
				{
					System.out.print("A");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof Invincibility)
				{
					System.out.print("I");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof Radar)
				{
					System.out.print("R");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof Enemy)
				{
					System.out.print("E");
					System.out.print("  ");
				}
				else if(grid[row][col] instanceof Player)
				{
					System.out.print("P");
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		
	}
	
	/**
	 * This method will return the player's row location on the grid
	 * @return
	 */
	public int getPlayerLocationRow() 
	{
		for (int row = 0; row < grid.length; row++) 
		{
			for (int col = 0; col < grid[0].length; col++) 
			{
				if (grid[row][col] instanceof Player) 
				{
					return row;
				}
			}
		}
		return -1;
	}
	
	/**
	 * This will return the player's column location 
	 * @return
	 */
	public int getPlayerLocationCol() 
	{
		for (int row = 0; row < grid.length; row++) 
		{
			for (int col = 0; col < grid[0].length; col++) 
			{
				if (grid[row][col] instanceof Player)
				{
					return col;
				}
			}
		}
		return -1;
	}
	
	public Player getPlayer()
	{
		return player;
	}

	/**
	 * This method will remove all tiles that are seen by the player from the grid.
	 */
	public void removeSeenTiles() {
		// Erases all current SeenTiles from the grid
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] instanceof SeenTile) {
					grid[row][col] = null;
				}
			}
		}
	}

	/**
	 * When the method is used, then the powerups are respawned
	 */
	public void respawnPowerUps() {
		if (invincibility.getxLoc() != -1) {
			if (grid[invincibility.getxLoc()][invincibility.getyLoc()] == null) {
				grid[invincibility.getxLoc()][invincibility.getyLoc()] = invincibility;
			}
		}
		if (radar.getxLoc() != -1) {
			if (grid[radar.getxLoc()][radar.getyLoc()] == null) {
				grid[radar.getxLoc()][radar.getyLoc()] = radar;
			}
		}
		if (additionalBullet.getxLoc() != -1) {
			if (grid[additionalBullet.getxLoc()][additionalBullet.getyLoc()] == null) {
				grid[additionalBullet.getxLoc()][additionalBullet.getyLoc()] = additionalBullet;
			}
		}
			
		
	}


	

}