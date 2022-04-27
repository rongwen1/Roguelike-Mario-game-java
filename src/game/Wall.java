package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

public class Wall extends Ground implements Jumpable {
	private MoveActorAction moveActorAction;
	private int damage;
	private double chanceToJump;

	public Wall() {

		super('#');
		this.damage = 20;
		this.chanceToJump = 0.8;

	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
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

//	public String jump(Actor by, Location at) {
//
//		Actor actor = by;
//		if (!at.containsAnActor() && canActorEnter(actor)) {
//			if (Math.random() <= 0.8) {
//				//at.map().addActor(actor, at);
//
//				//at.setGround(new Dirt());
//				at.addActor(actor);
//				return actor + " jumps onto the wall.";
//
//			}
//			else {
//				actor.hurt(20);
//				return actor + " fails to jump onto the wall with 20hp damage";
//			}
//		}
//		else{
//			return "Someone is on the wall";
//		}
//	}



	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){
		return new ActionList(new JumpAction(this, location, direction));
	}


	public String toString() {
		return "Wall";
	}
}
