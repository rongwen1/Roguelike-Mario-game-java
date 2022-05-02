package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.interfaces.TradableItem;

/**
 * A type of weapon, with additional ability to smash koopa's shell.
 */
public class Wrench extends WeaponItem implements TradableItem {

    /**
     * Price of a single wrench item.
     */
    private static final int PRICE = 200;

    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'p', 50, "wrenches", 80);
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
    }

    /**
     * Get the value of the item.
     *
     * @return the price of the tradable item.
     */
    @Override
    public int getValue() {
        return PRICE;
    }

}
