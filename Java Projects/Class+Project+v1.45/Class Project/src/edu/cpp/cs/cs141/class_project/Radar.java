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
public class Radar extends PowerUps{
	private int xLoc;
	private int yLoc;

	public Radar() {
	}

	/**
	 * setter that sets x location of radar of the entered parameter
	 * @param xLoc
	 */
	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	/**
	 * Setter that sets y location of radar depending on entered parameter
	 * @param yLoc
	 */
	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	/**
	 * getter that returns the x location of the radar
	 * @return
	 */
	public int getxLoc() {
		return xLoc;
	}

	/**
	 * getter that returns the y location of the radar
	 * @return
	 */
	public int getyLoc() {
		return yLoc;
	}
}
