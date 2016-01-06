package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.actor.Player;

public abstract class Buff extends PowerUp {
	
	protected int duration;

	public Buff(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public int getDuration() {
		return duration;
	}
	
	public boolean isInvalid() {
		return getTick() >= duration * 60;
	}
	
	public abstract void uneffect(Player player);

}
