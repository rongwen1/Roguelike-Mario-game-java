package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.ConsumableItem;
import game.managers.ConsumedItemManager;

/**
 * <h1>Consume Action</h1>
 * Action for consuming consumable item.
 */
public class ConsumeAction extends Action {
    /**
     * Stores instance of consumable item
     */
    private final ConsumableItem consumableItem;

    /**
     * Constructor for Consume Action
     * @param consumableItem item that actor can consume
     */
    public ConsumeAction(ConsumableItem consumableItem) {
        this.consumableItem = consumableItem;
    }

    /**
     * Perform the Action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return actor performing the action will be printed out in menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //run consume method
        this.consumableItem.consume(actor);
        //Add ConsumableItem to ConsumedItemManager
        ConsumedItemManager.getInstance().addConsumableItem(this.consumableItem);
        //Remove item from actor inventory
        this.consumableItem.removeItemFromInventory();

        //Return string to be printed
        return actor.toString() + " consumes " + consumableItem.toString();

    }


    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return action that the actor can perform
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes " + this.consumableItem.toString();
    }
}
