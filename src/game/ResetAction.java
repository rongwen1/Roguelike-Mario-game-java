package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ResetAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        if (actor instanceof Player) {
            ((Player) actor).markResetAsDone();
        }
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    @Override
    public String hotkey() {
        return "r";
    }

}
