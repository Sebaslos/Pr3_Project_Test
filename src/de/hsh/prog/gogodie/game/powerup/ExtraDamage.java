package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.actor.Player;

public class ExtraDamage extends Buff {
	
	private int originATK;

	public ExtraDamage(int x, int y, int width, int height) {
		super(x, y, width, height);
		duration = 10;
		
		setImage(31);
	}

	@Override
	public void uneffect(Player player) {
		player.getWaffe().setATK(originATK);
	}

	@Override
	public void effect(Player player) {
		originATK = player.getWaffe().getATK();
		player.getWaffe().setATK(originATK * 2);
	}

}
