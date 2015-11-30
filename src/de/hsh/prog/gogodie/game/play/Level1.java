package de.hsh.prog.gogodie.game.play;

import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.map.Map;
import de.hsh.prog.gogodie.game.monster.MonsterFactory;

public class Level1 extends PlayBoard {
	
	private int monster_num;
	private int i = 0;
	
	public Level1(Player player) {
		super(player);
		monster_num = 100;
		map = new Map("/res/map1.png");
		init();
	}

	@Override
	protected void createMonster(int i) {
		if(i%4==0){
			monsters.add(MonsterFactory.createMonster("NormalMonster", 0, 0));
		}else if(i%4==1){
			monsters.add(MonsterFactory.createMonster("NormalMonster", 0, 720));
		}else if(i%4==2){
			monsters.add(MonsterFactory.createMonster("NormalMonster", 1280, 720));
		}else if(i%4==3){
			monsters.add(MonsterFactory.createMonster("NormalMonster", 1280, 0));
		}
	}

	@Override
	public void create() {
		if(i<monster_num){
			createMonster(i++);
		}
	}

}
