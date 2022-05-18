package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DefeatAction;
import game.interfaces.Behaviour;
import java.util.Random;

/**
 * Behavior to perform {@link DefeatAction} on itself at random.
 */
public class SuicideBehavior implements Behaviour {

    /**
     * Random number generator.
     */
    private static final Random r = new Random(); // not const according to google style

    /**
     * Chance of performing DefeatAction when getAction is called.
     */
    private double defeatChance = 0.1;

    /**
     * Constructor.
     */
    public SuicideBehavior() {
    }

    /**
     * Constructor with configurable defeat chance.
     *
     * @param defeatChance chance of performing DefeatAction when getAction is called.
     */
    public SuicideBehavior(double defeatChance) {
        this.defeatChance = defeatChance;
    }

    /**
     * Performs a random roll to determine returning nothing or a new DefeatAction
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no action is to be performed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (r.nextDouble() < defeatChance) {
            return new DefeatAction(false); // do not drop items on non-player kill defeat
        }
        return null;
    }

}
