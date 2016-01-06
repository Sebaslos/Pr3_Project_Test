package de.hsh.prog.gogodie.game.waffe;

import java.util.ArrayList;

import de.hsh.prog.gogodie.game.actor.ActorList;
import de.hsh.prog.gogodie.game.actor.Direction;
import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.powerup.PowerUp;
import de.hsh.prog.gogodie.game.utils.JukeBox;

public abstract class Waffe extends PowerUp{
	
	private int muzzle_x;
	private int muzzle_y;
	
	protected int munition_type;
	protected int munitionNum;
	protected int restMunition;
	private ArrayList<Munition> munitions;
	
	private boolean endlessMunition = false;
	
	private int shootTick = 0;
	protected int cooldownTime = 30;
	private boolean inCooldown = false;
	
	private int reloadTick = 0;
	protected int reloadTime = 60;
	private boolean inReload = false;
	
	protected String sfx_shoot;
	protected String sfx_reload;
	 
	public Waffe(int x, int y, int width, int height) {
		super(x, y, width, height);
		munitions = new ArrayList<Munition>();
	}

	public int getRestMunition() {
		return restMunition;
	}
	
	public void setRestMunition(int restMunition) {
		this.restMunition = restMunition;
	}
	
	public int getMunitionNum() {
		return munitionNum;
	}
	
	public void setEndlessMunition(boolean b) {
		endlessMunition = b;
	}
	
	private void cooldown() {
		shootTick++;
		if(shootTick > cooldownTime) {
			shootTick = 0;
			inCooldown = false;
		}
	}
	
	public boolean isInReload() {
		return inReload;
	}
	
	public void setInReload() {
		JukeBox.play(sfx_reload);
		inReload = true;
	}
	
	private void reload() {
		reloadTick++;
		if(reloadTick > reloadTime) {
			reloadTick = 0;
			restMunition = munitionNum;
			inReload = false;
		}
	}
	
	public ArrayList<Munition> getMunitions() {
		return munitions;
	}
	
	public void setMuzzlePositon(int x, int y, Direction d) {
		muzzle_x = x;
		muzzle_y = y;
		
		switch (d) {
        case LEFT:
        	muzzle_x = x - 16;
            break;
        case RIGHT:
        	muzzle_x = x + 16;
            break;
        case UP:
        	muzzle_y = y - 16;
            break;
        case DOWN:
        	muzzle_y = y + 16;
            break;
		}
	}
	
	public void setMuzzlePositon(int x, int y, double radio) {
		muzzle_x = x;
		muzzle_y = y;
		
		muzzle_x += 8 * Math.cos(radio);
		muzzle_y += 8 * Math.sin(radio);
	}
	
	public void shoot(double radio) {
		if(!inCooldown && !inReload) {
			JukeBox.play(sfx_shoot);
			Munition m = new Munition(muzzle_x, muzzle_y, munition_type, radio);
			munitions.add(m);
			ActorList.add(m);
			
			if(!endlessMunition)
				restMunition--;
			if(restMunition == 0)
				setInReload();
			
			inCooldown = true;
		}
	}
	
	public void shoot(Direction d) {
		if(!inCooldown && !inReload) {
			Munition m = new Munition(muzzle_x, muzzle_y, d);
			munitions.add(m);
			ActorList.add(m);
			
			if(!endlessMunition)
				restMunition--;
			if(restMunition == 0)
				setInReload();
			
			inCooldown = true;
		}
	}
	
	public void update() {
		if(inCooldown)
			cooldown();
		
		if(inReload)
			reload();
		
		ArrayList<Munition> ms = new ArrayList<Munition>();
		for(Munition m : munitions) {
			if(m.hasHit()==true) {
				ms.add(m);
			}else 
				m.update();
		}
		munitions.removeAll(ms);
	}

	@Override
	public void effect(Player player) {
		player.collectedWaffe(this);
	}

}
