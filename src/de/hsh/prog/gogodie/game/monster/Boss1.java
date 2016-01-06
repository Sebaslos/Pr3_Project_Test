package de.hsh.prog.gogodie.game.monster;

import de.hsh.prog.gogodie.game.actor.Direction;

public class Boss1 extends Monster {

	public Boss1(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
		setHP(100);
		setACT(3);
		setSpeed(2);
		
		type = 4;
		point = 5000;
	}

	private void init(){
		sprite.addAnimation(MonsterAnimation.BOSS1_WALK_UP, 47);
        sprite.addAnimation(MonsterAnimation.BOSS1_WALK_DOWN, 44);
        sprite.addAnimation(MonsterAnimation.BOSS1_WALK_LEFT, 45);
        sprite.addAnimation(MonsterAnimation.BOSS1_WALK_RIGHT, 46);
        
        sprite.playAnimation(MonsterAnimation.BOSS1_WALK_DOWN, false);
	}
	
	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            sprite.playAnimation(MonsterAnimation.BOSS1_WALK_LEFT, true);
            break;
        case RIGHT:
            x += speed;
            sprite.playAnimation(MonsterAnimation.BOSS1_WALK_RIGHT, true);
            break;
        case UP:
            y -= speed;
            sprite.playAnimation(MonsterAnimation.BOSS1_WALK_UP, true);
            break;
        case DOWN:
            y += speed;
            sprite.playAnimation(MonsterAnimation.BOSS1_WALK_DOWN, true);
            break;
		}
	}

}
