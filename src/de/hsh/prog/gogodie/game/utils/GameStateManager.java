package de.hsh.prog.gogodie.game.utils;

import java.awt.Graphics2D;

import de.hsh.prog.gogodie.game.play.Level1;
import de.hsh.prog.gogodie.game.play.Level2;
import de.hsh.prog.gogodie.game.play.PlayState;
import de.hsh.prog.gogodie.game.state.*;

public class GameStateManager {

	private boolean paused;
	private PauseState pauseState;
	
	private boolean loading;
	private LoadingState loadingState;
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 7;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY_LEVEL1 = 2;
	public static final int PLAY_LEVEL2 = 3;
	public static final int HELP = 4;
	public static final int HIGHSCORE = 5;
	public static final int CREDITS = 6;
	
	private Thread thread;
	
	public GameStateManager() {
		JukeBox.init();
		
		paused = false;
		pauseState = new PauseState(this);
		
		loading = false;
		loadingState = new LoadingState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}

	public void setLoading(boolean b) {
		loading = b;
	}
	
	public boolean isLoading() {
		return loading;
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU) {
			MenuState menu = new MenuState(this);
			thread = new Thread(menu);
			gameStates[i] = menu;
			
			setLoading(true);
			thread.start();
		}
		else if(i == PLAY_LEVEL1) {
			PlayState level1 = new Level1(this);
			thread = new Thread(level1);
			gameStates[i] = level1;
			
			setLoading(true);
			thread.start();
		}
		else if(i == PLAY_LEVEL2) {
			PlayState level2 = new Level2(this);
			thread = new Thread(level2);
			gameStates[i] = level2;
			
			setLoading(true);
			thread.start();
		}
		else if(i == HELP) {
			gameStates[i] = new HelpState(this);
			gameStates[i].init();
		}else if(i == HIGHSCORE) {
			gameStates[i] = new HighscoreState(this);
			gameStates[i].init();
		}else if(i == CREDITS) {
			gameStates[i] = new CreditsState(this);
			gameStates[i].init();
		}
		
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public void update() {
		if(loading && currentState != INTRO) {
			loadingState.update();
		}else {
			if(paused) {
				pauseState.update();
			}
			else if(gameStates[currentState] != null) {
				gameStates[currentState].update();
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		if(loading && currentState != INTRO) {
			loadingState.draw(g);
		}else {
			if(paused) {
				pauseState.draw(g);
			}
			else if(gameStates[currentState] != null) {
				gameStates[currentState].draw(g);
			}
		}
	}
	
}
