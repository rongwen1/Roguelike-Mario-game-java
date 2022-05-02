package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.DormantKoopa;

/**
 * Action performed by a Koopa when it goes into dormancy.
 */
public class DormantAction extends Action {

    /**
     * Perform the Action.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        map.removeActor(actor);
        loc.addActor(new DormantKoopa());
        return "Koopa has gone into hiding!";
    }
    /**
     * Returns a descriptive string to describe the action in the menu.
     *
     * @param actor the actor performing the action.
     * @return the text we put on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

}
