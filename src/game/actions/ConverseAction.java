package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Talkable;

/**
 * Action performed on the supplied {@code target} to obtain a random dialog line.
 */
public class ConverseAction extends Action {

    private final Talkable target;

    /**
     * Constructor.
     *
     * @param target the actor to be spoken to by the actor executing the action.
     */
    public ConverseAction(Talkable target) {
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
     * Returns a descriptive string to describe the action in the menu. Further assumes that {@code
     * target}, ie. {@code Actor}, correctly implements toString().
     *
     * @param actor the actor performing the action.
     * @return the text we put on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Speak with " + target;
    }

}