package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.waffe.Machinegun;
import de.hsh.prog.gogodie.game.waffe.Pistol;
import de.hsh.prog.gogodie.game.waffe.Shotgun;

public class PowerupFactory {
	
	private static double probability = 0.25;
	
	public static PowerUp createPowerup(int x, int y) {
		
		double n = Math.random();
		if(n < probability) {
			PowerUp p = null;
			n = Math.random();
			if(n < 0.075) {
				p = new Pistol(x, y, 16, 16);
			}else if(n >= 0.075 && n < 0.175) {
				p = new Shotgun(x, y, 16, 16);
			}else if(n >= 0.175 && n < 0.3) {
				p = new Machinegun(x, y, 16, 16);
			}else if(n >= 0.3 && n < 0.395) {
				p = new Shield(x, y, 16, 16);
			}else if(n >= 0.395 && n < 0.5) {
				p = new EndlessBullet(x, y, 16, 16);
			}else if(n >= 0.5 && n < 0.775) {
				p = new HpRecovery(x, y, 16, 16);
			}else if(n >= 0.775 && n < 0.875){
				p = new SpeedUp(x, y, 16, 16);
			}else {
				p = new ExtraDamage(x, y, 16, 16);
			}
			
			return p;
		}
		
		return null;
	}

}
