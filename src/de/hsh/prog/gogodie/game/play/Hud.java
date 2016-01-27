package de.hsh.prog.gogodie.game.play;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.Data;

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
		
		String s;
		if(player.getHP() <= 0)
			s = 0 + "/" + hp;
		else 
			s = player.getHP() + "/" + hp;
		Content.drawString(g, s, 206, 16, 3, false);
		
		//draw waffe
		g.drawImage(player.getWaffe().getStaticFrame(), 16, 656, 16 * 3, 16 * 3, null);
		//draw munition
		munition = this.player.getWaffe().getMunitionNum();
		s = player.getWaffe().getRestMunition() + "/" + munition;
		Content.drawString(g, s, 80, 668, 3, false);
		
		//draw time
		int minutes = (int) (Data.getTime() / 3600);
		int seconds = (int) ((Data.getTime() / 60) % 60);
		if(minutes < 10) {
			if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 1120, 16, 3, false);
			else Content.drawString(g, "0" + minutes + ":" + seconds, 1120, 16, 3, false);
		}
		else {
			if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 1120, 16, 3, false);
			else Content.drawString(g, minutes + ":" + seconds, 1120, 16, 3, false);
		}
		
	}
	
}
