package de.hsh.prog.gogodie;

import java.awt.EventQueue;

import de.hsh.prog.gogodie.game.Game;

public class GameLauncher {
	
	public static void main(String[]args){
		EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
            	new Game();
            }
        });
	}
}
