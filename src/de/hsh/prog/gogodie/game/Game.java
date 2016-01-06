package de.hsh.prog.gogodie.game;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	//private static int x;
	//private static int y;
	
	public Game() {
		super("gogoDie");
		
		add(new GamePanel());
		
		/*x = (getToolkit().getScreenSize().width - 1280) / 2;
        y = (getToolkit().getScreenSize().height - 720) / 2;
        setLocation(x, y);*/
        
        setResizable(false);
        pack();
        
        setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
}
