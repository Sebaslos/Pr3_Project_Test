package de.hsh.prog.gogodie.game.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Content {
	
	public static BufferedImage[][] player = load("/res/player.png", 32, 31);
	
	public static BufferedImage[][] AIM = load("/res/aim.png", 32, 32);
	
	public static BufferedImage[][] MENUBG = load("/res/testbild1.jpg", 1280, 720);
	
	public static BufferedImage[][] font = load("/res/font.gif", 8, 8);

	public static BufferedImage[][] load(String path, int w, int h) {
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(path));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}
	
	public static void drawString(Graphics2D g, String s, int x, int y, int scale, boolean selected) {
		s = s.toUpperCase();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == 47) c = 36; // slash
			if(c == 58) c = 37; // colon
			if(c == 32) c = 38; // space
			if(c >= 65 && c <= 90) c -= 65; // letters
			if(c >= 48 && c <= 57) c -= 22; // numbers
			int row = c / font[0].length;
			int col = c % font[0].length;
			if(selected)
				g.drawImage(font[row][col], x - 5 + 8 * i * scale, y - 5, 8 * scale, 8 * scale, null);
			else
				g.drawImage(font[row][col], x + 8 * i * scale, y, 8 * scale, 8 * scale, null);
		}
	}
	
	public static BufferedImage rotateImage(BufferedImage bfimage,double radio){
		
		int w = bfimage.getWidth();
		int h = bfimage.getHeight();
		int type = bfimage.getColorModel().getTransparency();
		BufferedImage img = new BufferedImage(w,h,type);
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		g2d.rotate(radio, w/2, h/2);
		g2d.drawImage(bfimage, 0, 0, null);
		g2d.dispose();
		return img;
	}
	
}
