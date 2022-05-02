package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.managers.ResetManager;

public class ResetAction extends Action {
    private Item item;

    public ResetAction(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();

        //Remove item from actor
        actor.removeItemFromInventory(this.item);
        return "The game has been reset";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game (Can only be used once)";
    }

    @Override
    public String hotkey() {
        return "r";
    }

}
