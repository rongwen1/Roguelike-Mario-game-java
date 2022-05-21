package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.NonMultiTurnBuffItem;
import game.interfaces.MultiTurnBuffItem;
import game.managers.ConsumedItemManager;

/**
 * <h1>Consume Action</h1>
 * Action for consuming consumable item.
 */
public class ConsumeAction extends Action {
    /**
     * Stores instance of consumable item
     */
    private final MultiTurnBuffItem multiturnBuffItem;
    private final NonMultiTurnBuffItem nonMultiturnBuffItem;

    /**
     * Constructor for Consume Action
     * @param multiturnBuffItem item that actor can consume
     */
    public ConsumeAction(MultiTurnBuffItem multiturnBuffItem) {
        this.multiturnBuffItem = multiturnBuffItem;
        this.nonMultiturnBuffItem = null;
    }

    /**
     * Constructor for Consume Action
     * @param item item that actor can consume
     */
    public ConsumeAction(NonMultiTurnBuffItem item) {
        this.nonMultiturnBuffItem = item;
        this.multiturnBuffItem = null;
    }

    /**
     * Perform the Action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return actor performing the action will be printed out in menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "";
        if (this.multiturnBuffItem != null){
            //run consume method. It outputs string to be printed after it is consumed.
            output = this.multiturnBuffItem.consume(actor);
            //Add ConsumableItem to ConsumedItemManager
            ConsumedItemManager.getInstance().addConsumableItem(this.multiturnBuffItem);
            //Remove item from actor inventory
            this.multiturnBuffItem.removeItemFromInventory();
        }
        else if (this.nonMultiturnBuffItem != null){
            //run consume method. It outputs string to be printed after it is consumed.
            output = this.nonMultiturnBuffItem.consume(actor);
        }

        //Return string to be printed
        return output;

    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return action that the actor can perform
     */
    @Override
    public String menuDescription(Actor actor) {
        String output = "";
        if (this.multiturnBuffItem != null){
            output = actor.toString() + " consumes " + this.multiturnBuffItem;
        }
        else if (this.nonMultiturnBuffItem != null){
            output = actor.toString() + " consumes " + this.nonMultiturnBuffItem;
        }
        return output;
    }
}
