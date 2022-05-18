package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;

public class HandcuffKeys extends Item {

    /***
     * Constructor.
     */
    public HandcuffKeys(){
        super("Handcuff Keys", 'k', true);
        this.addCapability(Status.UNLOCKS_PEACH_HANDCUFFS);
    }

}
