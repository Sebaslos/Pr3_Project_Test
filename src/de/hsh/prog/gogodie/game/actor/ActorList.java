package de.hsh.prog.gogodie.game.actor;

import java.util.ArrayList;

public class ActorList {

	public static ArrayList<Actor> actorList = new ArrayList<Actor>();
	
	public static void add(Actor actor) {
		actorList.add(actor);
	}
	
	public static void addA(ArrayList<Actor>actors) {
		actorList.addAll(actors);
	
	}
}
