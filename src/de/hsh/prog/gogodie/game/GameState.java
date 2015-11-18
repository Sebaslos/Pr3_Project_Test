package de.hsh.prog.gogodie.game;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class GameState extends JPanel{

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public GameState() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
	}
	
}
