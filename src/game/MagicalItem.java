package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicalItem extends Item implements ConsumableItem{
    protected static boolean isActive;
    protected int turns;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        turns = 0;
        isActive = false;
    }

    @Override
    public void tick(Location currentLocation) {
        //If the actor consumes item from the ground, remove this item from the ground
        if (isActive){
            currentLocation.removeItem(this);
        }

        super.tick(currentLocation);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        //Increment turns
        turns += 1;

        super.tick(currentLocation, actor);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actionList = new ArrayList<Action>();
        if (!isActive){
            actionList.add(new ConsumeAction(this));
            return actionList;
        }

        return super.getAllowableActions();
    }

    @Override
    public String run(Actor actor) {
        //Add capability of this item to the actor
        List<Enum<?>> status = this.capabilitiesList();
        for (Enum<?> stat: status){
            actor.addCapability(stat);
        }

        //Update attributes
        isActive = true;

        /*
        If actor consumes item from the ground, the item will be stored inside actor's inventory.
        At the tick(Location) method above, it removes item from the ground when actor has consumed it.
        By doing this, it can count the number of effect turns after item is consumed. Then inside the
        tick() method, capabilities can be removed from the actor after item effect wears off.
         */
        //Checks if the actor has this item in the inventory
        boolean actorHasItem = false;
        for (Item item: actor.getInventory()){
            if (item == this){
                actorHasItem = true;
                break;
            }
        }
        //If actor does not have this item in the inventory, add this item to the inventory.
        if (!actorHasItem){
            actor.addItemToInventory(this);
        }
        //After running the effect of the item, prevent the actor to perform pick/drop action of this item
        this.togglePortability();

        //Returns string to be printed
        return (actor.toString() + " has consumed " + this.toString());

    }
}
