package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action to be executed upon winning the game.
 */
public class VictoryAction extends Action {

    private final Actor target;

    /**
     * Constructor.
     *
     * @param target the actor with which to print the menu description for.
     */
    public VictoryAction(Actor target) {
        this.target = target;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Removes the player, ending the engine's game loop in World. Assumes that the only actor
        // executing this action would be the player,
        map.removeActor(actor);
        return "VICTORY: WON THE GAME!";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unlock " + target + "'s handcuffs";
    }

}
