package de.hsh.prog.gogodie.game.map;

import java.awt.Rectangle;

public class Tile {

	private Rectangle bound;
	private boolean solid;
	
	public Tile(boolean solid) {
		this.bound = new Rectangle(Map.TILE_SIZE,Map.TILE_SIZE);
		this.solid = solid;
	}
	
	public void setPosition(int x,int y){
		this.bound.x = x * Map.TILE_SIZE;
		this.bound.y = y * Map.TILE_SIZE;
	}
	
}
