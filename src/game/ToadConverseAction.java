package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToadConverseAction extends Action {

    private static final Random r = new Random();

    @Override
    public String execute(Actor actor, GameMap map) {
        final List<String> opts = new ArrayList<>();
        opts.add("The Princess is depending on you! You are our only hope.");
        opts.add("Being imprisoned in these walls can drive a fungus crazy :(");
        if (!actor.hasCapability(Status.BREAKS_KOOPA_SHELL)) {
            opts.add("You might need a wrench to smash Koopa's hard shells.");
        }
        if (!actor.hasCapability(Status.POWER_STAR_EFFECT_ONGOING)) {
            opts.add("You better get back to finding the Power Stars.");
        }
        final int index = r.nextInt(opts.size());
        return opts.get(index);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Speak with the Toad";
    }

}
