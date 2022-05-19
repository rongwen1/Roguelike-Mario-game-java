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

public class Bowser extends Enemy {

    private static final List<String> DIALOG_OPTS = List.of(
            "What was that sound? Oh, just a fire.",
            "Princess Peach! You are formally invited... to the creation of my new kingdom!",
            "Never gonna let you down!",
            "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
    private boolean resetPosition = false;
    private Location initialLocation;

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500, false);
        addItemToInventory(new HandcuffKeys()); // item to drop
        addCapability(Status.DROP_FIRE_WHEN_ATTACK);
    }

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

    @Override
    public void resetInstance() {
        // no defeat on reset
        heal(getMaxHp());
        followNewActor(null);
        resetPosition = true;
    }

}
