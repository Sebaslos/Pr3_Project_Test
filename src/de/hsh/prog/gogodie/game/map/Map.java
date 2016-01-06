package de.hsh.prog.gogodie.game.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

import de.hsh.prog.gogodie.game.GamePanel;
import de.hsh.prog.gogodie.game.actor.ActorList;
import de.hsh.prog.gogodie.game.actor.Hindernis;

public class Map {
	
	public static final int TILE_SIZE= 16;
	private BufferedImage map;
	
	private ArrayList<Hindernis> hindernise = new ArrayList<Hindernis>();
	
	public Map(String path) {
		try {
			map = ImageIO.read(this.getClass().getResourceAsStream(path));
			createhindernisse();
			ActorList.addAll(hindernise);
			drawHindernise();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getMap(){
		return map;
	}
	
	private void createhindernisse(){
		Random rand = new Random();
		for (int i=0; i<30; i++) {
			int y =rand.nextInt(GamePanel.HEIGHT - 16);
			int x= rand.nextInt(GamePanel.WIDTH - 16);
			int index = 20 + rand.nextInt(3);
			Hindernis hin = new Hindernis(x, y, 16, 16, index);
			if(!ActorList.isDualPosition(hin))
				hindernise.add(hin);
			else i--;
		}
		for (int i=0; i<80;i++){
			Hindernis hin = new Hindernis(i*16,-16,16,16,2);
			hindernise.add(hin);
		}
		for (int i=0; i<80;i++){
			Hindernis hin = new Hindernis(i*16,722,16,16,2);	
			hindernise.add(hin);
		}		
		for (int i=0; i<45;i++){
			Hindernis hin = new Hindernis(-16,i*16,16,16,1);	
			hindernise.add(hin);
		}	
		for (int i=0; i<45;i++){
			Hindernis hin = new Hindernis(1282,i*16,16,16,1);
			hindernise.add(hin);
		}
		
	}
	
	private void drawHindernise(){
		Graphics g = map.getGraphics();
		for (Hindernis hin : hindernise) {
			g.drawImage(hin.getStaticFrame(), hin.getX(), hin.getY(), null);
		}
		g.dispose();
	}
}
