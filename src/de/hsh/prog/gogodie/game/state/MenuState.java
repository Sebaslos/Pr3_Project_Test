package de.hsh.prog.gogodie.game.state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.Data;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.Highscore;
import de.hsh.prog.gogodie.game.utils.JukeBox;
import de.hsh.prog.gogodie.game.utils.MouseHandler;

public class MenuState extends GameState implements Runnable{

	private BufferedImage background;
	
	private static boolean playBgm = true;
	
	private boolean[] selected = {
			false,
			false,
			false,
			false,
			false
	};
	private String[] options = {
		"START",
		"HIGHSCORE",
		"HELP",
		"CREDITS",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		background = Content.MENUBG[0][0];
		JukeBox.load("/sound/menubgm.mp3", "menubgm");
		if(playBgm == true) {
			JukeBox.loop("menubgm", 1000, 1000, JukeBox.getFrames("menubgm") - 1000);
			playBgm = false;
		}
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		Content.drawString(g, "GOGODIE", 360, 80, 10, false);
		
		Content.drawString(g, options[0], 540, 430, 5, selected[0]);
		Content.drawString(g, options[1], 460, 480, 5, selected[1]);
		Content.drawString(g, options[2], 560, 530, 5, selected[2]);
		Content.drawString(g, options[3], 500, 580, 5, selected[3]);
		Content.drawString(g, options[4], 560, 630, 5, selected[4]);
	}

	@Override
	public void handleInput() {
		int mx = MouseHandler.getMouse_X();
		int my = MouseHandler.getMouse_Y();
		if(mx <= 740 && mx >= 540 && my >= 430 && my <= 470) {
			selected[0] = true;
		}else {
			selected[0] = false;
		}
		
		if(mx <= 820 && mx >= 460 && my >= 480 && my <= 520) {
			selected[1] = true;
		}else {
			selected[1] = false;
		}
		
		if(mx <= 720 && mx >= 560 && my >= 530 && my <= 570) {
			selected[2] = true;
		}else {
			selected[2] = false;
		}
		
		if(mx <= 660 && mx >= 500 && my >= 580 && my <= 620) {
			selected[3] = true;
		}else {
			selected[3] = false;
		}
		
		if(mx <= 720 && mx >= 560 && my >= 630 && my <= 670) {
			selected[4] = true;
		}else {
			selected[4] = false;
		}
		
		if(MouseHandler.Clicked == true) {
			selectOption();
		}
	}
	
	private void selectOption() {
		if(selected[0]) {
			Data.clear();
			Highscore.clear();
			JukeBox.stop("menubgm");
			String name = JOptionPane.showInputDialog("enter a name");
			Highscore.setName(name);
			gsm.setState(GameStateManager.PLAY_LEVEL1);
		}
		
		if(selected[1]) {
			gsm.setState(GameStateManager.HIGHSCORE);
		}
		
		if(selected[2]) {
			gsm.setState(GameStateManager.HELP);
		}
		
		if(selected[3]) {
			gsm.setState(GameStateManager.CREDITS);
		}
		
		if(selected[4]) {
			System.exit(0);
		}
	}

	@Override
	public void run() {
		init();
		gsm.setLoading(false);
	}
}
