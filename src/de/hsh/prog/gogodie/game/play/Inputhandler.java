package de.hsh.prog.gogodie.game.play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Inputhandler extends KeyAdapter {

	public class Key {
        private int numTimesPressed = 0;
        private boolean pressed = false;

        public int getNumTimesPressed() {
            return numTimesPressed;
        }

        public boolean isPressed() {
            return pressed;
        }

        public void toggle(boolean isPressed) {
            pressed = isPressed;
            if (isPressed) numTimesPressed++;
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
	
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
		System.out.println("pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	public void toggleKey(int keyCode, boolean isPressed) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            up.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            down.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right.toggle(isPressed);
        }
    }
	
	public void test(){
		if(up.isPressed()==true)
			System.out.println("go up");
		if(down.isPressed()==true)
			System.out.println("go down");
		if(left.isPressed()==true)
			System.out.println("go left");
		if(right.isPressed()==true)
			System.out.println("go right");
	}
}
