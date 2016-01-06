package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.actor.Player;

public class EndlessBullet extends Buff {
	
	public EndlessBullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		duration = 10;
		
		setImage(30);
	}

	@Override
	public void effect(Player player) {
		player.getWaffe().setEndlessMunition(true);
	}

	@Override
	public void uneffect(Player player) {
		player.getWaffe().setEndlessMunition(false);
	}

}
