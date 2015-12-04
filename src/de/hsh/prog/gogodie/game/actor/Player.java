package de.hsh.prog.gogodie.game.actor;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import de.hsh.prog.gogodie.game.monster.Monster;

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
	
	private ArrayList<Monster> monsters;
	private ArrayList<Rectangle> liste;
	
	public Player(Rectangle bound) {
		super(bound);
		sprite.addAnimation(PlayerAnimation.WALK_DOWN, 34);
        sprite.addAnimation(PlayerAnimation.WALK_UP, 37);
        sprite.addAnimation(PlayerAnimation.WALK_LEFT, 35);
        sprite.addAnimation(PlayerAnimation.WALK_RIGHT, 36);
        
        sprite.playAnimation(PlayerAnimation.WALK_DOWN, false);
	}
	
	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}
	
	public void setHindernis(ArrayList<Rectangle> liste) {
		this.liste = liste;
	}
	
	@Override
	public void update() {
		if (keyLeftPressed || keyRightPressed || keyDownPressed || keyUpPressed) {
            move(currentDirection);
        }
		super.update();
	}
	
	@Override
	protected void move(Direction d) {
		if(!resolveCollision(d)) {
			switch (d) {
	        case LEFT:
	            bound.x -= speed;
	            sprite.playAnimation(PlayerAnimation.WALK_LEFT, true);
	            break;
	        case RIGHT:
	            bound.x += speed;
	            sprite.playAnimation(PlayerAnimation.WALK_RIGHT, true);
	            break;
	        case UP:
	            bound.y -= speed;
	            sprite.playAnimation(PlayerAnimation.WALK_UP, true);
	            break;
	        case DOWN:
	            bound.y += speed;
	            sprite.playAnimation(PlayerAnimation.WALK_DOWN, true);
	            break;
			}
		}
		
	}
	
	private boolean resolveCollision(Direction d) {
		Rectangle r;
		switch (d) {
		case LEFT:
			r = new Rectangle(bound.x-speed, bound.y, bound.width, bound.height);
			break;
		case RIGHT:
			r = new Rectangle(bound.x+speed, bound.y, bound.width, bound.height);
			break;
		case UP:
			r = new Rectangle(bound.x, bound.y-speed, bound.width, bound.height);
			break;
		case DOWN:
			r = new Rectangle(bound.x, bound.y+speed, bound.width, bound.height);
			break;
		default:
			r = new Rectangle(bound.x, bound.y, bound.width, bound.height);
			break;
		}
			
		/*for(Monster monster : monsters) {
			if(r.intersects(monster.getBounds())) {
				return true;
			}
		}*/
		
		for(Rectangle hin : liste) {
			if(r.intersects(hin.getBounds())) {
				return true;
			}
		}
		
		return false;
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
