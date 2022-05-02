package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.interfaces.TradableItem;


/**
 * <h1>Power Star</h1>
 * Item that the actor can consume and has different buff effects.
 */
public class PowerStar extends MagicalItem implements TradableItem {
    /**
     * number of turns this item have stayed in this world
     */
    private final int turns;
    /**
     * number of turns this item has buffed the actor
     */
    private int effectTurns;
    /**
     * The amount of hp increase actor gets after it is consumed
     */
    private static final int increaseHp = 200;

    private final int price;

    /**
     * No Parameter Constructor for Power Star.
     * It initializes its name, displayChar and portability by calling its super constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        turns = 0;
        this.price = 600;
    }

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        turns = 0;
        this.price = 600;
    }

    /**
     * Called once per turn, so that items can experience the passage of time.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        //Remove this item from location after 10 turns in the world
        if (turns == 100000){
            currentLocation.removeItem(this);
        }

        super.tick(currentLocation);
    }

    /**
     * Called once per turn, so that items can experience the passage of time.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        //Remove this item from actor after 10 turns in the world
        if (turns == 100000){
            actor.removeItemFromInventory(this);
        }

        super.tick(currentLocation, actor);
    }

    /**
     * This method will run after actor consumes this item.
     * It will set buffs on actor that consumes this item
     * @param actor
     */
    @Override
    public void consume(Actor actor) {
        //Set buffed actor
        super.consume(actor);
        //Increase max hp of the actor
        actor.increaseMaxHp(increaseHp);
        //Add capability of this item to the actor
        this.addCapabilityToActor();

        //Update effect turns
        effectTurns = 10;

    }

    /**
     * Method that adds capability/buffs to the actor
     */
    @Override
    public void addCapabilityToActor() {
        buffedActor.addCapability(Status.POWER_STAR_EFFECT_ONGOING);
        buffedActor.addCapability(Status.INVINSIBLE);
        buffedActor.addCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN);
        buffedActor.addCapability(Status.INSTANT_KILL_ENEMY);
    }

    /**
     * Method that removes capability/buffs from the actor
     */
    @Override
    public void removeCapabilityFromActor() {
        buffedActor.removeCapability(Status.POWER_STAR_EFFECT_ONGOING);
        buffedActor.removeCapability(Status.INVINSIBLE);
        buffedActor.removeCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN);
        buffedActor.removeCapability(Status.INSTANT_KILL_ENEMY);
    }

    /**
     * Checks if this item buffs should be removed from the actor
     * @return boolean true if the item buffs should be removed. False otherwise
     */
    @Override
    public boolean removeBuff() {
        boolean flag = false;

        if (effectTurns == 0){
            //Update flag
            flag = true;
        }

        //Output the remaining effect turns
        if (!flag){
            System.out.println(buffedActor.toString() + " consumes " + this + " - " + effectTurns + " turns remaining");
        }

        //Increment turns
        effectTurns -= 1;

        return flag;
    }

    @Override
    public int getValue() {
        return price;
    }
}
