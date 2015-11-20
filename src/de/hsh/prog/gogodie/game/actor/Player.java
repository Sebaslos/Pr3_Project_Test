package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.hsh.prog.gogodie.game.gfx.SpriteSheet;

public class Player extends Mob implements KeyListener{

    private int keyLeft = KeyEvent.VK_LEFT;
    private int keyRight = KeyEvent.VK_RIGHT;
    private int keyUp = KeyEvent.VK_UP;
    private int keyDown = KeyEvent.VK_DOWN;
    
    private boolean keyLeftPressed = false;
    private boolean keyRightPressed = false;
    private boolean keyUpPressed = false;
    private boolean keyDownPressed = false;
	
	private Direction currentDirection = Direction.DOWN;
	
	public Player(SpriteSheet sprite, Rectangle bound) {
		super(sprite, bound);
		sprite.addAnimation(PlayerAnimation.WALK_DOWN, 34);
        sprite.addAnimation(PlayerAnimation.WALK_UP, 37);
        sprite.addAnimation(PlayerAnimation.WALK_LEFT, 35);
        sprite.addAnimation(PlayerAnimation.WALK_RIGHT, 36);
	}
	

	@Override
	public void update() {
		if (keyLeftPressed || keyRightPressed || keyDownPressed || keyUpPressed) {
            move(currentDirection);
        }
		super.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();

        if (k == keyLeft) {
            keyLeftPressed = true;
            currentDirection = Direction.LEFT;
        }

        if (k == keyRight) {
            keyRightPressed = true;
            currentDirection = Direction.RIGHT;
        }

        if (k == keyUp) {
            keyUpPressed = true;
            currentDirection = Direction.UP;
        }

        if (k == keyDown) {
            keyDownPressed = true;
            currentDirection = Direction.DOWN;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();

        if (k == keyLeft) {
            keyLeftPressed = false;
            if (keyRightPressed) {
                currentDirection = Direction.RIGHT;
            }
            if (keyDownPressed) {
                currentDirection = Direction.DOWN;
            }
            if (keyUpPressed) {
                currentDirection = Direction.UP;
            }
        }

        if (k == keyRight) {
            keyRightPressed = false;
            if (keyLeftPressed) {
                currentDirection = Direction.LEFT;
            }
            if (keyDownPressed) {
                currentDirection = Direction.DOWN;
            }
            if (keyUpPressed) {
                currentDirection = Direction.UP;
            }
        }

        if (k == keyUp) {
            keyUpPressed = false;
            if (keyDownPressed) {
                currentDirection = Direction.DOWN;
            }
            if (keyLeftPressed) {
                currentDirection = Direction.LEFT;
            }
            if (keyRightPressed) {
                currentDirection = Direction.RIGHT;
            }
        }

        if (k == keyDown) {
            keyDownPressed = false;
            if (keyUpPressed) {
                currentDirection = Direction.UP;
            }
            if (keyLeftPressed) {
                currentDirection = Direction.LEFT;
            }
            if (keyRightPressed) {
                currentDirection = Direction.RIGHT;
            }
        }
	}

}
