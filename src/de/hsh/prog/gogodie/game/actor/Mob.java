package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Mob extends Actor {

	protected int speed = 2;
	
	public Mob(Rectangle bound) {
		super(bound);
	}
	
	public void update() {
        sprite.update();
    }

    public Rectangle getBounds() {
        return bound;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed){
    	this.speed = speed;
    }
    
    public int getX() {
        return bound.x;
    }

    public int getY() {
        return bound.y;
    }
	
    public BufferedImage getFrame() {
        return sprite.getCurrentFrame();
    }
    
	protected abstract void move(Direction d);
	
}
