package game;

import edu.monash.fit2099.engine.actors.Actor;

public interface ConsumableItem {
    void consume(Actor actor);

    void addCapabilityToActor(Actor actor);

    void removeCapabilityFromActor(Actor actor);

    void removeItemFromInventory(Actor actor);

    boolean removeBuff(Actor actor);
}
