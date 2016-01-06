package de.hsh.prog.gogodie.game.monster;

import java.util.Random;

import de.hsh.prog.gogodie.game.actor.Actor;
import de.hsh.prog.gogodie.game.gfx.SpriteSheet;

public class Blutfleck extends Actor {

	public Blutfleck(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.sprite = new SpriteSheet("/res/blut.png", 16, 16, 10);
		init();
		Random rand = new Random();
		int i = rand.nextInt(2);
		if (i==0){
	        sprite.playAnimation(MonsterAnimation.Blutfleck1, true);	
		}
		if (i==1){
	        sprite.playAnimation(MonsterAnimation.Blutfleck2, true);		
		}
		if (i==2){
	        sprite.playAnimation(MonsterAnimation.Blutfleck3, true);		
		}
	}

	private void init() {
		sprite.addAnimation(MonsterAnimation.Blutfleck1,6,7,8,9);
		sprite.addAnimation(MonsterAnimation.Blutfleck2,16,17,18,19);
		sprite.addAnimation(MonsterAnimation.Blutfleck3,27,28,29);
	}
	
	public void update() {
		if(!sprite.isLastFrame()) {
			sprite.update();
		}
			
	}
	
}
