package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.gfx.SpriteSheet;

public abstract class Actor {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected SpriteSheet sprite;
	private BufferedImage image;
	
	public Actor(int x, int y, int width, int height) {
		this();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Actor() {
		this.sprite = new SpriteSheet("/res/sprite_sheet.png", 16, 16, 10);
	}
	
	public void setImage(int imageIndex) {
		image = sprite.getFrame(imageIndex);
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public BufferedImage getStaticFrame(){
		if(image!=null)
			return image;
		else return null;
	}
	
    public BufferedImage getFrame() {
        return sprite.getCurrentFrame();
    }
	
}
