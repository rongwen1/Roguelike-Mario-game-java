package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem implements TradableItem {

    private final int price;

    public Wrench() {
        super("Wrench", 'p', 50, "verb", 80);
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
        this.price = 200;
    }

    @Override
    public int getValue() {
        return price;
    }

}
