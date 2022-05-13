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
        this(100); // default hit points
    }

    /**
     * Constructor with an argument for koopa's hit points.
     *
     * @param hitPoints the Actor's starting hit points
     */
    public Koopa(int hitPoints) {
        super("Koopa", 'k', hitPoints, true);
        // DefeatAction is bypassed - if Koopa had droppable items, they will not be dropped
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
