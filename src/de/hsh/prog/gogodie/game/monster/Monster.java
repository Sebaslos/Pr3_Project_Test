package de.hsh.prog.gogodie.game.monster;

import de.hsh.prog.gogodie.game.actor.Actor;
import de.hsh.prog.gogodie.game.actor.Direction;
import de.hsh.prog.gogodie.game.actor.Mob;
import de.hsh.prog.gogodie.game.actor.Player;

public abstract class Monster extends Mob{
	
	protected int type;
	protected int point;
	
	private boolean alive;
	private Direction currentDirection = Direction.DOWN;
	
	private int tick = 0;
	private int attackTick = 0;
	private int cooldownTime = 30;
	private boolean inCooldown = false;
	
	public Monster(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.alive = true;
	}
	
	protected void attack(Player player){
		if(!player.hasShield())
			player.setHP(player.getHP() - this.getACT());
		else 
			System.out.println("fuck! player hasShield");
		inCooldown = true;
		System.out.println("Player HP: "+player.getHP());
	}

	private void cooldown() {
		attackTick++;
		if(attackTick > cooldownTime) {
			attackTick = 0;
			inCooldown = false;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public int getPoint() {
		return point;
	}
	
	@Override
	public void update() {
		if(alive) {
			if(tick>=10) {
				int dx = MonsterFactory.player_x - getX();
				int dy = MonsterFactory.player_y - getY();
				if(Math.abs(dx) > Math.abs(dy)) {
					if(dx < 0)
						currentDirection = Direction.LEFT;
					else
						currentDirection = Direction.RIGHT;
				}else {
					if(dy < 0)
						currentDirection = Direction.UP;
					else
						currentDirection = Direction.DOWN;
				}
				tick = 0;
			}
			
			Actor solid = hasCollision(currentDirection);
			if(solid == null)
				move(currentDirection);
			else
				resolveCollision(solid);
			
			tick++;
			if(inCooldown) {
				cooldown();
			}
			super.update();
		}
	}
	
	private void resolveCollision(Actor solid) {
		if(solid instanceof Player) {
			if(!inCooldown)
				attack((Player)solid);
		}else {
			if(currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) {
				if(this.y >= solid.getY())
					currentDirection = Direction.DOWN;
				else 
					currentDirection = Direction.UP;
			}else if(currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
				if(this.x >= solid.getX())
					currentDirection = Direction.RIGHT;
				else 
					currentDirection = Direction.LEFT;
			}
		}
	}
	
}
