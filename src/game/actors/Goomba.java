package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.SuicideBehavior;
import java.util.List;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

    /**
     * Immutable list of dialog options to be returned by {@code getDialog()}, preventing object
     * creation on each call.
     */
    private static final List<String> DIALOG_OPTS = List.of(
            "Mugga mugga!",
            "Ugha ugha... (Never gonna run around and desert you...)",
            "Ooga-Chaka Ooga-Ooga!");

    /**
     * Constructor.
     */
    public Goomba() {
        super("Goomba", 'g', 50, true);
        this.behaviours.put(1, new SuicideBehavior());
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
        return new IntrinsicWeapon(10, "kicks"); // 50% hit rate
    }

}
