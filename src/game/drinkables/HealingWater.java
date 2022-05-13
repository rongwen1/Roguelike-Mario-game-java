package game.drinkables;

import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.Drinkable;

public class HealingWater implements Drinkable {
    private final String name;
    private static final int healValue = 50;

    public HealingWater(){
        this.name = "Healing Water";
    }

    @Override
    public void drink(Actor actor) {
        actor.heal(healValue);
    }

    @Override
    public String toString() {
        return this.name;
    }


}