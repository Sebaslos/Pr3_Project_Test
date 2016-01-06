package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.actor.Player;

public class SpeedUp extends Buff {

	private int upspeed;
	
	public SpeedUp(int x, int y, int width, int height) {
		super(x, y, width, height);
		duration = 5;
		upspeed = 2;
		
		setImage(5);
	}

	@Override
	public void effect(Player player) {
		player.setSpeed(player.getSpeed() + upspeed);
	}
	
	@Override
	public void uneffect(Player player) {
		player.setSpeed(player.getSpeed() - upspeed);
	}

}
