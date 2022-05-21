package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.drinkables.PowerWater;
import game.actions.RefillAction;

/**
 * <h1>Power Fountain</h1>
 * Ground that enables player to refill power water
 */
public class PowerFountain extends Ground {
    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A');
    }

    /**
     * Checks and returns new actions that actor can perform
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns new actions that can be performed by the actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionlist = new ActionList();

        if (location.containsAnActor()){
            actionlist.add(new RefillAction(new PowerWater()));
        }

        return actionlist;
    }
}