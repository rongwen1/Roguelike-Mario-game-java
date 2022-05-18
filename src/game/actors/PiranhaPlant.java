package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import java.util.List;

public class PiranhaPlant extends Enemy {

    private static final List<String> DIALOG_OPTS = List.of(
            "Slsstssthshs~! (Never gonna say goodbye~)",
            "Ohmnom nom nom nom.");

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 10, false);
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

    @Override
    public List<String> getDialog() {
        return DIALOG_OPTS;
    }

    @Override
    public void followNewActor(Actor target) {
    } // override with empty method callback, as this Enemy does not follow

    @Override
    public void resetInstance() {
        // no defeat on reset
        increaseMaxHp(50); // auto heals to max
    }

}
