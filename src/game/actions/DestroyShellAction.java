package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action performed when destroying a Koopa's shell.
 */
public class DestroyShellAction extends DefeatAction {
    /**
     * The Actor that is to be attacked
     */
    private final Actor target;

    /**
     * Constructor with one argument.
     * @param target the Actor that is to be attacked
     */
    public DestroyShellAction(Actor target) {
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
        super.execute(target, map); // discard the returned string
        return "Player smashes Koopa's shell with a wrench";
    }

    /**
     * Returns a descriptive string to describe the action in the menu.
     *
     * @param actor the actor performing the action.
     * @return the text we put on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Destroy Koopa's shell";
    }

}
