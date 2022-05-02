package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actors.Enemy;
import game.actors.Player;
import game.interfaces.Behaviour;

public class AttackBehaviour implements Behaviour {

    private final Action actionOnTargetDefeat;

    public AttackBehaviour(Action actionOnTargetDefeat) {
        this.actionOnTargetDefeat = actionOnTargetDefeat;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // search for a Player in the actor's vicinity, and attack it
        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target instanceof Player) {
                if (actor instanceof Enemy) {
                    // if Enemy, aggressively follow after the player
                    ((Enemy) actor).followNewActor(target);
                }
                return new AttackAction(target, exit.getName(), actionOnTargetDefeat);
            }
        }
        return null;
    }

}
