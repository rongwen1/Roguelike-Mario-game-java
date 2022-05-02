package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Enemy;
import game.actions.DormantAction;

public class Koopa extends Enemy {

    public Koopa() {
        // DefeatAction is bypassed - if Koopa had droppable items, they will not be dropped
        super("Koopa", 'k', 100);
        super.actionOnSelfDefeat = new DormantAction();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches"); // 50% hit rate
    }

}
