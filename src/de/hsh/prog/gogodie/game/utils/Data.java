package de.hsh.prog.gogodie.game.utils;

public class Data {

	private static long time = 0;
	
	private static long level1_time = 0;
	private static long level2_time = 0;
	
	public static long getTime() {
		return time;
	}
	
	public static long getTotalTime() {
		return level1_time + level2_time;
	}
	
	public static void plusTime(long t) {
		time += t;
	}
	
	public static void setTime(long t) {
		time = t;
	}
	
	public static void reset() {
		time = 0;
	}
	
	public static void clear() {
		time = 0;
		level1_time = 0;
		level2_time = 0;
	}
	
	public static void setLevel1Time(long l) {
		level1_time = l;
		Highscore.level1_clear = true;
	}
	
	public static void setLevel2Time(long l) {
		level2_time = l;
		Highscore.level2_clear = true;
	}
	
	public static long getLevel1Time() {
		return level1_time;
	}
	
	public static long getLevel2Time() {
		return level2_time;
	}
	
	public static void update() {
		time++;
	}
	
}
