package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public abstract class HighGround extends Ground implements Jumpable {

    public HighGround(char displayChar){
        super(displayChar);
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
