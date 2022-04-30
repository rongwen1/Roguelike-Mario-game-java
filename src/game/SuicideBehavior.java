package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Random;

public class SuicideBehavior implements Behaviour {

    private static final Random r = new Random();
    private static final double DEFEAT_CHANCE = 0.1;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (r.nextDouble() < DEFEAT_CHANCE) {
            return new DefeatAction();
        }
        return null;
    }

}
