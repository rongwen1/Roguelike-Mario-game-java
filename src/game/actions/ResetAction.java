package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.managers.ResetManager;

/**
 * <h1>Reset Action</h1>
 * Action for resetting the game
 */
public class ResetAction extends Action {

    /**
     * perform reset action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return actor performing the action will be printed out in menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();

        return "The game has been reset";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return action that actor can perform
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game (Can only be used once)";
    }

    /**
     * Returns a hotkey to perform this action
     * @return hotkey to perform this action
     */
    @Override
    public String hotkey() {
        return "r";
    }

}
