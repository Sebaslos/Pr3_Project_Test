package de.hsh.prog.gogodie.game.play;

import de.hsh.prog.gogodie.game.actor.ActorList;
import de.hsh.prog.gogodie.game.map.Map;
import de.hsh.prog.gogodie.game.monster.Monster;
import de.hsh.prog.gogodie.game.monster.MonsterFactory;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.JukeBox;

public class Level1 extends PlayState {

	private int monster_num;
	private int i = 0;
	
	private boolean comeBoss = false;

	public Level1(GameStateManager gsm) {
		super(gsm);
	}
	
	@Override
	public void init() {
		super.init();
		monster_num = 20;
		map = new Map("/res/map_level1.jpg");
		
		bgm = "level1bgm";
		JukeBox.load("/sound/level1bgm.mp3", bgm);
		JukeBox.loop(bgm, 1000, 1000, JukeBox.getFrames(bgm) - 1000);
	}

	@Override
	protected int createMonster(String type) {
		double randomzahl = Math.random();
		Monster monster = null;
		if (randomzahl < 0.25)
			monster = MonsterFactory.createMonster(type,
					(int) (Math.random() * 1280), (int) (Math.random() * 80));
		if (0.25 <= randomzahl && randomzahl < 0.5)
			monster = MonsterFactory.createMonster(type,
					(int) (Math.random() * 1280),
					640 + (int) (Math.random() * 80));
		if (0.5 <= randomzahl && randomzahl < 0.75)
			monster = MonsterFactory.createMonster(type,
					(int) (Math.random() * 80),
					80 + (int) (Math.random() * 580));
		if (0.75 <= randomzahl && randomzahl < 1)
			monster = MonsterFactory.createMonster(type,
					1200 + (int) (Math.random() * 80),
					80 + (int) (Math.random() * 580));
		if(monster != null && !ActorList.isDualPosition(monster)) {
			monsters.add(monster);
			ActorList.add(monster);
			return monster.getType();
		}else
			return -1;
	}
	
	@Override
	public void execute() {
		if (i < monster_num) {
			if(createMonster("Monster") != -1){
				i++;
			}
		}
		
		if(i == monster_num && monsters.isEmpty()) {
			comeBoss = true;
		}
		
		if(comeBoss) {
			if(createMonster("Boss1") != -1) {
				i++;
				comeBoss = false;
			}
		}
		
		if(i == monster_num + 1 && monsters.isEmpty()){
			finished = true;
		}
			
	}

	@Override
	public void run() {
		init();
		gsm.setLoading(false);
	}

}
