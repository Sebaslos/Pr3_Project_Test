package de.hsh.prog.gogodie.game.waffe;

import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.actor.Actor;
import de.hsh.prog.gogodie.game.actor.ActorList;
import de.hsh.prog.gogodie.game.actor.Direction;
import de.hsh.prog.gogodie.game.actor.Mob;
import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.monster.Monster;
import de.hsh.prog.gogodie.game.utils.Content;

public class Munition extends Mob {
	
	public static final int NORMAL = 1;
	public static final int SPEED = 2;
	public static final int ACT = 3;
	
	private int type;

	private boolean hit = false;
	private double radio;
	//private Direction currentDirection;
	
	public Munition(int x, int y, Direction d) {
		this.x = x;
		this.y = y;
		//this.currentDirection = d;
		
		this.width = 16;
		this.height = 16;
		
		this.setImage(33);
		this.setACT(5);
		this.setSpeed(10);
	}
	
	public Munition(int x, int y, int type, double radio, int atk) {
		this.radio = radio;
		setPosition(x, y);
		
		this.type = type;
		
		this.width = 16;
		this.height = 16;
		
		this.setImage(33);
		
		if(this.type == NORMAL) {
			this.setACT(atk);  //5
			this.setSpeed(10);
		}else if(this.type == SPEED) {
			this.setACT(atk);   //3
			this.setSpeed(15);
		}else if(this.type == ACT) {
			this.setACT(atk);   //10
			this.setSpeed(10);
		}
		
		rotateMunition();
	}
	
	private void setPosition(int x, int y) {
		double ox = x + 8 * Math.sqrt(2) * Math.cos(radio);
		double oy = y + 8 * Math.sqrt(2) * Math.sin(radio);
		
		this.x = (int)(ox - 8);
		this.y = (int)(oy - 8);
	}

	public boolean hasHit() {
		return hit;
	}
	
	public void update() {
		Actor actor = hasCollision(radio);
		if(actor == null) {
			move();
		}else {
			hit = true;
			ActorList.actorList.remove(this);
			if(actor instanceof Monster) {
				((Monster) actor).setHP(((Monster) actor).getHP() - this.getACT());
			}
			if(actor instanceof Player) {
				System.out.println("shoot player selbst");
			}
		}
			
	}

	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            break;
        case RIGHT:
            x += speed;
            break;
        case UP:
            y -= speed;
            break;
        case DOWN:
            y += speed;
            break;
		}
	}
	
	private void rotateMunition() {
		BufferedImage timg = Content.rotateImage(this.getStaticFrame(), radio);
		this.setImage(timg);
	}
	
	private void move() {
		x += speed * Math.cos(radio);
		y += speed * Math.sin(radio);
	}
	
}
