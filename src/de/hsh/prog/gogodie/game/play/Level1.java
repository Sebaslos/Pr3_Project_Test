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
		double randomzahl = Math.random();
		if (randomzahl < 0.25)
			monsters.add(MonsterFactory.createMonster("NormalMonster",
					(int) (Math.random() * 1280), (int) (Math.random() * 80)));
		if (0.25 <= randomzahl && randomzahl < 0.5)
			monsters.add(MonsterFactory.createMonster("NormalMonster",
					(int) (Math.random() * 1280),
					640 + (int) (Math.random() * 80)));
		if (0.5 <= randomzahl && randomzahl < 0.75)
			monsters.add(MonsterFactory.createMonster("NormalMonster",
					(int) (Math.random() * 80),
					80 + (int) (Math.random() * 580)));
		if (0.75 <= randomzahl && randomzahl < 1)
			monsters.add(MonsterFactory.createMonster("NormalMonster",
					1200 + (int) (Math.random() * 80),
					80 + (int) (Math.random() * 580)));
	}

	@Override
	public void create() {
		if(i<monster_num){
			createMonster(i++);
		}
	}

}
