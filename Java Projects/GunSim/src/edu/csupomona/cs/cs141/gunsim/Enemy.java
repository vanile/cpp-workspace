package edu.csupomona.cs.cs141.gunsim;

public class Enemy extends ActiveAgents {
	private Gun heldGun;

	public Enemy() {
		super(5);
		heldGun = null;
	}

	public void chooseGun() {
		double rand = Math.random();

		if (rand <= .5) {
			heldGun = new HandGun();
		} else if (rand > .5 && rand <= .85) {
			heldGun = new Rifle();
		} else {
			heldGun = new Shotgun();
		}
	}

	public Gun getGun() {
		return heldGun;
	}

	public String printGun() {
		return heldGun.getType();
	}
}
