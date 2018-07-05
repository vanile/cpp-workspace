/**
 * 
 */
package edu.csupomona.cs.cs141.gunsim;

/**
 * @author Edwin Rodr&iacute;guez
 *
 */
public abstract class Gun implements Item{
	
	// inv : "invariant", a condition that must always
	//       hold for instances of this type.
	
	/**
	 * inv 0 <= accurary < 1;
	 */
	private double accuracy;
	
	private int ammo;
	
	/**
	 * inv ammo <= clipSize;
	 */
	private int clipSize;
	
	/**
	 * inv type == "Handgun" ||
	 *     type == "Shotgun" ||
	 *     type == "Rifle";
	 */
	private String type;
	
	private int damage;
	
	public Gun() {
		accuracy = .85;
		clipSize = 13;
		type = "Handgun";
		ammo = clipSize;
	}
	
	public Gun(double acc, int cs, String ty, int dmg) {
		accuracy = acc;
		clipSize = cs;
		type = ty;
		ammo = cs;
		damage = dmg;
	}
	
	public void reload() {
		ammo = clipSize;
	}
	
	/**
	 * @pre: amm >= 0;
	 * 
	 * @param amm
	 */
	public void reload(int amm) {
		assert (amm >= 0);
		
		if ((amm + ammo) <= clipSize) {
			ammo += amm;
		} else {
			reload();
		}
	}
	
	public abstract boolean shoot();
	
	public String getType() {
		return type;
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void minusAmmo()
	{
		ammo--;
	}
	
	public double getAccuracy()
	{
		return accuracy;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	

}
