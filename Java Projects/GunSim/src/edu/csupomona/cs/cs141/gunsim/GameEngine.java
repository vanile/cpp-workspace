package edu.csupomona.cs.cs141.gunsim;

public class GameEngine {
	Player player;
	Enemy enemy;
	int position;
	UserInterface uInterface = new UserInterface();
	Gun currentGun;

	public GameEngine() {
		player = new Player();
		enemy = null;
		position = 0;
		currentGun = null;
	}

	public void play() {
		boolean play = true;
		do {
		uInterface.welcome();
		while (position < 10 && player != null) {
			if (uInterface.takeAStep()) {
				position++;
				uInterface.position(position);
				if (randEncounter()) {
					while (enemy != null && player != null) {
						if (uInterface.isFighting()) 
							attackPhase();
						 else {
							if (runAway()) {
								itemDropped();
								enemy = null;
							} else 
								enemyAttack();
							
						}
					}
				}
			} else {
				position = 12;
				play = false;
			}
		}
		if (position != 12) {
			uInterface.winOrlose(position, player);
			play = uInterface.playAgain();
		}
		if (play){
			player = new Player();
			enemy = null;
			position = 0;
			currentGun = null;
		}
			
		} while (position != 12 && play);
		
	}

	private void itemDropped() {
		double item = Math.random();
		if (item <= .3) {
			uInterface.healthPack();
			player.hitPoints += 5;
		}
		else {
			uInterface.ammoPack();
			currentGun = chooseGun();
			currentGun.reload();
			uInterface.showAmmo(currentGun.getAmmo());
		}
		
		
	}

	public void attackPhase() {
		currentGun = chooseGun();
		if (currentGun.shoot()) {
			enemy.tookDmg(currentGun.getDamage());
			uInterface.showAmmo(currentGun.getAmmo());
			if (!checkEnemyDead()) {
				enemyAttack();
			}
		} else {
			uInterface.showAmmo(currentGun.getAmmo());
			enemyAttack();
		}
	}

	public void enemyAttack() {
		uInterface.enemyAttack();
		if (enemy.getGun().shoot()) {
			player.tookDmg(enemy.getGun().getDamage());
			if (checkPlayerDead()) {
				uInterface.playerHP(player.getHP());
			} else {
				uInterface.playerDead();
			}
		}
	}

	public boolean checkPlayerDead() {
		if (player.getHP() <= 0) {
			player = null;
			return false;
		}
		return true;
	}

	public boolean checkEnemyDead() {
		if (enemy.getHP() <= 0) {
			enemy = null;
			uInterface.enemyDead();
			itemDropped();
			return true;
		}
		uInterface.enemyHP(enemy.getHP());
		return false;
	}

	public Gun chooseGun() {
		int gunNum = uInterface.pickGun();
		return player.getGun(gunNum);
	}

	public boolean randEncounter() {
		if (Math.random() <= .15) {
			enemy = new Enemy();
			enemy.chooseGun();
			uInterface.enemyEncounter(enemy.printGun());
			return true;
		}
		return false;
	}

	public boolean isEnemy() {
		if (enemy != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean runAway() {
		if (Math.random() >= .50) {
			enemy = null;
			uInterface.gotAway();
			return true;
		}
		uInterface.failedEscape();
		return false;
	}
}
