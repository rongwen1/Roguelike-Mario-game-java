package game;

import edu.monash.fit2099.engine.items.Item;

public class Wrench extends Item {

    public Wrench() {
        super("Wrench", 'p', true);
        this.addCapability(Status.BREAKS_KOOPA_SHELL);
    }

}
