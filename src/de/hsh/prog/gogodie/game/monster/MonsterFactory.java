package de.hsh.prog.gogodie.game.monster;

import java.util.Random;

public class MonsterFactory {
	
	public static int player_x;
	public static int player_y;

	public static Monster createMonster(String type,int x,int y){
		if(type.equals("NormalMonster")){
			return new NormalMonster(x, y, 16, 16);
		}else if(type.equals("SpeedMonster")){
			return new SpeedMonster(x, y, 16, 16);
		}else if(type.equals("SlowlyMonster")){
			return new SlowlyMonster(x, y, 16, 16);
		}else if(type.equals("Boss1")){
			return new Boss1(x, y, 16, 16);
		}else if(type.equals("Boss2")){
			return new Boss2(x, y, 16, 16);
		}else if(type.equals("Monster")) {
			Random r = new Random();
			int i = r.nextInt(3);
			switch(i) {
			case 0:
				return new NormalMonster(x, y, 16, 16);
			case 1:
				return new SpeedMonster(x, y, 16, 16);
			case 2:
				return new SlowlyMonster(x, y, 16, 16);
			default:
				return null;
			}
		}else
			return null;
	}
	
	public static void setPlayerPosition(int x,int y){
		player_x = x;
		player_y = y;
	}
	
}
