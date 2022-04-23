package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Arrays;
import java.util.List;

public class ToadConverseAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        List<String> opts = Arrays.asList(
                "The Princess is depending on you! You are our only hope.",
                "Being imprisoned in these walls can drive a fungus crazy :("
        );
        if (!actor.hasCapability(Status.BREAKS_KOOPA_SHELL)) {
            opts.add("You might need a wrench to smash Koopa's hard shells.");
        }
        if (!actor.hasCapability(Status.POWER_STAR_EFFECT_ONGOING)) {
            opts.add("You better get back to finding the Power Stars.");
        }
        return opts.get(12459 % opts.size()); // FIXME random
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Speak with the Toad";
    }

}
