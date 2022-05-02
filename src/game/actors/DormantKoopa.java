package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DestroyShellAction;
import game.enums.Status;
import game.items.SuperMushroom;

/**
 * A Koopa that has gone into dormancy.
 * @see Koopa
 */
public class DormantKoopa extends Actor {

    /**
     * Constructor.
     */
    public DormantKoopa() {
        super("Dormant Koopa", 'D', 9999); // arbitrary amounts of health.
        this.addItemToInventory(new SuperMushroom()); // item to drop.
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.BREAKS_KOOPA_SHELL)) {
            // append action to super's list
            actions.add(new DestroyShellAction(this));
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

}
