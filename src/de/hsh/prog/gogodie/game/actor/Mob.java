package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Mob extends Actor {

	protected int HP;
	protected int ACT;
	protected int speed = 2;
	
	public Mob(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public Mob() {}
	
	public void update() {
        sprite.update();
    }

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		this.HP = hP;
	}

	public int getACT() {
		return ACT;
	}

	public void setACT(int aCT) {
		this.ACT = aCT;
	}
	
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    public BufferedImage getFrame() {
        return sprite.getCurrentFrame();
    }
    
    protected Actor hasCollision(Direction d) {
		Rectangle r;
		switch (d) {
		case LEFT:
			r = new Rectangle(x-speed, y, width, height);
			break;
		case RIGHT:
			r = new Rectangle(x+speed, y, width, height);
			break;
		case UP:
			r = new Rectangle(x, y-speed, width, height);
			break;
		case DOWN:
			r = new Rectangle(x, y+speed, width, height);
			break;
		default:
			r = new Rectangle(x, y, width, height);
			break;
		}
		
		for(Actor actor : ActorList.actorList) {
			if(actor!=this && r.intersects(actor.getBounds())) {
				return actor;
			}
		}
		return null;
	}
    
    protected Actor hasCollision(double radio) {
    	Rectangle r = null;
    	
    	r = new Rectangle((int)(x + speed * Math.cos(radio)), (int)(y + speed * Math.sin(radio)), width, height);
    	
    	for(Actor actor : ActorList.actorList) {
			if(actor!=this && r.intersects(actor.getBounds())) {
				return actor;
			}
		}
		return null;
    }
    
	protected abstract void move(Direction d);
	
}
