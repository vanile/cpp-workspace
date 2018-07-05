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
public class AdditionalBullet extends PowerUps {
	/**
	 * Keeps track of the x location of the AdditionalBullet power-up
	 */
	private int xLoc;
	/**
	 * Keeps track of the y location of the AdditionalBullet power-up
	 */
	private int yLoc;

	public AdditionalBullet()
	{
		
	}
		

	/**
	 * This method assigns an x location to the power-up.
	 * By default the x location is 0.
	 * 
	 * @param xLoc the x location that will be given to the power-up
	 */
	public void setxLoc(int xLoc) 
	{
		this.xLoc = xLoc;
	}


	/**
	 * This method assigns a y location to the power-up.
	 * By default the y location is 0.
	 * @param yLoc the y location that will be given to the power-up
	 */
	public void setyLoc(int yLoc) 
	{
		this.yLoc = yLoc;
	}


	/**
	 * This method returns the x location of the power-up.
	 * If no x location was assigned, by default this will return 0.
	 * @return the x location of the power-up
	 */
	public int getxLoc() {
		return xLoc;
	}

	/**
	 * This method returns the y location of the power-up.
	 * If no x location was assigned, by default this will return 0.
	 * 
	 * @return the y location of the power-up
	 */
	public int getyLoc() {
		return yLoc;
	}
}
