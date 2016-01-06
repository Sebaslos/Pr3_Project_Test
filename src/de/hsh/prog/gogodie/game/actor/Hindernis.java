package de.hsh.prog.gogodie.game.actor;

public class Hindernis extends Actor {

	public Hindernis(int x, int y, int width, int height, int index) {
		super(x, y, width, height);
		this.setImage(index);
	}

}
