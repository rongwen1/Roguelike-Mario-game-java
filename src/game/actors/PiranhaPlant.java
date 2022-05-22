package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import java.util.List;

/**
 * An enemy that sits on top of warp pipes.
 */
public class PiranhaPlant extends Enemy {

    /**
     * Immutable list of dialog options to be returned by {@code getDialog()}, preventing object
     * creation on each call.
     */
    private static final List<String> DIALOG_OPTS = List.of(
            "Slsstssthshs~! (Never gonna say goodbye~)",
            "Ohmnom nom nom nom.");

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150, false);
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps"); // 50% hit rate
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
     * Swap to a new Actor for the current Enemy to follow, or stop following the current follow
     * target.
     * <br> Overridden with an empty method callback to prevent default logic from executing, as
     * this Enemy does not follow.
     *
     * @param target the new actor to follow, or null if to reset the target to follow.
     */
    @Override
    public void followNewActor(Actor target) {
    }

    /**
     * Callback for when the current Resettable needs to be reset.
     */
    @Override
    public void resetInstance() {
        // no defeat on reset
        increaseMaxHp(50); // auto heals to max
    }

}
