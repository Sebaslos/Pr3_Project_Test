package de.hsh.prog.gogodie.game.monster;

import de.hsh.prog.gogodie.game.actor.Direction;

public class SpeedMonster extends Monster {

	public SpeedMonster(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
		setHP(5);
		setACT(1);
		setSpeed(4);
		
		type = 2;
		point = 100;
	}

	private void init(){
		sprite.addAnimation(MonsterAnimation.SPEED_MONSTER_WALK_UP, 42);
        sprite.addAnimation(MonsterAnimation.SPEED_MONSTER_WALK_DOWN, 43);
        sprite.addAnimation(MonsterAnimation.SPEED_MONSTER_WALK_LEFT, 40);
        sprite.addAnimation(MonsterAnimation.SPEED_MONSTER_WALK_RIGHT, 41);
        
        sprite.playAnimation(MonsterAnimation.SPEED_MONSTER_WALK_DOWN, false);
	}
	
	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            sprite.playAnimation(MonsterAnimation.SPEED_MONSTER_WALK_LEFT, true);
            break;
        case RIGHT:
            x += speed;
            sprite.playAnimation(MonsterAnimation.SPEED_MONSTER_WALK_RIGHT, true);
            break;
        case UP:
            y -= speed;
            sprite.playAnimation(MonsterAnimation.SPEED_MONSTER_WALK_UP, true);
            break;
        case DOWN:
            y += speed;
            sprite.playAnimation(MonsterAnimation.SPEED_MONSTER_WALK_DOWN, true);
            break;
		}
	}

}
