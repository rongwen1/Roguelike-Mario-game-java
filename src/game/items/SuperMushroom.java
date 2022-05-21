package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.interfaces.TradableItem;

/**
 * <h1>Super Mushroom</h1>
 * Item that the actor can consume and has different buff effects.
 */
public class SuperMushroom extends MagicalItem implements TradableItem {
    /**
     * The amount of hp increase actor gets after it is consumed
     */
    private static final int increaseMaxHp = 50;
    /**
     * price to buy this item
     */
    private final int price;

    /**
     * No Parameter Constructor for Super Mushroom.
     * It initializes its name, displayChar and portability by calling its super constructor.
     */
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

    /**
     * This method will run after actor consumes this item.
     * It will set buffs on actor that consumes this item
     * @param actor actor that consume this item
     */
    @Override
    public String consume(Actor actor) {
        //set buffed actor
        String output = super.consume(actor);
        //Increase max hp of the actor
        actor.increaseMaxHp(increaseMaxHp);
        //Add capability of this item to the actor
        this.addCapabilityToActor();

        return output;
    }

    /**
     * Method that adds capability/buffs to the actor
     */
    @Override
    public void addCapabilityToActor() {
        buffedActor.addCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        buffedActor.addCapability(Status.JUMP_FREELY);
        buffedActor.addCapability(Status.TALL);
    }

    /**
     * Method that removes capability/buffs from the actor
     */
    @Override
    public void removeCapabilityFromActor() {
        buffedActor.removeCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        buffedActor.removeCapability(Status.JUMP_FREELY);
        buffedActor.removeCapability(Status.TALL);
    }

    /**
     * Checks if this item buffs should be removed from the actor
     * @return boolean true if the item buffs should be removed. False otherwise
     */
    @Override
    public boolean removeBuff() {
        boolean flag = false;

        if (!buffedActor.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)){
            //Update flag
            flag = true;
        }

        return flag;
    }
    /**
     * return price of this item
     * @return An integer representing price of this item
     */
    @Override
    public int getValue() {
        return price;
    }

}
