package de.hsh.prog.gogodie.game.monster;

import de.hsh.prog.gogodie.game.actor.Direction;

public class SlowlyMonster extends Monster {

	public SlowlyMonster(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
		setHP(15);
		setACT(5);
		setSpeed(2);
		
		type = 3;
		point = 300;
	}
	
	private void init(){
		sprite.addAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_UP, 50);
        sprite.addAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_DOWN, 51);
        sprite.addAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_LEFT, 48);
        sprite.addAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_RIGHT, 49);
        
        sprite.playAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_DOWN, false);
	}

	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            sprite.playAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_LEFT, true);
            break;
        case RIGHT:
            x += speed;
            sprite.playAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_RIGHT, true);
            break;
        case UP:
            y -= speed;
            sprite.playAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_UP, true);
            break;
        case DOWN:
            y += speed;
            sprite.playAnimation(MonsterAnimation.SLOWLY_MONSTER_WALK_DOWN, true);
            break;
		}
	}

}
