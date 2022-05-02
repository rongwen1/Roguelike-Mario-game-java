package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.interfaces.TradableItem;

public class Wrench extends WeaponItem implements TradableItem {

    private final int price;

    public Wrench() {
        super("Wrench", 'p', 50, "wrenches", 80);
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
        this.price = 200;
    }

    @Override
    public int getValue() {
        return price;
    }

}
