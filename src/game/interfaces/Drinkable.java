package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Drinkable</h1>
 * Drinkable interface that has method to drink this water.
 */
public interface Drinkable {
    /**
     * Empty method. Can add code to buff the actor.
     * @param actor
     */
    void drink(Actor actor);

    /**
     * empty method
     * @return returns string
     */
    String toString();
}
