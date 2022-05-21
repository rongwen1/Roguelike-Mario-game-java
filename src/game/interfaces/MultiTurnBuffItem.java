package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Consumable Item</h1>
 * Consumable Item interface that has methods to activate the buffs of item,
 * add/remove capability from actor.
 */
public interface MultiTurnBuffItem {
    /**
     * Empty method that contains buffs after it is consumed by actor
     * @param actor that consumes this item
     */
    String consume(Actor actor);

    /**
     * method that adds capability to the actor
     */
    void addCapabilityToActor();

    /**
     * method that removes capability from the actor
     */
    void removeCapabilityFromActor();

    /**
     * method that removes item from inventory
     */
    void removeItemFromInventory();

    /**
     * method that checks if item buffs should be removed from the actor
     * @return boolean true if item buffs should be removed. False otherwise
     */
    boolean removeBuff();
}

