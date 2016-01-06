package de.hsh.prog.gogodie.game.state;

import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.utils.GameStateManager;

public class PauseState extends GameState {

	public PauseState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {

	}

	@Override
	public void handleInput() {

	}

}
