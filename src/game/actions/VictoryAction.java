package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class VictoryAction extends Action {

    private final Actor target;

    public VictoryAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Removes the player, ending the engine's game loop in World. Assumes that the only actor
        // executing this action would be the player,
        map.removeActor(actor);
        return "VICTORY: WON THE GAME!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Unlock " + target + "'s handcuffs";
    }

}
