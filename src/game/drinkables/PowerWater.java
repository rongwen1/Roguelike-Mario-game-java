package game.drinkables;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.interfaces.Drinkable;

public class PowerWater implements Drinkable {
    private final String name;

    public PowerWater(){
        this.name = "Power Water";
    }

    @Override
    public void drink(Actor actor) {
        actor.addCapability(Status.INCREEASE_BASE_DAMAGE_BY_15);
    }

    @Override
    public String toString() {
        return this.name;
    }

}

