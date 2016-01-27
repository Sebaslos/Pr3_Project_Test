package de.hsh.prog.gogodie.game.state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.MouseHandler;

public class CreditsState extends GameState {

	private String option = "BACK";
	private boolean selected = false;
	
	private BufferedImage background;
	
	private String[] nameList = {
		"Adrian Krehl",
		"Fares Sebei",
		"Shanghui Dai",
		"Liaoming Wang",
		"Lars Eike",
		"Dennis Glowacki"
	};
	
	public CreditsState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		background = Content.MENUBG[0][0];
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		//g.setColor(new Color(164, 198, 222));
		//g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		Content.drawString(g, "CREDITS", 412, 40, 8, false);
		
		for(int i=0;i < nameList.length;i++) {
			String name = nameList[i];
			Content.drawString(g, name, rechnenX(name.length(), 5), 150 + i * 75, 5, false);
		}
		
		Content.drawString(g, option, 40, 640, 5, selected);
	}
	
	private int rechnenX(int length, int scale) {
		return (int)((1280 - length * scale * 8) / 2);
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
