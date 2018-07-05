/**
 * 
 */
package edu.csupomona.cs.cs141.gunsim;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class HandGun extends Gun {
	
	UserInterface uInterface = new UserInterface();
	public HandGun() {
		super(.85, 13, "Handgun", 1);

	}

	@Override
	public boolean shoot() {
		boolean isShot;
		if (getAmmo() > 0) {
			minusAmmo();
			double die = Math.random();
			if (die <= getAccuracy()) {
				uInterface.shot("Bang");
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
