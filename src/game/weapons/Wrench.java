package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.interfaces.TradableItem;
/**
 * <h1>Wrench</h1>
 * Weapon used to kill enemy.
 */
public class Wrench extends WeaponItem implements TradableItem {

    /**
     * price of wrench
     */
    private final int price;

    /**
     * Constructor
     */
    public Wrench() {
        super("Wrench", 'p', 50, "wrenches", 80);
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
        this.price = 200;
    }
    /**
     *
     * @return An integer representing price of this item
     */
    @Override
    public int getValue() {
        return price;
    }

}
