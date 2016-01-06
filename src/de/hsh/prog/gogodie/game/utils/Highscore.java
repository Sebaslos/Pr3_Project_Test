package de.hsh.prog.gogodie.game.utils;

public class Highscore {
	
	private static long score = 0;
	
	public static boolean level1_clear = false;
	public static boolean level2_clear = false;
	
	public static boolean show_current_score = false;
	
	public static void plusScore(long ds) {
		score += ds;
	}
	
	public static void setScore(long l) {
		score = l;
	}
	
	public static long getScore() {
		return score;
	}
	
	public static void calculate() {
		long time = 0;
		if(level1_clear && level2_clear) {
			time = Data.getTotalTime();
			if(time > 0 && time <= 3600 * 2) {
				score += 10000;
			}else if(time > 3600 * 2 && time <=  3600 * 3) {
				score += 7500;
			}else if(time > 3600 * 3 && time <=  3600 * 5) {
				score += 5000;
			}else {
				score += 3000;
			}
		}else if(level1_clear) {
			score += 2000;
		}
		
		show_current_score = true;
		XmlUtils.updateScore(score);
	}
	
	public static void clear() {
		score = 0;
		level1_clear = false;
		level2_clear = false;
	}
	
}
