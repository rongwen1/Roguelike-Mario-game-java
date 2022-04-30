package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeAction extends Action {

    private final ConsumableItem consumableItem;

    public ConsumeAction(ConsumableItem consumableItem) {
        this.consumableItem = consumableItem;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes " + this.consumableItem.toString();
    }
}
