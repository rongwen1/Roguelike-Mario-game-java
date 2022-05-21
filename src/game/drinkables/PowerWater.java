package game.drinkables;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.interfaces.Drinkable;

/**
 * <h1>Power Water</h1>
 * Drinkable item that has buffs
 */
public class PowerWater implements Drinkable {
    /**
     * name of this item
     */
    private final String name;

    /**
     * Constructor. It initializes this item's name
     */
    public PowerWater(){
        this.name = "Power Water";
    }

    /**
     * Performs when bottle drinks this item
     */
    @Override
    public void drink(Actor actor) {
        actor.addCapability(Status.INCREEASE_BASE_DAMAGE_BY_15);
    }

    /**
     * Method that returns this item's name
     * @return name of this item
     */
    @Override
    public String toString() {
        return this.name;
    }
}
