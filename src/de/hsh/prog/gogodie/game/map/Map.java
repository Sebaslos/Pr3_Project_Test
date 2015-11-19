package de.hsh.prog.gogodie.game.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import de.hsh.prog.gogodie.game.GameState;

public class Map {
	
	public static final int TILE_SIZE = 16;
	private BufferedImage map;
	private BufferedImage buf;
	int pixls_buf[];
	
	public Map(String path) {
		map = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		try {
			buf = ImageIO.read(this.getClass().getResourceAsStream(path));
			pixls_buf = buf.getRGB(0, 0, buf.getWidth(), buf.getHeight(), null, 0, buf.getWidth());
		} catch (Exception e) {
				e.printStackTrace();
		}
		drawMap(map, buf);
	}
	
	private void drawMap(BufferedImage map,BufferedImage buf){
		System.out.println(buf.getHeight()+","+buf.getWidth());
		System.out.println(pixls_buf.length);
		int xX = map.getWidth() / buf.getWidth();
		int xY = map.getHeight() / buf.getHeight();
		for(int y=0;y<buf.getHeight();y++){
			for(int x=0;x<buf.getWidth();x++){
				enlarge(map,x,y,xX,xY);
			}
		}
	}
	
	private void enlarge(BufferedImage map,int x,int y,int xX,int xY){
		for(int j=0;j<xY;j++){
			for(int i=0;i<xX;i++){
				map.setRGB(x*xX+i, y*xY+j, pixls_buf[x + y*buf.getWidth()]);
			}
		}
	}

	public BufferedImage getMap(){
		BufferedImage rmap = new BufferedImage(GameState.WIDTH, GameState.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = rmap.getGraphics();
		g.drawImage(map, 0, 0, null);
		return rmap;
	}
	
}
