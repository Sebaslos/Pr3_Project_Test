package de.hsh.prog.gogodie.game.play;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.hsh.prog.gogodie.game.actor.ActorList;
import de.hsh.prog.gogodie.game.actor.Direction;
import de.hsh.prog.gogodie.game.actor.Player;
import de.hsh.prog.gogodie.game.map.Map;
import de.hsh.prog.gogodie.game.monster.Blutfleck;
import de.hsh.prog.gogodie.game.monster.Monster;
import de.hsh.prog.gogodie.game.monster.MonsterFactory;
import de.hsh.prog.gogodie.game.powerup.PowerUp;
import de.hsh.prog.gogodie.game.powerup.PowerupFactory;
import de.hsh.prog.gogodie.game.state.GameState;
import de.hsh.prog.gogodie.game.utils.Content;
import de.hsh.prog.gogodie.game.utils.Data;
import de.hsh.prog.gogodie.game.utils.GameStateManager;
import de.hsh.prog.gogodie.game.utils.Highscore;
import de.hsh.prog.gogodie.game.utils.JukeBox;
import de.hsh.prog.gogodie.game.utils.Keys;
import de.hsh.prog.gogodie.game.utils.MouseHandler;
import de.hsh.prog.gogodie.game.waffe.Munition;

public abstract class PlayState extends GameState implements Runnable{

	private Player player;
	protected ArrayList<Monster> monsters;
	protected ArrayList<Blutfleck> blutflecken;
	protected ArrayList<PowerUp> powerups;
	
	protected Map map;
	
	private Hud hud;
	
	protected boolean finished;
	protected String bgm;
	
	private int tick;
	
	private boolean blockInput;
	private boolean eventClear;
	private boolean eventDie;
	private boolean eventFinish;
	private int eventTick;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		tick = 0;
		finished = false;
		
		eventTick = 0;
		blockInput = false;
		eventClear = false;
		eventDie = false;
		eventFinish = false;
		
		player = new Player(640,320,16,15);
		monsters = new ArrayList<Monster>();
		blutflecken = new ArrayList<Blutfleck>();
		powerups = new ArrayList<PowerUp>();
		hud = new Hud(player);
		MonsterFactory.setPlayerPosition(player.getX(), player.getY());
		
		JukeBox.load("/sound/pistol.wav", "pistol");
		JukeBox.load("/sound/shotgun.wav", "shotgun");
		JukeBox.load("/sound/machinegun.wav", "machinegun");
		JukeBox.setVolume("machinegun", -10);
		JukeBox.setVolume("shotgun", -10);
		JukeBox.load("/sound/pt_reload.wav", "pt_reload");
		JukeBox.load("/sound/sg_reload.wav", "sg_reload");
		JukeBox.load("/sound/mg_reload.wav", "mg_reload");
		
		Data.reset();
	}

	@Override
	public void update() {
		
		if(eventDie) event();
		if(eventFinish) event();
		if(eventClear) eventClear();
		
		if(player.getHP() <= 0) {
			blutflecken.add(new Blutfleck(player.getX(), player.getY(), 16, 16));
			eventDie = true;
			blockInput = true;
		}else
			player.update();
		
		if(finished) {
			if(this instanceof Level1) {
				Data.setLevel1Time(Data.getTime());
				eventClear = true;
			}
			else if(this instanceof Level2) {
				Data.setLevel2Time(Data.getTime());
				eventFinish = true;
			}
		}
		
		Data.update();
		
		handleInput();
		
		if (tick % 2 == 0) {
			MonsterFactory.setPlayerPosition(player.getX(), player.getY());
			Monster losch = null;
			for (Monster monster : monsters) {
				if (monster.getHP() <= 0) {
					losch = monster;
					Highscore.plusScore(losch.getPoint());
					
					PowerUp powerup = PowerupFactory.createPowerup(losch.getX(), losch.getY());
					if(powerup != null)
						powerups.add(powerup);
					else
						blutflecken.add(new Blutfleck(losch.getX(), losch.getY(), 16, 16));
				} else
					monster.update();
			}
			monsters.remove(losch);
			ActorList.actorList.remove(losch);
		}
		
		for (Blutfleck bf : blutflecken) {
			bf.update();
		}
		
		for(int i=0;i < powerups.size();i++) {
			PowerUp powerup = powerups.get(i);
			if(player.getBounds().intersects(powerup.getBounds())) {
				powerups.remove(i);
				i--;
				player.collect(powerup);
			}
		}
		
		if (tick++ >= 60) {
			execute();
			tick = 0;
		}
			
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(map.getMap(), 0, 0, null);
		
		for(Blutfleck bf : blutflecken) {
			g.drawImage(bf.getFrame(), bf.getX(), bf.getY(), (int)(bf.getWidth() * 1.3), (int)(bf.getHeight() * 1.3) ,null);
		}
		
		for(PowerUp p : powerups) {
			p.draw(g);
		}
		
		if(player.getHP() > 0)
			g.drawImage(player.getStaticFrame(), player.getX() - 8, player.getY() - 8, null);
		
		for(Monster monster : monsters){
			g.drawImage(monster.getFrame(), monster.getX(), monster.getY(), (int)(monster.getWidth() * 1.3), (int)(monster.getHeight() * 1.3) ,null);
		}
		
		for(Munition m : player.getWaffe().getMunitions()) {
			if(m.hasHit()==false)
				g.drawImage(m.getStaticFrame(), m.getX(), m.getY(), null);
		}
		
		hud.draw(g);
		
		if(eventDie)
			Content.drawString(g, "YOU DIE", 384, 328, 8, false);
		if(eventClear)
			Content.drawString(g, "LEVEL CLEAR", 288, 328, 8, false);
		if(eventFinish)
			Content.drawString(g, "YOU WIN", 384, 328, 8, false);
	}

	@Override
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			JukeBox.stop(bgm);
			gsm.setPaused(true);
		}
		
		if(blockInput) return;
		
		if(MouseHandler.pressed == true) player.shoot();
		if(Keys.isDown(Keys.R) && !player.getWaffe().isInReload()) player.reloadWaffe();
		
    	if(Keys.dirList.isEmpty()) return;
    	
    	int i = Keys.dirList.get(Keys.dirList.size() - 1);
    	if(i == KeyEvent.VK_W) player.setDirection(Direction.UP);
    	if(i == KeyEvent.VK_S) player.setDirection(Direction.DOWN);
    	if(i == KeyEvent.VK_A) player.setDirection(Direction.LEFT);
    	if(i == KeyEvent.VK_D) player.setDirection(Direction.RIGHT);
	}
	
	protected abstract int createMonster(String type);
	
	public abstract void execute();
	
	
	/*----------------------------------------------------------*/
	private void event() {
		eventTick++;
		if(eventTick == 180) {
			eventTick = 0;
			endOpreration();
		}
	}
	
	private void eventClear() {
		eventTick++;
		if(eventTick == 180) {
			eventTick = 0;
			clearOperation();
		}
	}
	
	private void endOpreration() {
		ActorList.removeAll();
		Highscore.calculate();
		JukeBox.stop(bgm);
		JukeBox.loop("menubgm", 1000, 1000, JukeBox.getFrames("menubgm") - 1000);
		gsm.setState(GameStateManager.HIGHSCORE);
	}
	
	private void clearOperation() {
		ActorList.removeAll();
		JukeBox.stop(bgm);
		gsm.setState(GameStateManager.PLAY_LEVEL2);
	}
	
}
