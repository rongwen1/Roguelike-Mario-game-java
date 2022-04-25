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
        String result = this.consumableItem.run(actor);
        //Return string to be printed
        return result;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes " + this.consumableItem.toString();
    }
}
