package de.hsh.prog.gogodie.game.state;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.GamePanel;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.MouseHandler;

public class HelpState extends GameState {

	private String option = "BACK";
	private boolean selected = false;
	
	public HelpState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(164, 198, 222));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		Content.drawString(g, "HELP", 512, 40, 8, false);
		
		Content.drawString(g, option, 40, 640, 5, selected);
	}

	@Override
	public void handleInput() {
		int mx = MouseHandler.getMouse_X();
		int my = MouseHandler.getMouse_Y();
		if(mx <= 200 && mx >= 40 && my >= 640 && my <= 680) {
			selected = true;
		}else {
			selected = false;
		}
		
		if(MouseHandler.Clicked == true) {
			selectOption();
		}
	}

	private void selectOption() {
		if(selected) {
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}
