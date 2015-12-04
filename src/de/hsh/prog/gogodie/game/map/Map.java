package de.hsh.prog.gogodie.game.map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import de.hsh.prog.gogodie.game.actor.Hindernis;

public class Map {

	public static final int TILE_SIZE= 16;
	private BufferedImage map;
	private Hindernis hindernis;
	private ArrayList<Rectangle>  liste = new ArrayList<Rectangle>();
	
	public Map(String path) {
		try {
			map = ImageIO.read(this.getClass().getResourceAsStream(path));
			hindernisse();
			hindernis = new Hindernis(new Rectangle(16,16,16,16),liste);
			hindernis.malen(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Rectangle> getHindernis() {
		return liste;
	}
	
	public BufferedImage getMap(){
		return map;
	}
	
	private void hindernisse(){
		Random rand = new Random();
		for (int i=0; i<93; i++) {
			int y =rand.nextInt(720);
			int x= rand.nextInt(1280);
			liste.add(i, new Rectangle(x,y,16,16));
		}				
	}
}
