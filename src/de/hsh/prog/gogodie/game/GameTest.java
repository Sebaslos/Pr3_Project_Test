package de.hsh.prog.gogodie.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import de.hsh.prog.gogodie.game.play.PlayState;

@SuppressWarnings("serial")
public class GameTest extends JFrame {

	//public static final GameTest instance = new GameTest();
	private PlayState ps;
	
	public GameTest() {
		super("gogoDie");
		ps = new PlayState();
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		switchState(ps);
		setVisible(true);
		ps.startGame();
	}
	
	/*public static GameTest getInstance(){
		return instance;
	}*/
	
	public void switchState(GameState state){
		this.getContentPane().removeAll();
		this.add(state, BorderLayout.CENTER);
		this.pack();
	}
	
}
