package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class SuperMushroom extends MagicalItem{
    private static final int increaseHp = 50;

    public SuperMushroom() {
        super("Super Mushroom", '^', true);
    }

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public void consume(Actor actor) {
        //Increase max hp of the actor
        actor.increaseMaxHp(increaseHp);
        //Add capability of this item to the actor
        this.addCapabilityToActor(actor);

    }

    @Override
    public void addCapabilityToActor(Actor actor) {
        actor.addCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        actor.addCapability(Status.JUMP_FREELY);
    }

    @Override
    public void removeCapabilityFromActor(Actor actor) {
        actor.removeCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING);
        actor.removeCapability(Status.JUMP_FREELY);
    }

    @Override
    public boolean removeBuff(Actor actor) {
        boolean flag = false;

        if (!actor.hasCapability(Status.SUPER_MUSHROOM_EFFECT_ONGOING)){
            //Update flag
            flag = true;
        }

        return flag;
    }
}
