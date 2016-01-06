package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.actor.Player;

public class Shield extends Buff {

	public Shield(int x, int y, int width, int height) {
		super(x, y, width, height);
		duration = 5;
		
		setImage(13);
	}
	
	@Override
	public void effect(Player player) {
		player.setHasShield(true);
	}
	
	@Override
	public void uneffect(Player player) {
		player.setHasShield(false);
	}

}
