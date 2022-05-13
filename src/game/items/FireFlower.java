package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.items.MagicalItem;

public class FireFlower extends MagicalItem {
    private int effectTurns;

    /***
     * Constructor.
     */
    public FireFlower() {
        super("Fire Flower", 'f', true);
    }

    @Override
    public void addCapabilityToActor() {
        buffedActor.addCapability(Status.DROP_FIRE_WHEN_ATTACK);
    }

    @Override
    public void removeCapabilityFromActor() {
        buffedActor.removeCapability(Status.DROP_FIRE_WHEN_ATTACK);
    }

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
