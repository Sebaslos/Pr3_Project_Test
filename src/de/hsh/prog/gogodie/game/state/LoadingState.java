package de.hsh.prog.gogodie.game.state;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.GamePanel;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.GameStateManager;

public class LoadingState extends GameState {

	public LoadingState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {

	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		Content.drawString(g, "loading", 960, 640, 5, false);
	}

	@Override
	public void handleInput() {

	}

}
