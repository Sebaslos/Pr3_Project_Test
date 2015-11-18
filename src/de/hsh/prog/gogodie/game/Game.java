package de.hsh.prog.gogodie.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import de.hsh.prog.gogodie.game.play.PlayState;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	private static Game THIS;
	
	public Game() {
		super("gogoDie");
		THIS = this;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		switchState(new PlayState());
	}

	public static void switchState(GameState state){
		THIS.getContentPane().removeAll();
		THIS.add(state,BorderLayout.CENTER);
		THIS.pack();
	}
	
}
