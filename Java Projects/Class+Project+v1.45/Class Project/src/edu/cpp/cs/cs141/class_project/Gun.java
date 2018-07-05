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
 * This class represents the gun used by the player and has the gun's attributes and actions.
 * The player only has one bullet to start off with
 *
 */
public class Gun implements Serializable {
	/**
	 * ammo field stated
	 */
	private int ammo;
	
	public Gun() {
		ammo = 1;
	}
	
	/**
	 * shoot method that decreases ammo by 1
	 */
	public void shoot() 
	{
		ammo--;	
	}
	/**
	 * adds a bullet when player gets addbullet powerup
	 */
	public void addBullet() 
	{
		ammo++;
	}
	/**
	 * @return
	 * will return ammo of the gun that player has
	 */
	public int getAmmo()
	{
		return ammo;
	}
}
