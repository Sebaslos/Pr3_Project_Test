package de.hsh.prog.gogodie.game.play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.GameState;
import de.hsh.prog.gogodie.game.actor.Player;

public abstract class PlayBoard {

	private BufferedImage staticBuffer;
	private BufferedImage dynamicBuffer;
	
	private Player player;
	private int i = 0;
	
	public PlayBoard() {
		staticBuffer = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		dynamicBuffer = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}
	
	public Image getBuffer() {
		
		
		return staticBuffer;
	}

}
