package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;
import game.actions.JumpAction;
import game.interfaces.Jumpable;
import game.Status;

/**
 * <h1>High Ground</h1>
 * Abstract class that represents high ground.
 */
public abstract class HighGround extends Ground implements Jumpable {

	/**
	 * Constructor for high ground
	 * @param displayChar
	 */
    public HighGround(char displayChar){
        super(displayChar);
    }

	/**
	 * Impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor the Actor to check
	 * @return true if actor can enter this terrain, false otherwise
	 */
    @Override
    public boolean canActorEnter(Actor actor) {
		// Player can walk to the high ground, no need to jump after consuming power star
        if(actor.hasCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN)){
            return true;
        }
        else{
            return false;
        }
    }

	/**
	 * Returns a new Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new collection of Actions
	 */
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){

		if(location.containsAnActor()){
			return new ActionList();
		}
         // destroy ground
		else if(actor.hasCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN)){
			location.setGround(new Dirt());
			location.addItem(new Coin(5));
			return new ActionList();
		}
		else {
			return new ActionList(new JumpAction(this, location, direction));

		}
	}

}
