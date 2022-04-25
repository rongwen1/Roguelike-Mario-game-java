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

        //Remove the capabilities added to the actor by this item after 10 effect turns
        if (effectTurn == 10){
            //Remove capability of this item from the actor.  ////STILL NEED TO UPDATE////
            List<Enum<?>> status = this.capabilitiesList();
            for (Enum<?> stat: status){
                actor.removeCapability(stat);
            }
            isActive = false;
            //Remove the item from actor's inventory
            actor.removeItemFromInventory(this);

            System.out.println("Removed the capability of " + this.toString());
        }

        //Increment effect turn when item effect is active
        if (isActive){
            effectTurn += 1;
            System.out.println("EFFECT TURN: " + effectTurn);
        }


    }

    @Override
    public String run(Actor actor) {
        //Increase max hp of the actor
        actor.increaseMaxHp(increaseHp);

        //Update attributes
        effectTurn = 0;

        return super.run(actor);
    }
}
