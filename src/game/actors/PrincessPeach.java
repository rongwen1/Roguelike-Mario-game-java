package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.VictoryAction;
import game.enums.Status;
import game.interfaces.Talkable;
import java.util.List;

/**
 * Held captive by bowser in the lava zone.
 */
public class PrincessPeach extends Actor implements Talkable {

    /**
     * Number of turns played by the game since this object's instantiation.
     */
    private int tick = 0;

    /**
     * Immutable list of dialog options to be returned by {@code getDialog()}, preventing object
     * creation on each call.
     */
    private static final List<String> DIALOG_OPTS = List.of(
            "Dear Mario, I'll be waiting for you...",
            "Never gonna give you up!",
            "Release me, or I will kick you!");

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 9999); // arbitrary amounts of health
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        final ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.UNLOCKS_PEACH_HANDCUFFS)) {
            actions.add(new VictoryAction(this));
        }
        return actions;
    }

    /**
     * Provides a list of possible dialog to be chosen from by {@code processDialog()}.
     *
     * @return a list of possible dialog to choose from.
     */
    @Override
    public List<String> getDialog() {
        return DIALOG_OPTS;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in
     *                   conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (tick++ % 2 == 0) {
            processDialog(display);
        }
        return new DoNothingAction();
    }

}
