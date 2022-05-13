package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.drinkables.PowerWater;
import game.actions.RefillAction;

public class PowerFountain extends Ground {
    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionlist = new ActionList();

        if (location.containsAnActor()){
            actionlist.add(new RefillAction(new PowerWater()));
        }

        return actionlist;
    }

}