package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;

public class Wall extends Ground implements Jumpable {

	private int damage;
	private double chanceToJump;

	public Wall() {

		super('#');
		this.damage = 20;
		this.chanceToJump = 0.8;

	}

	@Override
	public boolean canActorEnter(Actor actor) {

		if(actor.hasCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN)){
			return true;
		}
		else{
			return false;
		}
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


	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){

		if(location.containsAnActor()){
			return new ActionList();
		}

		else if(actor.hasCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN)){
			location.setGround(new Dirt());
			location.addItem(new Coin(5));
			return new ActionList();
		}
		else {
			return new ActionList(new JumpAction(this, location, direction));

		}
	}

	public String toString() {
		return "Wall";
	}
}
