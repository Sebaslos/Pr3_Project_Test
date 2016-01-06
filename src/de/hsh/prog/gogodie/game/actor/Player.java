package de.hsh.prog.gogodie.game.actor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.hsh.prog.gogodie.game.powerup.Buff;
import de.hsh.prog.gogodie.game.powerup.PowerUp;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.Keys;
import de.hsh.prog.gogodie.game.utils.MouseHandler;
import de.hsh.prog.gogodie.game.waffe.Machinegun;
import de.hsh.prog.gogodie.game.waffe.Pistol;
import de.hsh.prog.gogodie.game.waffe.Shotgun;
import de.hsh.prog.gogodie.game.waffe.Waffe;

public class Player extends Mob{
	
	private int maxHp;
	
	private BufferedImage originImage;
	private double radio;
	
	private double ox;
	private double oy;
	
	private Waffe waffe;
	private ArrayList<Buff> buffs;
	
	private boolean hasShield;
	
	private Direction currentDirection = Direction.DOWN;
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		ActorList.add(this);
		
		maxHp = 20;
		setHP(maxHp);
		hasShield = false;
		
		collectedWaffe(new Pistol(x, y, 16, 16));
		buffs = new ArrayList<Buff>();
		
		setImage(Content.player[0][0]);
		
		originImage = Content.player[0][0];
		
		/*sprite.addAnimation(PlayerAnimation.WALK_DOWN, 34);
        sprite.addAnimation(PlayerAnimation.WALK_UP, 37);
        sprite.addAnimation(PlayerAnimation.WALK_LEFT, 35);
        sprite.addAnimation(PlayerAnimation.WALK_RIGHT, 36);
        
        sprite.playAnimation(PlayerAnimation.WALK_DOWN, false);*/
	}

	@Override
	public void update() {
		rotatePlayer();
		
		if (Keys.anyDirectionKeyDown()) {
			//setDirectionAnimation(currentDirection);
			if(hasCollision(currentDirection)==null)
				move(currentDirection);
		}
		
		waffe.update();
		for(int i=0;i < buffs.size();i++) {
			Buff b = buffs.get(i);
			if(b.isInvalid()) {
				buffs.remove(i);
				i--;
				b.uneffect(this);
			}else 
				b.update();
		}
		//super.update();
	}
	
	public boolean hasShield() {
		return hasShield;
	}
	
	public void setHasShield(boolean b) {
		hasShield = b;
	}
	
	public void reloadWaffe() {
		waffe.setInReload();
	}
	
	public void collectedWaffe(Waffe waffe) {
		this.waffe = waffe;
		if(this.waffe instanceof Pistol){
			waffe.setImage(29);
		}else if(this.waffe instanceof Shotgun){
			waffe.setImage(28);
		}else if(this.waffe instanceof Machinegun){
			waffe.setImage(27);
		}
	}
	
	public void collect(PowerUp powerup) {
		powerup.effect(this);
		if(powerup instanceof Buff) {
			powerup.setTick(0);
			buffs.add((Buff) powerup);
		}
	}
	
	public void shoot() {
		waffe.setMuzzlePositon((int)ox, (int)oy, radio);
		waffe.shoot(radio);
	}

	public void setDirection(Direction d) {
		this.currentDirection = d;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	public Waffe getWaffe() {
		return waffe;
	}
	
	/*private void setDirectionAnimation(Direction d) {
		switch (d) {
        case LEFT:
            sprite.playAnimation(PlayerAnimation.WALK_LEFT, true);
            break;
        case RIGHT:
            sprite.playAnimation(PlayerAnimation.WALK_RIGHT, true);
            break;
        case UP:
            sprite.playAnimation(PlayerAnimation.WALK_UP, true);
            break;
        case DOWN:
            sprite.playAnimation(PlayerAnimation.WALK_DOWN, true);
            break;
		}
	}*/
	
	@Override
	protected void move(Direction d) {
		switch (d) {
        case LEFT:
            x -= speed;
            break;
        case RIGHT:
            x += speed;
            break;
        case UP:
            y -= speed;
            break;
        case DOWN:
            y += speed;
            break;
		}
	}
	
	private void rotatePlayer(){
		radio = getRadio();
		BufferedImage timg = Content.rotateImage(originImage, radio);
		this.setImage(timg);
	}
	
	private double getRadio() {
		double mx = MouseHandler.getMouse_X();
		double my = MouseHandler.getMouse_Y();
		ox = this.getX() + this.getWidth() / 2;
		oy = this.getY() + this.getHeight() / 2;
		
		double radio = 0;
		if(mx>ox)
			radio = Math.atan((my-oy)/(mx-ox));
		else if(mx<ox)
			radio = -Math.PI + Math.atan((my-oy)/(mx-ox));
		else if(mx==ox&&my<oy)
			radio = -Math.PI / 2;
		else if(mx==ox&&my>oy)
			radio = Math.PI / 2;
		
		return radio;
	}
	
}
