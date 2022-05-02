package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class DestroyShellAction extends DefeatAction {

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(actor, map); // discard the returned string
        return "Player smashes Koopa's shell with a wrench";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Destroy Koopa's shell";
    }

}
