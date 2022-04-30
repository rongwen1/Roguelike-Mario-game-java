package game;

import edu.monash.fit2099.engine.actors.Actor;

public interface ConsumableItem {
    void consume(Actor actor);

    void addCapabilityToActor();

    void removeCapabilityFromActor();

    void removeItemFromInventory();

    boolean removeBuff();
}
