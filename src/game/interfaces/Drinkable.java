package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Drinkable</h1>
 * Drinkable interface that has method to drink this water.
 */
public interface Drinkable {
    void drink(Actor actor);
    String toString();
}
