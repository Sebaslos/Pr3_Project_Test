package de.hsh.prog.gogodie.game.waffe;

public class Pistol extends Waffe {

	public Pistol(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.munitionNum = 12;
		this.restMunition = munitionNum;
		
		this.munition_type = Munition.NORMAL;
		
		this.sfx_shoot = "pistol";
		this.sfx_reload = "pt_reload";
		this.setImage(14);
	}
	
}