/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #1
 *
 * Building a Gun class, with attributes and behaviors, and
 * a corresponding program that uses the class.
 *
 * Alexander Kimea
 */


package edu.csupomona.cs.cs141.prog_assgmnt_1;

import java.util.Random;
import java.util.Scanner;

/**
 * The Gun class is where the basic attributes
 * and properties of a default gun is stated so
 * a constructor can utilize this class and
 * give more precise values to the properties an
 * attributes.
 * 
 * @author thefurryman
 *
 */
public class Gun {
	
	private int ammo = 10;
	private int accuracy = 85;
	
	/**
	 * This method will return the number of ammo in int that is being used
	 * @return 
	 */
	public int getAmmo() {
		return ammo;
	}
	
	/**
	 * This will get the accuracy itn int of the current gun being used.
	 * @return 
	 */
	public int getAccuracy() {
		return accuracy;
	}
	
	/**
	 * The primary method where are guns are created and assigned
	 * specified attributes based on selected gun.
	 * @param name
	 */
	public void createGun(String name) {
		if (name == "shotgun") {
			this.ammo = 12;
			this.accuracy = 70;
		} else if (name == "rifle") {
			this.ammo = 30;
			this.accuracy = 90;
		} else {
			this.ammo = 10;
			this.accuracy = 85;
		}
	}
	
	/**
	 * The shoot method which, when used, will subtract 1 ammo
	 * from the equiped gun and add a text effect.
	 */
	public void shoot() {
		System.out.println("Bang!");
		--ammo;
	}
	
	/**
	 * The primary reload method where guns will be fully reloaded
	 * of its ammo.
	 * @param gun
	 */
	public void reload(String gun) {
		if (gun == "shotgun") {
			ammo = 12;
		} else if (gun == "rifle") {
			this.ammo = 30;
		} else {
			this.ammo = 10;
		}
	}
	
	/**
	 * Overloaded reload method where user is asked in MainUI to
	 * enter a custom amount of ammo to be reloaded. The amount entered
	 * will be added to the remaining ammo.
	 * @param x
	 */
	public void reload(int x) {
		ammo += x;
	}
	
	/**
	 * The random number generator which all guns use when firing at a target.
	 * Max = 100, Min = 0. If the randomly generated number is less than or equal
	 * to the gun's accuracy, the target will be successfully shot.
	 * @return
	 */
	public static int accuracyRNG() {
		Random rand = new Random();
		int randomValue = rand.nextInt((100 - 0) + 1) + 0;
		return randomValue;
	}
	
	
	

}

