package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.waffe.Machinegun;
import de.hsh.prog.gogodie.game.waffe.Pistol;
import de.hsh.prog.gogodie.game.waffe.Shotgun;

public class PowerupFactory {
	
	private static double probability = 0.5;
	
	public static PowerUp createPowerup(int x, int y) {
		
		double n = Math.random();
		if(n < probability) {
			PowerUp p = null;
			n = Math.random();
			if(n < 0.1) {
				p = new Pistol(x, y, 16, 16);
			}else if(n >= 0.1 && n < 0.2) {
				p = new Shotgun(x, y, 16, 16);
			}else if(n >= 0.2 && n < 0.3) {
				p = new Machinegun(x, y, 16, 16);
			}else if(n >= 0.3 && n < 0.45) {
				p = new Shield(x, y, 16, 16);
			}else if(n >= 0.45 && n < 0.6) {
				p = new EndlessBullet(x, y, 16, 16);
			}else if(n >= 0.6 && n < 0.8) {
				p = new HpRecovery(x, y, 16, 16);
			}else {
				p = new SpeedUp(x, y, 16, 16);
			}
			
			return p;
		}
		
		return null;
	}

}
