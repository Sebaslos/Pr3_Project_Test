package de.hsh.prog.gogodie.game.monster;

import java.awt.Rectangle;

import de.hsh.prog.gogodie.game.actor.Direction;
import de.hsh.prog.gogodie.game.actor.Mob;

public abstract class Monster extends Mob{
	
	protected int HP;
	protected int ACT;
	
	private boolean alive;
	private Direction currentDirection = Direction.DOWN;
	
	public Monster(Rectangle bound) {
		super(bound);
		this.alive = true;
		setSpeed(1);
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
	
	protected void attact(){
		
	}

	@Override
	public void update() {
		if(MonsterFactory.player_x - getX()>0&&currentDirection!=Direction.RIGHT){
			currentDirection = Direction.RIGHT;
			//move(Direction.RIGHT);
		}else
		if(MonsterFactory.player_x - getX()<0&&currentDirection != Direction.LEFT){
			currentDirection = Direction.LEFT;
			//move(Direction.LEFT);
		}else 
		if(MonsterFactory.player_y - getY()>0&&currentDirection != Direction.DOWN){
			currentDirection = Direction.DOWN;
			//move(Direction.DOWN);
		}else
		if(MonsterFactory.player_y - getY()<0&&currentDirection != Direction.UP){
			currentDirection = Direction.UP;
			//move(Direction.UP);
		}
		move(currentDirection);
		super.update();
	}
	
}
