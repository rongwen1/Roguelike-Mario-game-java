package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Default action to perform on actor's defeat.
 */
public class DefeatAction extends Action {

    /**
     * Perform the Action.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : actor.getInventory()) {
            dropActions.add(item.getDropAction(actor));
        }
        for (Action drop : dropActions) {
            drop.execute(actor, map);
        }
        // remove actor
        map.removeActor(actor);
        return actor + " is killed.";
    }

    /**
     * Returns a descriptive string to describe the action in the menu.
     *
     * @param actor the actor performing the action.
     * @return the text we put on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Defeat " + actor;
    }

}
