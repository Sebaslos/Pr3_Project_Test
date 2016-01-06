package de.hsh.prog.gogodie.game.waffe;

public class Machinegun extends Waffe {

	public Machinegun(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.munitionNum = 30;
		this.restMunition = munitionNum;
		this.cooldownTime = 15;
		this.reloadTime = 90;
		
		this.munition_type = Munition.SPEED;
		
		this.sfx_shoot = "machinegun";
		this.sfx_reload = "mg_reload";
		this.setImage(23);
	}
	
}
