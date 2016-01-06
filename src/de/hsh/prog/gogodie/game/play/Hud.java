package de.hsh.prog.gogodie.game.play;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.utils.Content;

public class Hud {

	private Player player;
	private int munition;
	private int hp;
	
	public Hud(Player player) {
		this.player = player;
		munition = this.player.getWaffe().getMunitionNum();
		hp = this.player.getMaxHp();
	}
	
	public void draw(Graphics2D g) {
		Content.drawString(g, "HP", 16, 16, 3, false);
		
		//draw Hp bar
		g.setColor(Color.black);
		g.fillRect(72, 16, 126, 26);
		g.setColor(Color.gray);
		g.fillRect(75, 19, 120, 20);
		g.setColor(Color.red);
		g.fillRect(75, 19, (int)(120 * player.getHP() / hp), 20);
		
		String s = player.getHP() + "/" + hp;
		Content.drawString(g, s, 206, 16, 3, false);
		
		//draw waffe
		g.drawImage(player.getWaffe().getStaticFrame(), 16, 656, 16 * 3, 16 * 3, null);
		//draw munition
		munition = this.player.getWaffe().getMunitionNum();
		s = player.getWaffe().getRestMunition() + "/" + munition;
		Content.drawString(g, s, 80, 668, 3, false);
		
		
	}
	
}
