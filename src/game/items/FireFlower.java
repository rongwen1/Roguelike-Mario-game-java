package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.items.MagicalItem;

/**
 * <h1>Fire Flower</h1>
 * Item that can be picked up and consumed by actor.
 */
public class FireFlower extends MagicalItem {
    /**
     * number of turns this item has buffed the actor
     */
    private int effectTurns;

    /***
     * Constructor.
     */
    public FireFlower() {
        super("Fire Flower", 'f', true);
    }

    /**
     * Method that adds capability/buffs to the actor
     */
    @Override
    public void addCapabilityToActor() {
        buffedActor.addCapability(Status.DROP_FIRE_WHEN_ATTACK);
    }

    /**
     * Method that removes capability/buffs from the actor
     */
    @Override
    public void removeCapabilityFromActor() {
        buffedActor.removeCapability(Status.DROP_FIRE_WHEN_ATTACK);
    }

    /**
     * This method will run after actor consumes this item.
     * It will set buffs on that actor.
     * @param actor actor that consume this item
     */
    @Override
    public String consume(Actor actor) {
        //Set buffed actor
        String output = super.consume(actor);
        //Add capability of this item to the actor
        this.addCapabilityToActor();

        //Update effect turns
        effectTurns = 20;

        return output;
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
}
