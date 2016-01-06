package de.hsh.prog.gogodie.game;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.Keys;
import de.hsh.prog.gogodie.game.utils.MouseHandler;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int FPS = 60;
	private final int TARGET_TIME = 1000 / FPS;
	
	// drawing stuff
	private BufferedImage image;
	private Graphics2D g;

	// game state manager
	private GameStateManager gsm;
	
	//mouse adapter
	private MouseAdapter ma;
	
	// constructor
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		setAimCursor();
		ma = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				MouseHandler.Clicked = true;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				MouseHandler.pressed = true;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				MouseHandler.setMousePositon(e.getPoint());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				MouseHandler.pressed = false;
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				MouseHandler.Clicked = false;
				MouseHandler.setMousePositon(e.getPoint());
			}
			
		};
	}
	
	// ready to display
	@Override
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			addKeyListener(this);
			addMouseListener(ma);
			addMouseMotionListener(ma);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	// initializes fields
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
	}
	
	@Override
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			render();
			
			elapsed = System.nanoTime() - start;
			
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// updates game
	private void update() {
		gsm.update();
	}
		
	// draws game
	private void draw() {
		gsm.draw(g);
	}
		
	// copy buffer to screen
	private void render() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
	public void setAimCursor() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Cursor cursor = tk.createCustomCursor(Content.AIM[0][0], new Point(16, 16), "aim");
		this.setCursor(cursor);
	}
	
	// key event
	@Override
	public void keyTyped(KeyEvent key) {}
	@Override
	public void keyPressed(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), true);
	}
	@Override
	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);
	}
}
