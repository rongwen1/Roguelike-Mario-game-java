package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Non Multi Turn Buff Item</h1>
 * Consumable Item interface that has methods to activate the buffs of item,
 * add/remove capability from actor.
 */
public interface NonMultiTurnBuffItem {
    /**
     * Empty method that contains buffs after it is consumed by actor
     * @param actor actor that consume this item
     * @return description of item action
     */
    String consume(Actor actor);

}
