package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

public class Coin extends Item {

    private int value;

    public Coin(int value) {
        super("Coin",'$', true);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    public String toString(){
        return "Coin ($" + value + ")";
    }

}
