package de.hsh.prog.gogodie.game.powerup;

import de.hsh.prog.gogodie.game.actor.Player;

public class HpRecovery extends PowerUp {

	private int recoveryValue;
	
	public HpRecovery(int x, int y, int width, int height) {
		super(x, y, width, height);
		recoveryValue = 10;
		
		setImage(7);
	}
	
	@Override
	public void effect(Player player) {
		if(player.getHP() + recoveryValue > player.getMaxHp())
			player.setHP(player.getMaxHp());
		else 
			player.setHP(player.getHP() + recoveryValue);
	}

}
