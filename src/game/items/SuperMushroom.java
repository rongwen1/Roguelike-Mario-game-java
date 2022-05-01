package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.TradableItem;

public class SuperMushroom extends MagicalItem implements TradableItem {
    private static final int increaseHp = 50;
    private final int price;

    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.price = 400;
    }

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.price = 400;
    }

    @Override
    public void consume(Actor actor) {
        //set buffed actor
        super.consume(actor);
        //Increase max hp of the actor
        actor.increaseMaxHp(increaseHp);
        //Add capability of this item to the actor
        this.addCapabilityToActor();

    }

    @Override
    public void addCapabilityToActor() {
        buffedActor.addCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        buffedActor.addCapability(Status.JUMP_FREELY);
    }

    @Override
    public void removeCapabilityFromActor() {
        buffedActor.removeCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        buffedActor.removeCapability(Status.JUMP_FREELY);
    }

    @Override
    public boolean removeBuff() {
        boolean flag = false;

        if (!buffedActor.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)){
            //Update flag
            flag = true;
        }

        return flag;
    }

    @Override
    public int getValue() {
        return price;
    }
}
