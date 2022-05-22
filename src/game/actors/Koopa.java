package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DormantAction;
import java.util.List;

/**
 * Koopa Troopas, commonly shortened to Koopas or Troopas, known in Japan as Nokonoko, are reptilian
 * mini-troopers of the Koopa Troop from the Mario franchise. When defeated, Koopas may flee from or
 * retreat inside their shells.
 */
public class Koopa extends Enemy {

    /**
     * Immutable list of dialog options to be returned by {@code getDialog()}, preventing object
     * creation on each call.
     */
    protected static final List<String> DIALOG_OPTS = List.of(
            "Never gonna make you cry!",
            "Koopi koopi koopii~!");

    /**
     * Constructor for spawning Koopas.
     */
    public Koopa() {
        this("Koopa", 'k', 100); // default hit points
    }

    /**
     * Constructor with an argument for koopa's hit points, amongst other attributes. Used for
     * extending Koopa. Also holds default initialization logic for other constructor signatures.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, true);
        // DefeatAction is bypassed - if Koopa had droppable items, they will not be dropped
        super.actionOnSelfDefeat = new DormantAction();
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
        return new IntrinsicWeapon(30, "punches"); // 50% hit rate
    }

}
