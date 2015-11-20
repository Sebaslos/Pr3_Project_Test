package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.gfx.SpriteSheet;

public abstract class Actor {

	protected Rectangle bound;
	protected SpriteSheet sprite;
	
	public Actor(SpriteSheet sprite,Rectangle bound) {
		this.sprite = sprite;
		this.bound = bound;
	}
	
	public BufferedImage getFrame(int index){
		return sprite.getFrame(index);
	}
	
}
