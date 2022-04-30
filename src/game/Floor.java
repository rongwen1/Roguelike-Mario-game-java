package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

    public Floor() {
        super('_');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        boolean allowEnterFloor = !(actor instanceof Enemy);
        return allowEnterFloor && super.canActorEnter(actor);
    }

}
