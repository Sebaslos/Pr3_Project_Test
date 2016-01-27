package de.hsh.prog.gogodie.game.utils;

public class Highscore {
	
	private static PlayerInfo playinfo = new PlayerInfo();
	
	public static boolean level1_clear = false;
	public static boolean level2_clear = false;
	
	public static boolean show_current_score = false;
	
	public static void setName(String n) {
		playinfo.setName(n);
	}
	
	public static String getName() {
		return playinfo.getName();
	}
	
	public static void plusScore(long ds) {
		playinfo.setScore(playinfo.getScore() + ds);
	}
	
	public static void setScore(long l) {
		playinfo.setScore(l);
	}
	
	public static long getScore() {
		return playinfo.getScore();
	}
	
	public static void calculate() {
		long time = 0;
		if(level1_clear && level2_clear) {
			time = Data.getTotalTime();
			if(time > 0 && time <= 3600 * 2) {
				plusScore(10000);
			}else if(time > 3600 * 2 && time <=  3600 * 3) {
				plusScore(7500);
			}else if(time > 3600 * 3 && time <=  3600 * 5) {
				plusScore(5000);
			}else {
				plusScore(3000);
			}
		}else if(level1_clear) {
			plusScore(2000);
		}
		
		show_current_score = true;
		XmlUtils.updateScore(playinfo);
	}
	
	public static void clear() {
		setScore(0);
		setName(null);
		level1_clear = false;
		level2_clear = false;
	}
	
}
