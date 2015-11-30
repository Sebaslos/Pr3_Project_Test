package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.gfx.SpriteSheet;

public abstract class Actor {

	protected Rectangle bound;
	protected SpriteSheet sprite;
	
	public Actor(Rectangle bound) {
		this.sprite = new SpriteSheet("/res/sprite_sheet.png", 16, 16, 10);
		this.bound = bound;
	}
	
	public BufferedImage getFrame(int index){
		return sprite.getFrame(index);
	}
	
}
