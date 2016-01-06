package de.hsh.prog.gogodie.game.utils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keys {
	
	public static final int NUM_KEYS = 8;
	
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static ArrayList<Integer>dirList = new ArrayList<Integer>(2);
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int R = 4;
	public static int SPACE = 5;
	public static int ESCAPE = 6;
	public static int ENTER = 7;

	public static void keySet(int i, boolean b) {
		if(i == KeyEvent.VK_W) keyState[UP] = b;
		else if(i == KeyEvent.VK_A) keyState[LEFT] = b;
		else if(i == KeyEvent.VK_S) keyState[DOWN] = b;
		else if(i == KeyEvent.VK_D) keyState[RIGHT] = b;
		else if(i == KeyEvent.VK_R) keyState[R] = b;
		else if(i == KeyEvent.VK_SPACE) keyState[SPACE] = b;
		else if(i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
		else if(i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
		
		if(i==KeyEvent.VK_W || i==KeyEvent.VK_A || i==KeyEvent.VK_S || i==KeyEvent.VK_D) {
			resolveDirection(i, b);
		}
	}
	
	public static void resolveDirection(int i, boolean b){
		if(b == true) {
			if(!dirList.contains(i)) {
				if(dirList.size() == 2)
					dirList.remove(0);
				dirList.add(i);
			}
		}
		
		if(b == false) {
			if(dirList.contains(i)) {
				dirList.remove((Integer)i);
			}
		}
		
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean isDown(int i) {
		return keyState[i];
	}
	
	public static boolean anyKeyDown() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i] && !prevKeyState[i]) return true;
		}
		return false;
	}
	
	public static boolean anyDirectionKeyDown() {
		for(int i = 0; i < NUM_KEYS - 4; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}
}
