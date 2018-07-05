package edu.csupomona.cs.cs141.gunsim;

public class Player extends ActiveAgents {
	Gun[] guns;

	public Player() {
		super(20);
		guns = new Gun[3];
		guns[0] = new HandGun();
		guns[1] = new Shotgun();
		guns[2] = new Rifle();

	}

	public Gun getGun(int choice) {
		return guns[choice];
	}

}
