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
public class SeenTile implements Serializable {
	private int seenTileRow;
	private int seenTileCol;
	
	public SeenTile(){
		seenTileRow = -1;
		seenTileCol = -1;
	}

	/**
	 * @return the seenTileRow
	 */
	public int getSeenTileRow() {
		return seenTileRow;
	}

	/**
	 * 
	 * @param seenTileRow the seenTileRow to set
	 */
	public void setSeenTileRow(int seenTileRow) {
		this.seenTileRow = seenTileRow;
	}

	/**
	 * returns the seen tile column
	 * @return the seenTileCol
	 */
	public int getSeenTileCol() {
		return seenTileCol;
	}

	/**
	 * sets the tile seen by player in column
	 * @param seenTileCol the seenTileCol to set
	 */
	public void setSeenTileCol(int seenTileCol) {
		this.seenTileCol = seenTileCol;
	}

}
