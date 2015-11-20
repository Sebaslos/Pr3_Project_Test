package de.hsh.prog.gogodie.game.map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {

	public static final int TILE_SIZE= 16;
	private BufferedImage map;
	
	public Map(String path) {
		try {
			map = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getMap(){
		return map;
	}
}
