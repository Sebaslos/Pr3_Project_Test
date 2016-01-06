package de.hsh.prog.gogodie.game.actor;

import java.util.ArrayList;

public class ActorList {

	public static ArrayList<Actor> actorList = new ArrayList<Actor>();
	
	public static void add(Actor actor) {
			actorList.add(actor);
	}
	
	public static void addAll(ArrayList<? extends Actor>actors) {
		actorList.addAll(actors);
	}
	
	public static void removeAll() {
		actorList.clear();
	}
	
	public static boolean isDualPosition(Actor actor) {
		for(Actor at : actorList) {
			if(actor.getBounds().intersects(at.getBounds()))
				return true;
		}
		return false;
	}
}
