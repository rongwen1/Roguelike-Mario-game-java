package game;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

    public Goomba() {
        super("Goomba", 'g', 50);
        this.behaviours.put(123, new SuicideBehavior());
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "kicks"); // 50% hit rate
    }

}
