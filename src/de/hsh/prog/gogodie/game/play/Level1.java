package de.hsh.prog.gogodie.game.play;

import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.map.Map;

public class Level1 extends PlayBoard {
	
	public Level1(Player player) {
		super(player);
		map = new Map("/res/map1.png");
		init();
	}

}
