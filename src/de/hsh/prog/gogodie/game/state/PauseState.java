package de.hsh.prog.gogodie.game.state;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.actor.ActorList;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.Highscore;
import de.hsh.prog.gogodie.game.utils.JukeBox;
import de.hsh.prog.gogodie.game.utils.Keys;
import de.hsh.prog.gogodie.game.utils.MouseHandler;

public class PauseState extends GameState {
	
	private boolean[] selected = {
			false,
			false
	};
	
	private String[] options = {
			"CONTINUE",
			"EXIT"
	};

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
		g.setColor(new Color(164, 198, 222));
		g.fillRoundRect(400, 150, 480, 350, 30, 30);
		
		Content.drawString(g, "current score", 432, 182, 4, false);
		Content.drawString(g, Highscore.getScore()+"", 560, 246, 4, false);
		
		Content.drawString(g, options[0], 512, 360, 4, selected[0]);
		Content.drawString(g, options[1], 576, 424, 4, selected[1]);
	}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false);
			JukeBox.resumeLoop();
		}
		
		int mx = MouseHandler.getMouse_X();
		int my = MouseHandler.getMouse_Y();
		
		if(mx <= 768 && mx >= 512 && my >= 360 && my <= 392) {
			selected[0] = true;
		}else {
			selected[0] = false;
		}
		
		if(mx <= 704 && mx >= 576 && my >= 424 && my <= 456) {
			selected[1] = true;
		}else {
			selected[1] = false;
		}
		
		if(MouseHandler.Clicked == true) {
			selectOption();
			MouseHandler.Clicked = false;
		}
	}
	
	private void selectOption() {
		if(selected[0]) {
			gsm.setPaused(false);
			JukeBox.resumeLoop();
		}
		
		if(selected[1]) {
			gsm.setPaused(false);
			ActorList.removeAll();
			JukeBox.loop("menubgm", 1000, 1000, JukeBox.getFrames("menubgm") - 1000);
			gsm.setState(GameStateManager.MENU);
		}
	}

}
