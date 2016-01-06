package de.hsh.prog.gogodie.game.utils;

import java.awt.Point;

public class MouseHandler {

	public static boolean Clicked = false;
	public static boolean pressed = false;
	
	public static Point currentMousePosition = new Point();
	
	public static void setMousePositon(Point p) {
		currentMousePosition = p;
	}
	
	public static int getMouse_X() {
		return currentMousePosition.x;
	}
	
	public static int getMouse_Y() {
		return currentMousePosition.y;
	}
	
}
