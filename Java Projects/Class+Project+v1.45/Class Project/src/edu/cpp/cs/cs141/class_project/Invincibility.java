package edu.cpp.cs.cs141.class_project;

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
 * This class represents the Invcincibility powerup object and its attributes.
 *
 */
public class Invincibility extends PowerUps{
	/**
	 * The x Location on the grid.
	 */
	private int xLoc;
	/**
	 * The y location on the grid.
	 */
	private int yLoc;

	public Invincibility() {
		
	}

	/**
	 * @param xLoc
	 * sets xLocation on grid
	 */
	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	/**
	 * @param yLoc
	 * sets ylocation on grid according to entered parameter
	 */
	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	/**
	 * @return
	 * This method will return xlocation on the grid of the powerup
	 */
	public int getxLoc() {
		return xLoc;
	}

	/**
	 * @return
	 * This method will return y location on the grid of the powerup
	 */
	public int getyLoc() {
		return yLoc;
	}
	
}
