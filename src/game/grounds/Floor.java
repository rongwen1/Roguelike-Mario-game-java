package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Enemy;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

    /**
     * Constructor for floor
     */
    public Floor() {
        super('_');
    }

    /**
     * Impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true if actor can enter this terrain, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean allowEnterFloor = !(actor instanceof Enemy);
        return allowEnterFloor && super.canActorEnter(actor);
    }

}
