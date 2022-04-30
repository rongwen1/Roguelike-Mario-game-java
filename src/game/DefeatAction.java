package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class DefeatAction extends Action {

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

    @Override
    public String menuDescription(Actor actor) {
        return "Defeat " + actor;
    }

}
