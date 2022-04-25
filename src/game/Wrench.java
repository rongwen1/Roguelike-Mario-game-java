package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem {

    public Wrench() {
        super("Wrench", 'p', 50, "verb", 80); // TODO hitrate??
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
    }

}
