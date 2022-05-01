package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Currency;

public class Coin extends Item implements Resettable{

    private int value;
    private Location location;

    public Coin(int value) {
        super("Coin",'$', true);
        this.value = value;
        this.registerInstance();
        ResetManager.getInstance().appendResetInstance(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        //NEED TO BE CHANGED///////////////////////
        this.location = currentLocation;
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        //NEED TO BE CHANGED///////////////////
        this.location = currentLocation;
        super.tick(currentLocation);
    }

    public int getValue() {
        return value;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }

    public String toString(){
        return "Coin ($" + value + ")";
    }


    @Override
    public void resetInstance() {
        location.removeItem(this);
    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
