package de.hsh.prog.gogodie.game.monster;

import de.hsh.prog.gogodie.game.actor.Direction;

public class Boss2 extends Monster {

	public Boss2(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
		setHP(250);
		setACT(5);
		setSpeed(4);
		
		type = 4;
		point = 5000;
	}

	private void init(){
		sprite.addAnimation(MonsterAnimation.BOSS2_WALK_UP, 55);
        sprite.addAnimation(MonsterAnimation.BOSS2_WALK_DOWN, 52);
        sprite.addAnimation(MonsterAnimation.BOSS2_WALK_LEFT, 53);
        sprite.addAnimation(MonsterAnimation.BOSS2_WALK_RIGHT, 54);
        
        sprite.playAnimation(MonsterAnimation.BOSS2_WALK_DOWN, false);
	}
	
	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            sprite.playAnimation(MonsterAnimation.BOSS2_WALK_LEFT, true);
            break;
        case RIGHT:
            x += speed;
            sprite.playAnimation(MonsterAnimation.BOSS2_WALK_RIGHT, true);
            break;
        case UP:
            y -= speed;
            sprite.playAnimation(MonsterAnimation.BOSS2_WALK_UP, true);
            break;
        case DOWN:
            y += speed;
            sprite.playAnimation(MonsterAnimation.BOSS2_WALK_DOWN, true);
            break;
		}
	}

}
