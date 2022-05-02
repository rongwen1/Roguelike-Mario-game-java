package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actors.Enemy;
import game.actors.Player;
import game.interfaces.Behaviour;

/**
 * Behavior to attack an adjacent Player.
 *
 * @see Player
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The action to perform when the target attacked is defeated.
     */
    private final Action actionOnTargetDefeat;

    /**
     * Constructor.
     *
     * @param actionOnTargetDefeat the action to perform when the target attacked is defeated.
     */
    public AttackBehaviour(Action actionOnTargetDefeat) {
        this.actionOnTargetDefeat = actionOnTargetDefeat;
    }

    /**
     * Conditionally returns an AttackAction on an adjacent Player actor.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
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
