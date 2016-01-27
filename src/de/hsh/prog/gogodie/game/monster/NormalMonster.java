package de.hsh.prog.gogodie.game.monster;

import de.hsh.prog.gogodie.game.actor.Direction;

public class NormalMonster extends Monster {

	public NormalMonster(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
		setHP(10);
		setACT(3);
		setSpeed(3);
		
		type = 1;
		point = 200;
	}
	
	private void init(){
		sprite.addAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_UP, 18);
        sprite.addAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_DOWN, 19);
        sprite.addAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_LEFT, 16);
        sprite.addAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_RIGHT, 17);
        
        sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_DOWN, false);
	}

	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_LEFT, true);
            break;
        case RIGHT:
            x += speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_RIGHT, true);
            break;
        case UP:
            y -= speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_UP, true);
            break;
        case DOWN:
            y += speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_DOWN, true);
            break;
		}
	}

}
