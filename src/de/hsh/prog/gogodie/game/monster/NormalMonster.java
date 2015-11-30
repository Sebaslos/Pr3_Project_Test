package de.hsh.prog.gogodie.game.monster;

import java.awt.Rectangle;

import de.hsh.prog.gogodie.game.actor.Direction;

public class NormalMonster extends Monster {

	public NormalMonster(Rectangle bound) {
		super(bound);
		init();
		setHP(10);
		setACT(2);
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
            bound.x -= speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_LEFT, true);
            break;
        case RIGHT:
            bound.x += speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_RIGHT, true);
            break;
        case UP:
            bound.y -= speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_UP, true);
            break;
        case DOWN:
            bound.y += speed;
            sprite.playAnimation(MonsterAnimation.NORMAL_MONSTER_WALK_DOWN, true);
            break;
		}
	}

}
