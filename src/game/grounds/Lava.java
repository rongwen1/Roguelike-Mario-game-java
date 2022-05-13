package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.LavaAction;
import game.actors.Enemy;

public class Lava extends Ground {


    /**
     * Constructor.
     *
     */
    public Lava(){
        super('L');

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        final boolean allowEnterFloor = !(actor instanceof Enemy);
        return allowEnterFloor && super.canActorEnter(actor);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // If actor is still standing on Lava, hurt actor again
        if(location.containsAnActor()){
            actor.hurt(15);
            return new ActionList();
        }

        else{
            return new ActionList(new LavaAction(direction, location));
        }
    }
}
