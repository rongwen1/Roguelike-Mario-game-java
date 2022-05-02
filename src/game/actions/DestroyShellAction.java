package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class DestroyShellAction extends DefeatAction {

    private final Actor target;

    public DestroyShellAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        super.execute(target, map); // discard the returned string
        return "Player smashes Koopa's shell with a wrench";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Destroy Koopa's shell";
    }

}
