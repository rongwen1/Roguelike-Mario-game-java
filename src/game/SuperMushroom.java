package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public class SuperMushroom extends MagicalItem{
    private static final int increaseHp = 50;
    private int effectTurn;

    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addCapability(Status.SUPER_MUSHROOM_WILLBECHANGEDLATER);
    }

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addCapability(Status.SUPER_MUSHROOM_WILLBECHANGEDLATER);
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
        super.addCapabilityToActor(actor);

        //Update attributes
        effectTurn = 0;

    }

    @Override
    public boolean consumedTicker(Actor actor) {
        boolean flag = false;
        effectTurn += 1;

        if (effectTurn == 10){
            //Update flag
            flag = true;
        }

        return flag;
    }
}
