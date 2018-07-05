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

/**
 * The target class with an boolean attribute of whether
 * or not the target has been shot
 * @author thefurryman
 *
 */
public class Target {
	/**
	 * determines whether an object of Target is shot
	 * or not
	 */
	private boolean isShot = false;
	
	/**
	 * Method called if the target is shot and will change
	 * attribute of {@link isIntact()} to true;
	 */
	public void targetShot() {
		isShot = true;
	}
	
	/**
	 * This method will retrieve the current status of whether or not
	 * the target is shot.
	 * @return
	 */
	public boolean status() {
		return isShot;
	}
	
}
