package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Random;

public class SuicideBehavior implements Behaviour {

    private static final Random r = new Random();
    private static final int DEFEAT_CHANCE = 10; // 1 in DEFEAT_CHANCE chance to happen

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (r.nextInt(DEFEAT_CHANCE) == 0) {
            return new DefeatAction();
        }
        return null;
    }

}
