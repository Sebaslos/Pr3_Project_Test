package de.hsh.prog.gogodie.game.waffe;

public class Shotgun extends Waffe {

	public Shotgun(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.munitionNum = 7;
		this.restMunition = munitionNum;
		this.cooldownTime = 60;
		this.reloadTime = 120;
		
		this.munition_type = Munition.ACT;
		
		this.sfx_shoot = "shotgun";
		this.sfx_reload = "sg_reload";
		this.setImage(15);
	}
	
}
