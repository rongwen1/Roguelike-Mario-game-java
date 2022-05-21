package game.drinkables;

import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.Drinkable;

/**
 * <h1>Healing Water</h1>
 * Drinkable item that has buffs
 */
public class HealingWater implements Drinkable {
    /**
     * name of this item
     */
    private final String name;
    /**
     * heal value
     */
    private static final int healValue = 50;

    /**
     * Constructor. It initializes this item's name
     */
    public HealingWater(){
        this.name = "Healing Water";
    }

    /**
     * Performs when bottle drinks this item
     */
    @Override
    public void drink(Actor actor) {
        actor.heal(healValue);
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