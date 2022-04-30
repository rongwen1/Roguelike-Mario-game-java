package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem implements TradableItem {
    private int price;

    public Wrench() {
        super("Wrench", 'p', 50, "verb", 80); // TODO hitrate??
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
        this.price = 200;
    }

    @Override
    public int getValue() {
        return price;
    }



}



