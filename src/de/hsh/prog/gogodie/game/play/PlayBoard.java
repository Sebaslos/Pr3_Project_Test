package de.hsh.prog.gogodie.game.play;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.GameState;
import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.map.Map;

public abstract class PlayBoard {

	private BufferedImage staticBuffer;
	private BufferedImage dynamicBuffer;
	
	protected Map map;
	
	private Player player;
	
	public PlayBoard(Player player) {
		this.player = player;
		staticBuffer = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		dynamicBuffer = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void init(){
		staticBuffer = map.getMap();
	}
	
	public Image getBuffer() {
		Graphics g = dynamicBuffer.getGraphics();
		g.drawImage(staticBuffer, 0, 0, null);
		g.drawImage(player.getFrame(), player.getX(), player.getY(), null);
		g.dispose();
		return dynamicBuffer;
	}
	
	public void update() {
        player.update();
        
    }

}
