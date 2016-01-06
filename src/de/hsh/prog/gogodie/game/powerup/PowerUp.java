package de.hsh.prog.gogodie.game.powerup;

import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.actor.Actor;
import de.hsh.prog.gogodie.game.actor.Player;

public abstract class PowerUp extends Actor {

	private int tick;
	
	public PowerUp(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		tick = 0;
	}
	
	public int getTick() {
		return tick;
	}
	
	public void setTick(int tick) {
		this.tick = tick;
	}
	
	public void update() {
		tick++;
	}
	
	public abstract void effect(Player player);
	
	public void draw(Graphics2D g) {
		g.drawImage(this.getStaticFrame(), this.getX(), this.getY(), (int)(this.getWidth() * 1.3), (int)(this.getHeight() * 1.3), null);
	}
	
}
