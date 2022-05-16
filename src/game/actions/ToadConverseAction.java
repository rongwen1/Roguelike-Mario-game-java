package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Talkable;

/**
 * Action performed on the Toad to obtain a random dialog line.
 *
 * @see game.actors.Toad
 */
public class ToadConverseAction extends Action {

    private final Talkable target;

    public ToadConverseAction(Talkable target) {
        super();
        this.target = target;
    }

    /**
     * Perform the Action.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return target.processDialog();
    }

    /**
     * Returns a descriptive string to describe the action in the menu.
     * Further assumes that {@code target}, ie. {@code Actor}, correctly implements toString().
     *
     * @param actor the actor performing the action.
     * @return the text we put on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Speak with " + target;
    }

}