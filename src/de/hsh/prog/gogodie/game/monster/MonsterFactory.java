package de.hsh.prog.gogodie.game.monster;

import java.awt.Rectangle;

public class MonsterFactory {
	
	public static int player_x;
	public static int player_y;

	public static Monster createMonster(String type,int x,int y){
		if(type.equals("NormalMonster")){
			return new NormalMonster(new Rectangle(x, y, 16, 16));
		}else if(type.equals("SpeedMonster")){
			return new SpeedMonster(new Rectangle(x, y, 16, 16));
		}else if(type.equals("SlowlyMonster")){
			return new SlowlyMonster(new Rectangle(x, y, 16, 16));
		}else if(type.equals("Boss1")){
			return new Boss1(new Rectangle(x, y, 16, 16));
		}else if(type.equals("Boss2")){
			return new Boss2(new Rectangle(x, y, 16, 16));
		}else 
			return null;
	}
	
	public static void setPlayerPosition(int x,int y){
		player_x = x;
		player_y = y;
	}
	
}
