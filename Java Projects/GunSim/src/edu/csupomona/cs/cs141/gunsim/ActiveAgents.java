package edu.csupomona.cs.cs141.gunsim;

public abstract class ActiveAgents {
	int hitPoints;

	public ActiveAgents(int hp) {
		hitPoints = hp;
	}

	public void tookDmg(int hp) {
		hitPoints = hitPoints - hp;
	}

	public int getHP() {
		return hitPoints;
	}

}
