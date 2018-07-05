/**
 * 
 */
package edu.csupomona.cs.cs141.gunsim;

/**
 * @author Ray
 *
 */
public class Rifle extends Gun {

	/**
	 * 
	 */
	UserInterface uInterface = new UserInterface();
	public Rifle() {
		super(.65, 6, "Rifle", 2);
	}

	/* (non-Javadoc)
	 * @see edu.csupomona.cs.cs141.gunsim.Gun#shoot()
	 */
	@Override
	public boolean shoot() {
		boolean isShot;
		if (getAmmo() > 0){
			double die = Math.random();
			if (die <= getAccuracy()) {
				minusAmmo();
				uInterface.shot("Pop");
			isShot = true;
			} else {
			uInterface.missedShot();
			isShot = false;
			}
			} else {
			isShot = false;
		}
		return isShot;
	}

}
