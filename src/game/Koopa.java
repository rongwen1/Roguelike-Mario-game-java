package game;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class Koopa extends Enemy {

    public Koopa() {
        super("Koopa", 'k', 100);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches"); // 50% hit rate
    }

}
