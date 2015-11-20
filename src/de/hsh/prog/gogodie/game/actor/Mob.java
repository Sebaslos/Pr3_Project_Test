package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.gfx.SpriteSheet;
public class Mob extends Actor {

	private int speed = 2;
	
	public Mob(SpriteSheet sprite,Rectangle bound) {
		super(sprite,bound);
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
    
    public int getX() {
        return bound.x;
    }

    public int getY() {
        return bound.y;
    }
	
    public BufferedImage getFrame() {
        return sprite.getCurrentFrame();
    }
    
	protected void move(Direction d){
		switch (d) {
        case LEFT:
            bound.x -= speed;
            sprite.playAnimation(PlayerAnimation.WALK_LEFT, true);
            break;
        case RIGHT:
            bound.x += speed;
            sprite.playAnimation(PlayerAnimation.WALK_RIGHT, true);
            break;
        case UP:
            bound.y -= speed;
            sprite.playAnimation(PlayerAnimation.WALK_UP, true);
            break;
        case DOWN:
            bound.y += speed;
            sprite.playAnimation(PlayerAnimation.WALK_DOWN, true);
            break;
		}
	}
}
