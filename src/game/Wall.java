package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

public class Wall extends HighGround {

	private int damage;
	private double chanceToJump;
	private final String NAME;

	public Wall() {
		super('#');
		this.damage = 20;
		this.chanceToJump = 0.8;
		this.NAME = "Wall";

	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public int damage() {
		return damage;
	}

	@Override
	public double chanceToJump() {
		return chanceToJump;
	}

	public String toString() {
		return NAME;
	}
}
