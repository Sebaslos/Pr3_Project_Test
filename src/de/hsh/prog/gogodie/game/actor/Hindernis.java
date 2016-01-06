package de.hsh.prog.gogodie.game.actor;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Hindernis extends Actor {
	private ArrayList<Rectangle>  liste;

	public Hindernis(Rectangle bound,ArrayList<Rectangle> liste) {
		super(bound);
		sprite.addAnimation(HindernisAnimation.STEIN , 1);
		this.liste = liste;
	}

	
	public void malen(BufferedImage map){
		Graphics g = map.getGraphics();
		
		for (int i=0; i<93; i++) {

			g.drawImage(sprite.getFrame(1),(int)liste.get(i).getX(),(int) liste.get(i).getY(), null);
		}
		g.dispose();
	}
}
