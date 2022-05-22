package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;

/**
 * <h1>Lava</h1>
 * A ground that will cause 15 hp damage on player when player stands on it
 */
public class Lava extends Ground {

    /**
     * Constructor.
     *
     */
    public Lava(){
        super('L');

    }

    /**
     * Impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true if actor can enter this terrain, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        final boolean allowEnterFloor = !(actor instanceof Enemy);
        return allowEnterFloor && super.canActorEnter(actor);
    }

    /**
     * Add the allowable action to the ground
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList that has all the actions can be performed on this ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // If player is still standing on Lava, hurt player
        if(location.containsAnActor()){
            actor.hurt(15);

        }
        return new ActionList();

    }
}
