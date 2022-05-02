package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.SuicideBehavior;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

    /**
     * Constructor.
     */
    public Goomba() {
        super("Goomba", 'g', 50);
        this.behaviours.put(1, new SuicideBehavior());
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
