package game;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class Koopa extends Enemy {

    public Koopa() {
        // DefeatAction is bypassed - if Koopa had droppable items, they will not be dropped
        super("Koopa", 'k', 100, new DormantAction());
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches"); // 50% hit rate
    }

}
