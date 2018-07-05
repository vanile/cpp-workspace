/**
 * 
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
 * @author thefurryman
 * The base UI of the whole program, user interacts and activity
 * is conducted from this class.
 */
public class MainUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		selectGun();	
	}
	
	/**
	 * Prompts the user using scanner and if/else to select a gun and will redirect to one of 3 gun 
	 * methods depending on gun chosen
	 */
	public static void selectGun() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select one of the following guns:");
		System.out.println("1) handgun");
		System.out.println("2) rifle");
		System.out.println("3) shotgun");
		int selectedGun = sc.nextInt();
		System.out.println("You have selected a gun");
		
		if (selectedGun == 1) {
			useHandgun();
		} else if (selectedGun == 2) {
			useRifle();
		} else if (selectedGun == 3) {
			useShotgun();
		} else {
			useHandgun();
		}
		sc.nextLine();
	}
	
	/**
	 * The handgun method where the rest of the activity is conducted. The handgun object
	 * is created and all attributes are initialized. 5 target objects are initialized.
	 * 5 while loops are used to correspond to 5 targets based on remaining ammo. Within the
	 * while loops the RNG method is called to determine whether or not the target is actually shot.
	 * Custom and full options to reload are available after all targets
	 * are destroyed. Afterwards, each target status is shown with boolean type.
	 */
	public static void useHandgun() {
		Gun handgun = new Gun();
		handgun.createGun("handgun");
		handgun.getAccuracy();
		handgun.getAmmo();
		userOptions();
		
		Target target1 = new Target();
		Target target2 = new Target();
		Target target3 = new Target();
		Target target4 = new Target();
		Target target5 = new Target();
		
		while (handgun.getAmmo() > 0) {
			handgun.shoot();
			handgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 85) {
				target1.targetShot();
				System.out.println("Target1 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + handgun.getAmmo());
		System.out.println("");
		
		while (handgun.getAmmo() > 0) {
			handgun.shoot();
			handgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 85) {
				target2.targetShot();
				System.out.println("Target2 shot!");

				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + handgun.getAmmo());
		System.out.println("");
		
		while (handgun.getAmmo() > 0) {
			handgun.shoot();
			handgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 85) {
				target3.targetShot();
				System.out.println("Target3 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + handgun.getAmmo());
		System.out.println("");
		
		while (handgun.getAmmo() > 0) {
			handgun.shoot();
			handgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 85) {
				target4.targetShot();
				System.out.println("Target4 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + handgun.getAmmo());
		System.out.println("");
		
		while (handgun.getAmmo() > 0) {
			handgun.shoot();
			handgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 85) {
				target5.targetShot();
				System.out.println("Target5 shot!");

				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + handgun.getAmmo());
		System.out.println("");
		
		if (handgun.getAmmo() == 0) {
			Scanner sc = new Scanner(System.in);
			String reloadOption = sc.nextLine();
			System.out.println("Would you like to fully reload or choose a custom amount of ammo?");
			System.out.println("'full' or 'custom' ammo");
			if (reloadOption == "full") {
				handgun.reload("handgun");
			} else if (reloadOption == "custom") {
				Scanner sc2 = new Scanner(System.in);
				int custAmmo = sc2.nextInt();
				handgun.reload(custAmmo);
				sc2.nextLine();
			}
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to fully reload or choose a custom amount of ammo?");
		System.out.println("'1) full' or '2) custom' ammo");
		int reloadOption = sc.nextInt();
	
		if (reloadOption == 1) {
			handgun.reload("handgun");
		} else if (reloadOption == 2) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("how much?");
			int custAmmo = sc2.nextInt();
			handgun.reload(custAmmo);
		}
		System.out.println("Ammo remaining: " + handgun.getAmmo());
		
		System.out.println("Targets destroyed?");
		System.out.println(target1.status());
		System.out.println(target2.status());
		System.out.println(target3.status());
		System.out.println(target4.status());
		System.out.println(target5.status());
		System.out.println("Course complete!");
	
	}
	
	/**
	 * The rifle method. Same as {@link useHandgun()} but with 
	 * rifle attributes.
	 */
	public static void useRifle() {
		Gun rifle = new Gun();
		rifle.createGun("rifle");
		rifle.getAmmo();
		rifle.getAccuracy();
		
		userOptions();
		
		Target target1 = new Target();
		Target target2 = new Target();
		Target target3 = new Target();
		Target target4 = new Target();
		Target target5 = new Target();
		
		while (rifle.getAmmo() > 0) {
			rifle.shoot();
			rifle.getAmmo();
			
			if (Gun.accuracyRNG() <= 90) {
				target1.targetShot();
				System.out.println("Target1 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + rifle.getAmmo());
		System.out.println("");
		
		while (rifle.getAmmo() > 0) {
			rifle.shoot();
			rifle.getAmmo();
			
			if (Gun.accuracyRNG() <= 90) {
				target2.targetShot();
				System.out.println("Target2 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + rifle.getAmmo());
		System.out.println("");
		
		
		while (rifle.getAmmo() > 0) {
			rifle.shoot();
			rifle.getAmmo();
			
			if (Gun.accuracyRNG() <= 90) {
				target1.targetShot();
				System.out.println("Target3 shot!");
				target3.targetShot();
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		
		System.out.println("Ammo remaining: " + rifle.getAmmo());
		System.out.println("");
		
		while (rifle.getAmmo() > 0) {
			rifle.shoot();
			rifle.getAmmo();
			
			if (Gun.accuracyRNG() <= 90) {
				target1.targetShot();
				System.out.println("Target4 shot!");
				target4.targetShot();
				break;
			} else {
				System.out.println("Target missed");
			}
		}

		
		System.out.println("Ammo remaining: " + rifle.getAmmo());
		System.out.println("");
		
		while (rifle.getAmmo() > 0) {
			rifle.shoot();
			rifle.getAmmo();
			
			if (Gun.accuracyRNG() <= 90) {
				target1.targetShot();
				System.out.println("Target5 shot!");
				target5.targetShot();
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + rifle.getAmmo());
		System.out.println("");
		
		Scanner sca = new Scanner(System.in);
		System.out.println("Would you like to fully reload or choose a custom amount of ammo?");
		System.out.println("'full' or 'custom' ammo");
		int reloadOption2 = sca.nextInt();
		
		
		if (reloadOption2 == 1) {
			rifle.reload("rifle");
		} else if (reloadOption2 == 2) {
			Scanner sc2 = new Scanner(System.in);
			System.out.println("how much?");
			int custAmmo = sc2.nextInt();
			rifle.reload(custAmmo);
			sc2.nextLine();
		}
		System.out.println("Ammo remaining: " + rifle.getAmmo());
		System.out.println("Targets destroyed?");
		System.out.println(target1.status());
		System.out.println(target2.status());
		System.out.println(target3.status());
		System.out.println(target4.status());
		System.out.println(target5.status());
		System.out.println("Course complete!"); 

		}
	
	
	/**
	 * The shotgun method. Same as {@link useHandgun()} but with
	 * shotgun attributes
	 */
	public static void useShotgun() {
		Gun shotgun = new Gun();
		shotgun.createGun("shotgun");
		shotgun.getAmmo();
		shotgun.getAccuracy();
		
		userOptions();
		
		Target target1 = new Target();
		Target target2 = new Target();
		Target target3 = new Target();
		Target target4 = new Target();
		Target target5 = new Target();
		
		while (shotgun.getAmmo() > 0) {
			shotgun.shoot();
			shotgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 70) {
				target1.targetShot();
				System.out.println("Target1 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + shotgun.getAmmo());
		System.out.println("");
		
		while (shotgun.getAmmo() > 0) {
			shotgun.shoot();
			shotgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 70) {
				target2.targetShot();
				System.out.println("Target2 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + shotgun.getAmmo());
		System.out.println("");
		
		
		while (shotgun.getAmmo() > 0) {
			shotgun.shoot();
			shotgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 70) {
				target1.targetShot();
				System.out.println("Target3 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
	
		
		System.out.println("Ammo remaining: " + shotgun.getAmmo());
		System.out.println("");
		
		while (shotgun.getAmmo() > 0) {
			shotgun.shoot();
			shotgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 70) {
				target1.targetShot();
				System.out.println("Target4 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		
		System.out.println("Ammo remaining: " + shotgun.getAmmo());
		System.out.println("");
		
		while (shotgun.getAmmo() > 0) {
			shotgun.shoot();
			shotgun.getAmmo();
			
			if (Gun.accuracyRNG() <= 70) {
				target1.targetShot();
				System.out.println("Target5 shot!");
				break;
			} else {
				System.out.println("Target missed");
			}
		}
		
		System.out.println("Ammo remaining: " + shotgun.getAmmo());
		System.out.println("");
		
		Scanner sca = new Scanner(System.in);
		System.out.println("Would you like to fully reload or choose a custom amount of ammo?");
		System.out.println("'1) full' or '2) custom' ammo");
		int reloadOption2 = sca.nextInt();
		if (reloadOption2 == 1) {
			shotgun.reload("shotgun");
		} else if (reloadOption2 == 2) {
			Scanner sc2 = new Scanner(System.in);
			int custAmmo = sc2.nextInt();
			System.out.println("how much?");
			shotgun.reload(custAmmo);
			sc2.nextLine();
		}
		
		System.out.println(shotgun.getAmmo());
		
		System.out.println("Targets destroyed?");
		System.out.println(target1.status());
		System.out.println(target2.status());
		System.out.println(target3.status());
		System.out.println(target4.status());
		System.out.println(target5.status());
		System.out.println("Course complete!"); 

		}

	/**
	 *  This method follows in sequence after the user selects their gun. This prompts
	 *  the user of whether they would like to shoot targets or quit the program using
	 *  Scanner objects and if/else statements
	 */
	public static void userOptions() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Would you like to 1) 'shoot' or 2) 'quit'?");
		System.out.println("select the number");
		int userInput = sc.nextInt();
		
		if (userInput == 1) {
			System.out.println("5 targets have been created");
			
		} else if (userInput == 2) {
			System.out.println("Now quitting...");
			System.exit(0);
			
		} else {
			System.out.println("invalid option");
			System.out.println("restart program");
		}
		sc.nextLine();
			
	}
	
}
