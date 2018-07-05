/**
 * 
 */
package edu.csupomona.cs.cs141.gunsim;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public class Shotgun extends Gun {
	
	UserInterface uInterface = new UserInterface();
	public Shotgun() {
		super(.7, 6, "Shotgun", 5);
	}

	@Override
	public boolean shoot() {
		if (getAmmo() > 0) {
			minusAmmo();
			double die = Math.random();
			if (die <= getAccuracy()) {
				uInterface.shot("Boom");
			return true;
			} else {
			uInterface.missedShot();
			return false;
			}
			} else {
			return false;
		}
	}
}
