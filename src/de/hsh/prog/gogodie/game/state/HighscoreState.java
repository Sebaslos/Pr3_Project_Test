package de.hsh.prog.gogodie.game.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import de.hsh.prog.gogodie.game.GamePanel;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.Highscore;
import de.hsh.prog.gogodie.game.utils.MouseHandler;
import de.hsh.prog.gogodie.game.utils.XmlUtils;

public class HighscoreState extends GameState {

	private String option = "BACK";
	private boolean selected = false;
	private ArrayList<String> scoreList;
	
	public HighscoreState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		scoreList = XmlUtils.getScoreList();
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(164, 198, 222));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		Content.drawString(g, "HIGHSCORE", 350, 40, 8, false);
		
		drawHighscoreList(g);
		if(Highscore.show_current_score == true) {
			Content.drawString(g, "current: "+Highscore.getScore(), 260, 500, 5, false);
		}
		
		
		Content.drawString(g, option, 40, 640, 5, selected);
	}
	
	private void drawHighscoreList(Graphics2D g) {
		for(int i=0;i < scoreList.size();i++) {
			Content.drawString(g, i+1 + ": "+scoreList.get(i), 500, 150 + i * 50, 5, false);
		}
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
			Highscore.show_current_score = false;
			gsm.setState(GameStateManager.MENU);
		}
	}

}
