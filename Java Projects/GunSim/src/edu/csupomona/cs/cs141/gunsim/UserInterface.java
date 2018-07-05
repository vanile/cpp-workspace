/**
 * 
 */
package edu.csupomona.cs.cs141.gunsim;

import java.util.Scanner;

/**
 * @author George
 *
 */
public class UserInterface {
	Scanner sc;

	public UserInterface() {
		sc = new Scanner(System.in);
	}
	public void welcome() {
		System.out.println("Welcome to Escape the Dungeon!");
		System.out.println("You must survive the Dungeon for 10 steps.");
	}

	public boolean takeAStep() {
		System.out.println("Take a Step?");
		System.out.println("(1) Yes");
		System.out.println("(2) Quit");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			System.out.println("You took a step");
			return true;

		default:
			System.out.println("Goodbye");
		}
		return false;
	}

	public boolean isFighting() {
		System.out.println("Choose an action");
		System.out.println("(1) Shoot");
		System.out.println("(2) Try To Escape");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			return true;
		case 2:
			break;
		default:
			System.out
					.println("Not a valid choice. You will now try to escape");
		}
		return false;
	}

	public int pickGun() {
		// Classes used for demonstration: Main, ActiveAgents, Enemy,
		// Player, Gun, Shotgun

		// Asks user which gun to use and declares the gun
		System.out.println("Choose a gun: (0) Handgun (1) Shotgun (2) Rifle ");

		// Records how much damage the gun will do.

		int option = sc.nextInt();

		switch (option) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;

		default:
			System.out.println("Not a valid choice. Handgun has been chosen");
			return 1;
		}
	}

	public void position(int position) {
		System.out.println("You have taken " + position + " steps");
		
	}

	public void winOrlose(int position, Player player) {
		if (position != 12 && player != null) {
			System.out.println("YOU WIN!!!");
		} else {
			System.out.println("You lose...");
		}
		
	}

	public void enemyAttack() {
		System.out.println("Enemy has Attacked!");
		
	}

	public void playerHP(int hp) {
		System.out.println("You have " + hp
				+ " hp remaining");
		
	}

	public void playerDead() {
		System.out.println("You are dead...");
		
	}

	public void enemyDead() {
		System.out.println("Enemy is dead. You can continue on.");
		
	}

	public void enemyHP(int hp) {
		System.out.println("Enemy has " + hp + " hp left");
		
	}

	public void enemyEncounter(String printGun) {
		System.out.println("You encountered an enemy! The enemy has a "
				+ printGun);
	}

	public void gotAway() {
		System.out.println("You got away safely.");
		
	}

	public void failedEscape() {
		System.out.println("Can't escape!");
		
	}

	public void shot(String shot) {
		System.out.println(shot + "!");
		
	}

	public void missedShot() {
		System.out.println("Missed");
		
	}
	public void healthPack() {
		System.out.println("You found a Health Pack, your health is fully restored");
		
	}
	public void ammoPack() {
		System.out.println("You found an Ammo Pack, which Gun would you like to reload?");
	}
	public void showAmmo(int ammo) {
		System.out.println("You have " + ammo + " bullets left in this Gun");
		
	}
	public boolean playAgain() {
		boolean playAgain;
		System.out.println("Would you like to play Again?");
		System.out.println("(1) Yes");
		System.out.println("(2) No");
		if (sc.nextInt() == 1)
			playAgain = true;
		else 
			playAgain = false;
		return playAgain;
	}

}
