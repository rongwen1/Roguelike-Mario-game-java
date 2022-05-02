package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Resettable;

public class Coin extends Item implements Resettable {

    /**
     * Value of coin
     */
    private int value;

    /**
     * Location of coin
     */
    private Location location;
    /**
     * if this class should reset game in boolean
     */
    private boolean resetGame;

    /**
     *
     * @param value integer that representing value of coin
     */
    public Coin(int value) {
        super("Coin",'$', true);
        this.value = value;
        this.registerInstance();
        resetGame = false;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        if (resetGame){
            currentLocation.removeItem(this);
            resetGame = false;
        }
        super.tick(currentLocation);
    }

    /**
     *
     * @return an integer that representing the value of coin
     */
    public int getValue() {
        return value;
    }

    /**
     * Overwrite getDropAction, so that the coin can't be dropped by actor
     * @param actor Actor that want to drop coin
     * @return DropItemAction
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     *
     * @return String with its value
     */
    public String toString(){
        return "Coin ($" + value + ")";
    }


    @Override
    public void resetInstance() {
        resetGame = true;
    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
