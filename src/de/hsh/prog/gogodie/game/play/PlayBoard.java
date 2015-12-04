package de.hsh.prog.gogodie.game.play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.hsh.prog.gogodie.game.GameState;
import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.map.Map;
import de.hsh.prog.gogodie.game.monster.Monster;
import de.hsh.prog.gogodie.game.monster.MonsterFactory;

public abstract class PlayBoard {

	private BufferedImage staticBuffer;
	private BufferedImage dynamicBuffer;
	
	protected Map map;
	
	private Player player;
	protected ArrayList<Monster>monsters;
	
	public PlayBoard(Player player) {
		this.player = player;
		monsters = new ArrayList<Monster>();
		player.setMonsters(monsters);
		//this.monster = new NormalMonster(new Rectangle(160, 160, 16, 16));
		MonsterFactory.setPlayerPosition(player.getX(), player.getY());
		staticBuffer = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		dynamicBuffer = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void init(){
		//Graphics g = staticBuffer.getGraphics();
		//g.setColor(Color.pink);
		//g.fillRect(0, 0, GameState.WIDTH, GameState.HEIGHT);
		staticBuffer = map.getMap();
		player.setHindernis(map.getHindernis());
		//g.dispose();
	}
	
	public Image getBuffer() {
		Graphics g = dynamicBuffer.getGraphics();
		g.drawImage(staticBuffer, 0, 0, null);
		g.drawImage(player.getFrame(), player.getX(), player.getY(), null);
		for(Monster monster : monsters){
			g.drawImage(monster.getFrame(), monster.getX(), monster.getY(), null);
		}
		g.dispose();
		return dynamicBuffer;
	}
	
	public void update() {
        player.update();
        MonsterFactory.setPlayerPosition(player.getX(), player.getY());
        for(Monster monster : monsters){
        	monster.update();
        }
    }
	
	protected abstract void createMonster(int i);
	
	public abstract void create();

}
