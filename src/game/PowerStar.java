package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;

public class PowerStar extends MagicalItem implements TradableItem{
    private final int turns;
    private static final int increaseHp = 200;
    private int effectTurns;
    private int price;

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

    @Override
    public void tick(Location currentLocation) {
        //Remove this item from location after 10 turns in the world
        if (turns == 100000){
            currentLocation.removeItem(this);
        }

        super.tick(currentLocation);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        //Remove this item from actor after 10 turns in the world
        if (turns == 100000){
            actor.removeItemFromInventory(this);
        }

        super.tick(currentLocation, actor);
    }

    @Override
    public void consume(Actor actor) {
        //Increase max hp of the actor
        actor.increaseMaxHp(increaseHp);
        //Add capability of this item to the actor
        this.addCapabilityToActor(actor);

        //Update effect turns
        effectTurns = 10;

    }

    @Override
    public void addCapabilityToActor(Actor actor) {
        actor.addCapability(Status.POWER_STAR_EFFECT_ONGOING);
        actor.addCapability(Status.INVINSIBLE);
        actor.addCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN);
        actor.addCapability(Status.INSTANT_KILL_ENEMY);
    }

    @Override
    public void removeCapabilityFromActor(Actor actor) {
        actor.removeCapability(Status.POWER_STAR_EFFECT_ONGOING);
        actor.removeCapability(Status.INVINSIBLE);
        actor.removeCapability(Status.DESTROY_HIGHER_GROUND_TO_$5COIN);
        actor.removeCapability(Status.INSTANT_KILL_ENEMY);
    }

    @Override
    public boolean removeBuff(Actor actor) {
        boolean flag = false;

        if (effectTurns == 0){
            //Update flag
            flag = true;
        }

        //Output the remaining effect turns
        if (!flag){
            System.out.println(actor.toString() + " consumes " + this.toString() + " - " + effectTurns + " turns remaining");
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