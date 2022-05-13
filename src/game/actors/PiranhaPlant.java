package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class PiranhaPlant extends Enemy {

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

    @Override
    public void followNewActor(Actor target) {
    } // empty method callback, as this Enemy does not follow

    @Override
    public void resetInstance() {
        super.resetInstance();
        increaseMaxHp(50);
        // TODO finish
    }

}
