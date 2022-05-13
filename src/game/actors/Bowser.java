package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HandcuffKeys;

public class Bowser extends Enemy {

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500, false);
        this.addItemToInventory(new HandcuffKeys()); // item to drop
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches"); // 50% hit rate
    }

    @Override
    public void resetInstance() {
        super.resetInstance();
        heal(getMaxHp());
        // TODO finish
    }

}
