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
 * @author thefurryman
 * This abstract class represents the active agents on the grid
 * Player and Enemy with associated hitpoitns.
 */
public abstract class ActiveAgents implements Serializable {
	/**
	 * This field represents an ActiveAgents hit points. 
	 * This amount is determined by the constructor
	 */
	private int hitPoints;
	
	
	/**An active agent with a varying amount of hp
	 * Since this is an abstract class, ActiveAgetns cannot be instantiated
	 * Instead this will be passed down for subclasses to use
	 * 
	 * @param hp amount of hp the active agent has
	 */
	public ActiveAgents(int hp) {
		hitPoints = hp;
	}
	
}
