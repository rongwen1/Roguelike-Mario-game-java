package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.items.HandcuffKeys;
import java.util.List;

/**
 * The final boss.
 */
public class Bowser extends Enemy {

    /**
     * Immutable list of dialog options to be returned by {@code getDialog()}, preventing object
     * creation on each call.
     */
    private static final List<String> DIALOG_OPTS = List.of(
            "What was that sound? Oh, just a fire.",
            "Princess Peach! You are formally invited... to the creation of my new kingdom!",
            "Never gonna let you down!",
            "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");

    /**
     * If set, bowser's position should be reset the next time its turn is played.
     */
    private boolean resetPosition = false;

    /**
     * The location of bowser at the instant it had been instantiated.
     */
    private Location initialLocation;

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500, false);
        addItemToInventory(new HandcuffKeys()); // item to drop
        addCapability(Status.DROP_FIRE_WHEN_ATTACK);
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
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches"); // 50% hit rate
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
        if (initialLocation == null) {
            // set location to where Bowser is when playTurn is first ran
            initialLocation = map.locationOf(this);
        }
        if (resetPosition) {
            if (!initialLocation.containsAnActor()) {
                // will only move Bowser if its original location was empty, else it remains
                map.moveActor(this, initialLocation);
            }
            resetPosition = false;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Callback for when the current Resettable needs to be reset.
     */
    @Override
    public void resetInstance() {
        // no defeat on reset
        heal(getMaxHp());
        followNewActor(null);
        resetPosition = true;
    }

}
