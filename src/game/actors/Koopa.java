package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DormantAction;

/**
 * Koopa Troopas, commonly shortened to Koopas or Troopas, known in Japan as Nokonoko, are reptilian
 * mini-troopers of the Koopa Troop from the Mario franchise. When defeated, Koopas may flee from or
 * retreat inside their shells.
 */
public class Koopa extends Enemy {

    /**
     * Constructor.
     */
    public Koopa() {
        // DefeatAction is bypassed - if Koopa had droppable items, they will not be dropped
        super("Koopa", 'k', 100);
        super.actionOnSelfDefeat = new DormantAction();
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
